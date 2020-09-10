package com.ace.secretscript.common.plugin;

import com.ace.secretscript.common.util.LoggerUtil;
import com.ace.secretscript.common.util.ReflectHelper;
import com.ace.secretscript.common.util.Tools;
import com.ace.secretscript.entity.page.Page;
import org.apache.ibatis.executor.ErrorContext;
import org.apache.ibatis.executor.ExecutorException;
import org.apache.ibatis.executor.statement.BaseStatementHandler;
import org.apache.ibatis.executor.statement.RoutingStatementHandler;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.mapping.ParameterMode;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.property.PropertyTokenizer;
import org.apache.ibatis.scripting.xmltags.ForEachSqlNode;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.type.TypeHandler;
import org.apache.ibatis.type.TypeHandlerRegistry;

import javax.xml.bind.PropertyException;
import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;

/**
 * @author ace
 * @version V1.0
 * @title PagePlugin.java
 * @package com.xiye.common.plugin
 * @description 分页插件
 * MyBatis分页拦截器，测试是报错java.lang.NoSuchMethodException:org.apache.ibatis.executor.statement.StatementHandler.prepare(java.sql.Connection)。具体信息如下：
 * 出现此错误的原因是MyBatis 3.4.0 之后，StatementHandler的prepare方法做了修改，如下：
 * Statement prepare(Connection connection, Integer transactionTimeout) throws SQLException;
 * 解决办法：在args = { Connection.class }中添加第二个参数Integer.class
 * @Intercepts({ @Signature(type = StatementHandler.class, method = "prepare", args = { Connection.class,Integer.class }) })
 * @date 2020-03-27
 */
@Intercepts({@Signature(type = StatementHandler.class, method = "prepare", args = {Connection.class, Integer.class})})
public class PagePlugin implements Interceptor {

    /**
     * 数据库方言
     */
    private String dialect = "";

    /**
     * mapper.xml中需要拦截的ID(正则匹配)
     */
    private String pageSqlId = "";

    @Override
    public Object intercept(Invocation ivk) throws Throwable {
        if (ivk.getTarget() instanceof RoutingStatementHandler) {
            RoutingStatementHandler statementHandler = (RoutingStatementHandler) ivk.getTarget();
            BaseStatementHandler delegate = (BaseStatementHandler) ReflectHelper.getValueByFieldName(statementHandler, "delegate");
            MappedStatement mappedStatement = (MappedStatement) ReflectHelper.getValueByFieldName(delegate, "mappedStatement");

            /* 拦截需要分页的SQL */
            if (mappedStatement.getId().matches(pageSqlId)) {
                BoundSql boundSql = delegate.getBoundSql();
                /* 分页SQL<select>中parameterType属性对应的实体参数，即Mapper接口中执行分页方法的参数,该参数不得为空 */
                Object parameterObject = boundSql.getParameterObject();
                if (parameterObject != null) {
                    Connection connection = (Connection) ivk.getArgs()[0];
                    String sql = boundSql.getSql();
                    // String countSql = "select count(0) from (" + sql+ ") as tmp_count"; //记录统计
                    /* 记录统计 == oracle 加 as 报错(SQL command not properly ended) */
                    String countSql = "SELECT COUNT(1) FROM (" + sql + ") TMP_COUNT";
                    PreparedStatement countStmt = connection.prepareStatement(countSql);
                    BoundSql countBS = new BoundSql(mappedStatement.getConfiguration(), countSql, boundSql.getParameterMappings(), parameterObject);
                    setParameters(countStmt, mappedStatement, countBS, parameterObject);
                    int count = 0;
                    ResultSet rs = null;
                    try {
                        rs = countStmt.executeQuery();
                        if (rs.next()) {
                            count = rs.getInt(1);
                        }
                    } catch (SQLException e) {
                        LoggerUtil.error(e.toString(), e);
                    } finally {
                        if (rs != null) {
                            rs.close();
                        }
                        countStmt.close();
                    }
                    Page page;
                    /* 参数就是Page实体 */
                    if (parameterObject instanceof Page) {
                        page = (Page) parameterObject;
                        page.setEntityOrField(true);
                        page.setTotalResult(count);
                    } else {
                        /* 参数为某个实体，该实体拥有Page属性 */
                        Field pageField = ReflectHelper.getFieldByFieldName(parameterObject, "page");
                        if (pageField != null) {
                            page = (Page) ReflectHelper.getValueByFieldName(parameterObject, "page");
                            if (page == null) {
                                page = new Page();
                            }
                            page.setEntityOrField(false);
                            page.setTotalResult(count);
                            /* 通过反射，对实体对象设置分页对象 */
                            ReflectHelper.setValueByFieldName(parameterObject, "page", page);
                        } else {
                            throw new NoSuchFieldException(parameterObject.getClass().getName() + "不存在 page 属性！");
                        }
                    }
                    String pageSql = generatePageSql(sql, page);
                    /* 将分页sql语句反射回BoundSql */
                    ReflectHelper.setValueByFieldName(boundSql, "sql", pageSql);
                }
            }
        }
        return ivk.proceed();
    }

    /**
     * @Author ace
     * @Date 2020-03-27 16:25:08
     * @Description 对SQL参数(?)设值, 参考org.apache.ibatis.executor.parameter.DefaultParameterHandler
     * @Param [ps, mappedStatement, boundSql, parameterObject]
     * @Return void
     */
    private static void setParameters(PreparedStatement ps, MappedStatement mappedStatement, BoundSql boundSql, Object parameterObject) throws SQLException {
        ErrorContext.instance().activity("setting parameters").object(mappedStatement.getParameterMap().getId());
        List<ParameterMapping> parameterMappings = boundSql.getParameterMappings();
        if (parameterMappings != null) {
            Configuration configuration = mappedStatement.getConfiguration();
            TypeHandlerRegistry typeHandlerRegistry = configuration.getTypeHandlerRegistry();
            MetaObject metaObject = parameterObject == null ? null : configuration.newMetaObject(parameterObject);
            for (int i = 0; i < parameterMappings.size(); i++) {
                ParameterMapping parameterMapping = parameterMappings.get(i);
                if (parameterMapping.getMode() != ParameterMode.OUT) {
                    Object value;
                    String propertyName = parameterMapping.getProperty();
                    PropertyTokenizer prop = new PropertyTokenizer(propertyName);
                    if (parameterObject == null) {
                        value = null;
                    } else if (typeHandlerRegistry.hasTypeHandler(parameterObject.getClass())) {
                        value = parameterObject;
                    } else if (boundSql.hasAdditionalParameter(propertyName)) {
                        value = boundSql.getAdditionalParameter(propertyName);
                    } else if (propertyName.startsWith(ForEachSqlNode.ITEM_PREFIX) && boundSql.hasAdditionalParameter(prop.getName())) {
                        value = boundSql.getAdditionalParameter(prop.getName());
                        if (value != null) {
                            value = configuration.newMetaObject(value).getValue(propertyName.substring(prop.getName().length()));
                        }
                    } else {
                        value = metaObject == null ? null : metaObject.getValue(propertyName);
                    }
                    TypeHandler typeHandler = parameterMapping.getTypeHandler();
                    if (typeHandler == null) {
                        throw new ExecutorException("There was no TypeHandler found for parameter " + propertyName + " of statement " + mappedStatement.getId());
                    }
                    typeHandler.setParameter(ps, i + 1, value, parameterMapping.getJdbcType());
                }
            }
        }
    }

    /**
     * @Author ace
     * @Date 2020-03-27 16:25:18
     * @Description 根据数据库方言，生成特定的分页sql
     * @Param [sql, page]
     * @Return java.lang.String
     */
    private String generatePageSql(String sql, Page page) {
        if (page != null && Tools.notEmpty(dialect)) {
            StringBuilder pageSql = new StringBuilder();
            if ("oracle".equals(dialect)) {
                pageSql.append("SELECT * FROM (SELECT TMP_TB.*,ROWNUM ROW_ID FROM (");
                pageSql.append(sql);
                pageSql.append(") TMP_TB WHERE ROWNUM <= ");
                pageSql.append(page.getCurrentResult() + page.getShowCount());
                pageSql.append(") WHERE ROW_ID > ");
                pageSql.append(page.getCurrentResult());
            }
            return pageSql.toString();
        } else {
            return sql;
        }
    }

    @Override
    public Object plugin(Object arg0) {
        return Plugin.wrap(arg0, this);
    }

    @Override
    public void setProperties(Properties p) {
        dialect = p.getProperty("dialect");
        if (Tools.isEmpty(dialect)) {
            try {
                throw new PropertyException("dialect property is not found!");
            } catch (PropertyException e) {
                LoggerUtil.error(e.toString(), e);
            }
        }
        pageSqlId = p.getProperty("pageSqlId");
        if (Tools.isEmpty(pageSqlId)) {
            try {
                throw new PropertyException("pageSqlId property is not found!");
            } catch (PropertyException e) {
                LoggerUtil.error(e.toString(), e);
            }
        }
    }

}
