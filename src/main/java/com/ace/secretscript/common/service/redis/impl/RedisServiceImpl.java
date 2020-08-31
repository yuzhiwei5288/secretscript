package com.ace.secretscript.common.service.redis.impl;

import com.ace.secretscript.common.service.redis.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.List;
import java.util.Set;

/**
 * @author ace
 * @version V1.0
 * @title RedisServiceImpl.java
 * @package com.xiye.xykc.service.redis.impl
 * @description redis实现类
 * @date 2020-05-06
 */
@Service
public class RedisServiceImpl implements RedisService {

    @Autowired
    JedisPool jedisPool;

    /**
     * 命令
     */
    private int expire = 0;

    /**
     * @Author ace
     * @Date 2020-05-06 15:51:10
     * @Description get value from redis
     * @Param [key]
     * @Return byte[]
     */
    @Override
    public byte[] get(byte[] key) {
        byte[] value;
        Jedis jedis = jedisPool.getResource();
        try {
            value = jedis.get(key);
        } finally {
            jedis.close();
        }
        return value;
    }

    /**
     * @Author ace
     * @Date 2020-05-06 15:51:19
     * @Description get value from redis
     * @Param [key]
     * @Return java.lang.String
     */
    @Override
    public String get(String key) {
        String value;
        Jedis jedis = jedisPool.getResource();
        try {
            value = jedis.get(key);
        } finally {
            jedis.close();
        }
        return value;
    }

    /**
     * @Author ace
     * @Date 2020-05-06 15:51:28
     * @Description 存值
     * @Param [key, value]
     * @Return byte[]
     */
    @Override
    public byte[] set(byte[] key, byte[] value) {
        Jedis jedis = jedisPool.getResource();
        try {
            jedis.set(key, value);
            if (this.expire != 0) {
                jedis.expire(key, this.expire);
            }
        } finally {
            jedis.close();
        }
        return value;
    }

    /**
     * @Author ace
     * @Date 2020-05-06 15:51:39
     * @Description 新增(存储List)
     * @Param [key, list]
     * @Return void
     */
    @Override
    public void addList(String key, List<String> list) {
        Jedis jedis = jedisPool.getResource();
        //jedis.del(key); // 开始前，先移除所有的内容
        list.forEach(l -> jedis.rpush(key, l));
        jedis.close();
    }

    /**
     * @Author ace
     * @Date 2020-05-06 15:51:39
     * @Description 新增(存储List)
     * @Param [key, list]
     * @Return void
     */
    @Override
    public void replaceList(String key, List<String> list) {
        Jedis jedis = jedisPool.getResource();
        // 开始前，先移除所有的内容
        jedis.del(key);
        list.forEach(l -> jedis.rpush(key, l));
        jedis.close();
    }

    /**
     * @Author ace
     * @Date 2020-05-06 15:51:51
     * @Description 获取List
     * @Param [key]
     * @Return java.util.List<java.lang.String>
     */
    @Override
    public List<String> getList(String key) {
        Jedis jedis = jedisPool.getResource();
        List<String> list = jedis.lrange(key, 0, -1);
        jedis.close();
        return list;
    }

    /**
     * @Author ace
     * @Date 2020-05-06 15:52:10
     * @Description 存值
     * @Param [key, value]
     * @Return java.lang.String
     */
    @Override
    public String set(String key, String value) {
        Jedis jedis = jedisPool.getResource();
        try {
            jedis.set(key, value);
            if (this.expire != 0) {
                jedis.expire(key, this.expire);
            }
        } finally {
            jedis.close();
        }
        return value;
    }

    /**
     * @Author ace
     * @Date 2020-05-06 15:52:24
     * @Description 存值
     * @Param [key, value, expire]
     * @Return byte[]
     */
    @Override
    public byte[] set(byte[] key, byte[] value, int expire) {
        Jedis jedis = jedisPool.getResource();
        try {
            jedis.set(key, value);
            if (expire != 0) {
                jedis.expire(key, expire);
            }
        } finally {
            jedis.close();
        }
        return value;
    }

    /**
     * @Author ace
     * @Date 2020-05-06 15:52:33
     * @Description 存值
     * @Param [key, value, expire]
     * @Return java.lang.String
     */
    @Override
    public String set(String key, String value, int expire) {
        Jedis jedis = jedisPool.getResource();
        try {
            jedis.set(key, value);
            if (expire != 0) {
                jedis.expire(key, expire);
            }
        } finally {
            jedis.close();
        }
        return value;
    }

    /**
     * @Author ace
     * @Date 2020-05-06 15:52:41
     * @Description 删除
     * @Param [key]
     * @Return void
     */
    @Override
    public void del(byte[] key) {
        Jedis jedis = jedisPool.getResource();
        try {
            jedis.del(key);
        } finally {
            jedis.close();
        }
    }

    /**
     * @Author ace
     * @Date 2020-05-06 15:52:56
     * @Description 删除
     * @Param [key]
     * @Return void
     */
    @Override
    public void del(String key) {
        Jedis jedis = jedisPool.getResource();
        try {
            jedis.del(key);
        } finally {
            jedis.close();
        }
    }

    /**
     * @Author ace
     * @Date 2020-05-06 15:53:10
     * @Description 刷新
     * @Param []
     * @Return void
     */
    @Override
    public void flushDB() {
        Jedis jedis = jedisPool.getResource();
        try {
            jedis.flushDB();
        } finally {
            jedis.close();
        }
    }

    /**
     * @Author ace
     * @Date 2020-05-06 15:53:19
     * @Description 大小
     * @Param []
     * @Return java.lang.Long
     */
    @Override
    public Long dbSize() {
        Long dbSize = 0L;
        Jedis jedis = jedisPool.getResource();
        try {
            dbSize = jedis.dbSize();
        } finally {
            jedis.close();
        }
        return dbSize;
    }

    /**
     * @Author ace
     * @Date 2020-05-06 15:53:34
     * @Description 存值
     * @Param [pattern]
     * @Return java.util.Set<byte [ ]>
     */
    @Override
    public Set<byte[]> keys(String pattern) {
        Set<byte[]> keys;
        Jedis jedis = jedisPool.getResource();
        try {
            keys = jedis.keys(pattern.getBytes());
        } finally {
            jedis.close();
        }
        return keys;
    }
}
