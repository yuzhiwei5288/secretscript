package com.ace.secretscript.common.service.redis;

import java.util.List;
import java.util.Set;

/**
 * @author 西野
 * @version V1.0
 * @title RedisService.java
 * @package com.xiye.xykc.service.redis
 * @description redis接口
 * @date 2020-05-06
 */
public interface RedisService {

    /**
     * get value from redis
     *
     * @param key
     * @return byte[]
     * @author 西野
     * @date 2020-05-06 13:06:07
     */
    byte[] get(byte[] key);

    /**
     * get value from redis
     *
     * @param key
     * @return java.lang.String
     * @author 西野
     * @date 2020-05-06 13:06:07
     */
    String get(String key);

    /**
     * 存值
     *
     * @param key,value
     * @return byte[]
     * @author 西野
     * @date 2020-05-06 13:06:07
     */
    byte[] set(byte[] key, byte[] value);

    /**
     * 新增(存储List)
     *
     * @param key,value
     * @return void
     * @author 西野
     * @date 2020-05-06 13:06:07
     */
    void addList(String key, List<String> list);

    /**
     * 新增(存储List)
     *
     * @param key,value
     * @return void
     * @author 西野
     * @date 2020-05-06 13:06:07
     */
    void replaceList(String key, List<String> list);

    /**
     * 获取List
     *
     * @param key
     * @return java.util.List<java.lang.String>
     * @author 西野
     * @date 2020-05-06 13:06:07
     */
    List<String> getList(String key);

    /**
     * 存值
     *
     * @param key
     * @return java.lang.String
     * @author 西野
     * @date 2020-05-06 13:06:07
     */
    String set(String key, String value);

    /**
     * 存值
     *
     * @param key,value,expire
     * @return byte[]
     * @author 西野
     * @date 2020-05-06 13:06:07
     */
    byte[] set(byte[] key, byte[] value, int expire);

    /**
     * 存值
     *
     * @param key,value,expire
     * @return java.lang.String
     * @author 西野
     * @date 2020-05-06 13:06:07
     */
    String set(String key, String value, int expire);

    /**
     * 删除
     *
     * @param key
     * @return void
     * @author 西野
     * @date 2020-05-06 13:06:07
     */
    void del(byte[] key);

    /**
     * 删除
     *
     * @param key
     * @return void
     * @author 西野
     * @date 2020-05-06 13:06:07
     */
    void del(String key);

    /**
     * 刷新
     *
     * @param
     * @return void
     * @author 西野
     * @date 2020-05-06 13:06:07
     */
    void flushDB();

    /**
     * 大小
     *
     * @param
     * @return java.lang.Long
     * @author 西野
     * @date 2020-05-06 13:06:07
     */
    Long dbSize();

    /**
     * 大小
     *
     * @param pattern
     * @return java.util.Set<byte[]>
     * @author 西野
     * @date 2020-05-06 13:06:07
     */
    Set<byte[]> keys(String pattern);
}
