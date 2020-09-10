package com.ace.secretscript.common.util;


/**
 * @author ace
 * @version V1.0
 * @title Const.java
 * @package com.xiye.common.util
 * @description 静态参数
 * @date 2020-03-27
 */
public final class Const {

    /**
     * 系统错误
     */
    public static final int MAP_NUMBER = 10;
    /**
     * 查询类型
     */
    public static final String  QUERY_TYPE = "queryType";
    /**
     * 查询应急物资需求计划匹配
     */
    public static final String  QUERY_TYPE_JHPP = "queryTypeJhpp";


    /**
     * 分页条数
     */
    public static final String PAGE_COUNT = "10";

    /**
     * EXCEL存放路径
     */
    public static final String EXCELMODEL = "templates/excel/";

    /**
     * 图片存放路径
     */
    public static final String PICURL = "static/pic/";

    /**
     * 代码生成器生成压缩包
     */
    public static final String ZIPNAME = "XIYE_CODE.zip";

    /**
     * 压缩Freemarker文件地址
     */
    public static final String INPUTNAME = "ftl";


    /* ===================Websocket================== */

    /**
     * 消息发送范围
     */
    public static final Integer MESSAGE_RANGE_ALL = 0;

    /**
     * 公告
     */
    public static final Integer MESSAGE_TYPE_SYSTEM = 0;

    /**
     * 提醒
     */
    public static final Integer MESSAGE_TYPE_REMIND = 1;

    /**
     * 私信
     */
    public static final Integer MESSAGE_TYPE_PERSONAL = 2;

    /**
     * 未读
     */
    public static final Integer MESSAGE_STATUS_UNREAD = 0;

    /**
     * 已读
     */
    public static final Integer MESSAGE_STATUS_READ = 1;

    /* ===================Websocket================== */

    /* ===================消息队列================== */


    /**
     * 消息推送队列
     */
    public static final String QUEUE_MESSAGE = "QUEUE_MESSAGE";

    /**
     * 部门组织机构数据队列
     */
    public static final String QUEUE_DEPT = "QUEUE_DEPT";

    /**
     * 角色数据队列
     */
    public static final String QUEUE_ROLE = "QUEUE_ROLE";

    /**
     * 目录队列
     */
    public static final String QUEUE_MENU = "QUEUE_MENU";

    /**
     * 七牛操作队列
     */
    public static final String QUEUE_UPLOAD = "QUEUE_UPLOAD";

    /**
     * 缓存全部开启的部门信息、全部开启的角色信息队列等各种字典项
     */
    public static final String QUEUE_BUILD = "QUEUE_BUILD";

    /**
     * 岗位缓存队列
     */
    public static final String QUEUE_JOB = "QUEUE_JOB";

    /**
     * 数据字典缓存队列
     */
    public static final String QUEUE_DICTIONARIES = "QUEUE_DICTIONARIES";

    /* ===================消息队列================== */


    /* ===================redis缓存key================== */

    /**
     * 部门组织机构tree树形串
     */
    public static final String REDIS_DEPT_TREE = "dept:tree";

    /**
     * 物料主数据种类tree树形串
     */
    public static final String REDIS_MATERIALKIND_TREE = "materialkind:tree";

    /**
     * 部门组织机构数据LIST，用来展示下拉select等场景
     */
    public static final String REDIS_DEPT_LIST = "dept:list";

    /**
     * 角色数据LIST，用来展示下拉select等场景
     */
    public static final String REDIS_ROLE_LIST = "role:list";

    /**
     * 岗位数据LIST，用来展示下拉select等场景
     */
    public static final String REDIS_JOB_LIST = "job:list";

    /**
     * 字典项LIST，用来展示下拉select等场景
     */
    public static final String REDIS_DICTIONARIES_LIST = "dictionaries:list";

    /**
     * 民族数据LIST，用来展示下拉select等场景
     */
    public static final String REDIS_DICTIONARIES_NATION_LIST = "dictionaries:nation:list";

    /**
     * 政治面貌数据LIST，用来展示下拉select等场景
     */
    public static final String REDIS_DICTIONARIES_POLITICAL_LIST = "dictionaries:political:list";

    /**
     * 用户数据缓存，比如用户信息、角色信息、部门信息
     */
    public static final String REDIS_USER = "user:";

    /**
     * 消息推送队列
     */
    public static final String REDIS_MENU_USER = "menu_user:";

    /**
     * 部门组织机构数据队列
     */
    public static final String REDIS_LOGSTASH = "logstash:redis";

    /**
     * 用户id工厂id关联
     * */
    public static final String REDIS_USER_FACTORYID = "factory_id:";
    /**
     * 用户id供应商id关联
     * */
    public static final String REDIS_USER_SUPPLIERID = "supplier_id:";

    /* ===================redis缓存key================== */

    /* ===================定时任务================== */

    /**
     * 正常状态
     */
    public static final String STATUS_NORMAL = "0";

    /**
     * 禁用状态
     */
    public static final String STATUS_DISABLE = "-1";

    /* ===================定时任务================== */


    /* ===================响应 Code 常量定义类================== */

    /**
     * 请求失败
     */
    public static final Boolean REQUEST_SUCCESS = true;

    /**
     * 请求失败
     */
    public static final Boolean REQUEST_FAILED = false;

    /**
     * 系统错误
     */
    public static final int SYSTEM_ERROR = -1;

    /* ===================响应 Code 常量定义类================== */

    /* ===================响应 Code 常量定义类================== */

    /**
     * Token 失效
     */
    public static final int OAUTH_TOKEN_FAILURE = 401;

    /**
     * Token 缺失
     */
    public static final int OAUTH_TOKEN_MISSING = 401;

    /**
     * Token 权限不足
     */
    public static final int OAUTH_TOKEN_DENIED = 403;

    /* ===================响应 Code 常量定义类================== */

    /* ===================响应 Message 信息定义类================== */

    public static final String OAUTH_TOKEN_MISSING_MESSAGE = "Token 缺失";
    public static final String OAUTH_TOKEN_ILLEGAL_MESSAGE = "Token 格式非法或已失效";
    public static final String OAUTH_TOKEN_DENIED_MESSAGE = "Token 权限不足";
    public static final String SERVICE_ERROR = "网络异常，请稍后再试";

    /* ===================响应 Message 信息定义类================== */

    /**
     * 超级管理员验证
     */
    public static final String ADMIN_RIGHT = "admin";

    /**
     * 用户ID主键常量
     */
    public static final String USERID = "userId";

    /**
     * 账户常量
     */
    public static final String USERNAME = "username";

    /**
     * 用户密码主键常量
     */
    public static final String CYPHER = "password";

    /**
     * 状态常量
     */
    public static final String STATUS = "status";

    /**
     * 上级ID常量
     */
    public static final String JOBID = "jobId";

    /**
     * 上级ID常量
     */
    public static final String PARENTID = "parentId";

    /**
     * 角色ID主键常量
     */
    public static final String ROLEID = "roleId";

    /**
     * 角色ID主键常量
     */
    public static final String EMIAL_ADDRESS = "email";

    /**
     * 角色ID主键常量
     */
    public static final String KEYWORDS = "keywords";

    /**
     * 时间过滤常量
     */
    public static final String SELECTMONTH = "selectmonth";

    /**
     * 下拉框条件--单位名称
     * */
//    public static final String SELECTDWMC = "selectdwmc";

    /**
     * 下拉框条件--物料描述
     * */
//    public static final String SELECTWLMS = "selectwlms";

    /**
     * 下拉框条件--物料小类
     * */
//    public static final String SELECTWLXL = "selectwlxl";

    /**
     * 下拉框条件--仓库名称
     * */
//    public static final String SELECTCKMC = "selectckmc";

    /**
     * 地址相关
     */
    public static final String FILEPATH = "\\";

    public static final String FILEPATH_LEFT = "/";

    /**
     * 默认头像
     */
    public static final String IMAGEHEAD = "http://xiye.zhongxujituan.com/moren.jpg";

    /**
     * 操作类型--1：新增；2：单删；3：批量删除；4：重命名
     */
    public static final String QINIU_STATUS = "QINIU_STATUS";

    /**
     * 邮箱验证码前缀
     */
    public static final String EMAIL_CODE = "email:";

    /**
     * 手机验证码前缀
     */
    public static final String PHONE_CODE = "phone:";

    /**
     * 邮箱配置
     */
    public static final String EMAIL_SETTING = "EMAIL_SETTING";

    /**
     * qq保存state前缀key
     */
    public static final String QQ_STATE = "XIYE_QQ:";

    /**
     * qq登录
     */
    public static final Integer SOCIAL_TYPE_QQ = 1;

    /**
     * 微博登录保存state前缀key
     */
    public static final String WEIBO_STATE = "XIYE_WEIBO:";

    /**
     * 微博登录
     */
    public static final Integer SOCIAL_TYPE_WEIBO = 2;

    /**
     * 编码格式
     */
    public static final String UTF8 = "UTF-8";


    /* ===================Activiti工作流相关================== */

    /**
     * 流程状态 已激活
     */
    public static final Integer PROCESS_STATUS_ACTIVE = 1;

    /**
     * 流程状态 暂停挂起
     */
    public static final Integer PROCESS_STATUS_SUSPEND = 0;

    /**
     * 资源类型 XML
     */
    public static final Integer RESOURCE_TYPE_XML = 0;

    /**
     * 资源类型 图片
     */
    public static final Integer RESOURCE_TYPE_IMAGE = 1;

    /**
     * 状态 待提交申请
     */
    public static final Integer STATUS_TO_APPLY = 0;

    /**
     * 状态 处理中
     */
    public static final Integer STATUS_DEALING = 1;

    /**
     * 状态 处理结束
     */
    public static final Integer STATUS_FINISH = 2;

    /**
     * 状态 已撤回
     */
    public static final Integer STATUS_CANCELED = 3;

    /**
     * 结果 待提交
     */
    public static final Integer RESULT_TO_SUBMIT = 0;

    /**
     * 结果 处理中
     */
    public static final Integer RESULT_DEALING = 1;

    /**
     * 结果 通过
     */
    public static final Integer RESULT_PASS = 2;

    /**
     * 结果 驳回
     */
    public static final Integer RESULT_FAIL = 3;

    /**
     * 结果 撤回
     */
    public static final Integer RESULT_CANCEL = 4;

    /**
     * 结果 删除
     */
    public static final Integer RESULT_DELETED = 5;

    /**
     * 节点类型 开始节点
     */
    public static final Integer NODE_TYPE_START = 0;

    /**
     * 节点类型 用户任务
     */
    public static final Integer NODE_TYPE_TASK = 1;

    /**
     * 节点类型 结束
     */
    public static final Integer NODE_TYPE_END = 2;

    /**
     * 节点类型 排他网关
     */
    public static final Integer NODE_TYPE_EG = 3;

    /**E
     * 节点关联类型 角色
     */
    public static final Integer NODE_ROLE = 0;

    /**
     * 节点关联类型 用户
     */
    public static final Integer NODE_USER = 1;

    /**
     * 节点关联类型 部门
     */
    public static final Integer NODE_DEPARTMENT = 2;

    /**
     * 节点关联类型 操作人的部门负责人
     */
    public static final Integer NODE_DEP_HEADER = 3;

    /**
     * 待办消息id
     */
    public static final String MESSAGE_TODO_ID = "124717033060306944";

    /**
     * 通过消息id
     */
    public static final String MESSAGE_PASS_ID = "124743474544119808";

    /**
     * 驳回消息id
     */
    public static final String MESSAGE_BACK_ID = "124744392996032512";

    /**
     * 委托消息id
     */
    public static final String MESSAGE_DELEGATE_ID = "124744706050494464";

    /**
     * 待办消息
     */
    public static final String MESSAGE_TODO_CONTENT = "待审批";

    /**
     * 通过消息
     */
    public static final String MESSAGE_PASS_CONTENT = "申请流程已通过";

    /**
     * 驳回消息
     */
    public static final String MESSAGE_BACK_CONTENT = "申请流程已驳回";

    /**
     * 委托消息
     */
    public static final String MESSAGE_DELEGATE_CONTENT = "被委托待审批";

    /**
     * 执行任务用户类型
     */
    public static final String EXECUTOR_TYPE = "actualExecutor";

    /**
     * 删除理由前缀
     */
    public static final String DELETE_PRE = "deleted-";

    /**
     * 取消理由前缀
     */
    public static final String CANCEL_PRE = "canceled-";

    /**
     * 驳回标记
     */
    public static final String BACKED_FLAG = "backed";

    /**
     * 通过标记
     */
    public static final String PASSED_FLAG = "completed";

    /**
     * 下拉框表名
     */
    public static final String SELECT_TABLE = "table";
    /**
     * 下拉框字段1
     */
    public static final String SELECT_COLUMN1 = "column1";
    /**
     * 下拉框字段2
     */
    public static final String SELECT_COLUMN2 = "column2";

    /**
     * CHARTS表名
     */
    public static final String CHARTS_TABLE = "table";

    /**
     *公共方法charts字段
     */
    public static final String CHARTS_COLUMN = "column";

    /**
     *公共方法charts group字段值
     */
    public static final String CHARTS_GROUP_COLUMN = "groupColumn";

    /**
     *公共方法charts查询条件
     */
    public static final String CHARTS_CONDITION= "condition";



    /* ===================Activiti工作流相关================== */

    private Const() {
        throw new IllegalStateException("Utility class");
    }
}

