package com.ace.secretscript.entity.page;

import com.ace.secretscript.common.util.Const;
import com.ace.secretscript.common.util.LoggerUtil;
import com.alibaba.druid.proxy.jdbc.ClobProxyImpl;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.*;

/**
 * @author 西野
 * @version V1.0
 * @title PageData.java
 * @package com.xiye.common.entity.page
 * @description 参数封装Map
 * @date 2020-03-27
 */
public class PageData extends HashMap implements Map {

    private static final long serialVersionUID = 1;
    private transient Map map;
    private transient HttpServletRequest request;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }
        PageData pageData = (PageData) o;
        return Objects.equals(map, pageData.map) && Objects.equals(request, pageData.request);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), map, request);
    }

    public PageData(HttpServletRequest request) {
        this.request = request;
        Map properties = request.getParameterMap();
        Map returnMap = new HashMap();
        Iterator entries = properties.entrySet().iterator();
        Entry entry;
        String name;
        String value = "";
        while (entries.hasNext()) {
            entry = (Entry) entries.next();
            name = (String) entry.getKey();
            Object valueObj = entry.getValue();
            if (null == valueObj) {
                value = "";
            } else if (valueObj instanceof String[]) {
                String[] values = (String[]) valueObj;
                for (String value1 : values) {
                    value = value1 + ",";
                }
                value = value.substring(0, value.length() - 1);
            } else {
                value = valueObj.toString();
            }
            try {
                /* 解决富文本框传值乱码 */
                value = value.replaceAll("%(?![0-9a-fA-F]{2})", "%25");
                returnMap.put(name, URLDecoder.decode(value, Const.UTF8));
            } catch (UnsupportedEncodingException e) {
                LoggerUtil.error(e.toString(), e);
            }
        }
        map = returnMap;
    }

    public PageData() {
        map = new HashMap();
    }

    @Override
    public Object get(Object key) {
        Object obj;
        if (map.get(key) instanceof Object[]) {
            Object[] arr = (Object[]) map.get(key);
            obj = request.getParameter((String) key) == null ? arr : arr[0];
        } else {
            obj = map.get(key);
        }
        return obj;
    }

    public String getString(Object key) {
        Object value = this.get(key);
        return value == null ? "" : value.toString();
    }

    @Override
    public Object put(Object key, Object value) {
        /* 读取oracle Clob类型数据 */
        if (value instanceof ClobProxyImpl) {
            try {
                ClobProxyImpl cpi = (ClobProxyImpl) value;
                /* 获取流 */
                Reader is = cpi.getCharacterStream();
                BufferedReader br = new BufferedReader(is);
                String str = br.readLine();
                StringBuilder sb = new StringBuilder();
                /* 循环读取数据拼接到字符串 */
                while (str != null) {
                    sb.append(str);
                    sb.append("\n");
                    str = br.readLine();
                }
                value = sb.toString();
            } catch (Exception e) {
                LoggerUtil.error(e.toString(), e);
            }
        }
        return map.put(key, value);
    }

    @Override
    public Object remove(Object key) {
        return map.remove(key);
    }

    @Override
    public void clear() {
        map.clear();
    }

    @Override
    public boolean containsKey(Object key) {
        return map.containsKey(key);
    }

    @Override
    public boolean containsValue(Object value) {
        return map.containsValue(value);
    }

    @Override
    public Set entrySet() {
        return map.entrySet();
    }

    @Override
    public boolean isEmpty() {
        return map.isEmpty();
    }

    @Override
    public Set keySet() {
        return map.keySet();
    }

    @Override
    public void putAll(Map t) {
        map.putAll(t);
    }

    @Override
    public int size() {
        return map.size();
    }

    @Override
    public Collection values() {
        return map.values();
    }
}
