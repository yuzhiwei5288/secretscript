package com.ace.secretscript.config.redis;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.parser.ParserConfig;
import com.alibaba.fastjson.support.spring.FastJsonRedisSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.util.StringUtils;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.time.Duration;

/**
 * @author ace
 * @version V1.0
 * @title RedisConfig.java
 * @package com.xiye.xykc.config.redis
 * @description redis配置文件
 * @date 2020-08-09
 */
@Configuration
@EnableCaching
@ConditionalOnClass(RedisOperations.class)
@EnableConfigurationProperties(RedisProperties.class)
public class RedisConfig extends CachingConfigurerSupport {

    @Value("${spring.redis.host}")
    private String host;

    @Value("${spring.redis.port}")
    private int port;

    @Value("${spring.redis.jedis.pool.timeout}")
    private int timeout;

    @Value("${spring.redis.jedis.pool.max-idle}")
    private int maxIdle;

    @Value("${spring.redis.jedis.pool.max-wait}")
    private long maxWaitMillis;

    @Value("${spring.redis.password}")
    private String password;

    /**
     * 数据库序列
     */
    @Value("${spring.redis.database}")
    private int database;

    /**
     * @Author ace
     * @Date 2020-08-09 23:33:46
     * @Description 配置redis连接池
     * @Param []
     * @Return redis.clients.jedis.JedisPool
     */
    @Bean
    public JedisPool redisPoolFactory() {
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxIdle(maxIdle);
        jedisPoolConfig.setMaxWaitMillis(maxWaitMillis);
        boolean empty = StringUtils.isEmpty(password);
        boolean equals = "".equals(password);
        if (!empty) {
            return new JedisPool(jedisPoolConfig, host, port, timeout, password, database);
        } else {
            return new JedisPool(jedisPoolConfig, host, port, timeout, null, database);
        }
    }

    /**
     * @Author ace
     * @Date 2020-08-29 23:33:58
     * @Description 设置redis数据默认过期时间，默认1天,设置@cacheable 序列化方式
     * @Param []
     * @Return org.springframework.data.redis.cache.RedisCacheConfiguration
     */
    @Bean
    public RedisCacheConfiguration redisCacheConfiguration() {
        FastJsonRedisSerializer<Object> fastJsonRedisSerializer = new FastJsonRedisSerializer<>(Object.class);
        RedisCacheConfiguration configuration = RedisCacheConfiguration.defaultCacheConfig();
        configuration = configuration.serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(fastJsonRedisSerializer)).entryTtl(Duration.ofDays(30));
        return configuration;
    }

    /**
     * @Author ace
     * @Date 2020-08-29 23:39:14
     * @Description 全局开启AutoType，不建议使用
     * ParserConfig.getGlobalInstance().setAutoTypeSupport(true);
     * 建议使用这种方式，小范围指定白名单
     * @Param [redisConnectionFactory]
     * @Return org.springframework.data.redis.core.RedisTemplate<java.lang.Object, java.lang.Object>
     */
    @Bean(name = "redisTemplate")
    @ConditionalOnMissingBean(name = "redisTemplate")
    public RedisTemplate<Object, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<Object, Object> template = new RedisTemplate<>();
        /* 序列化 */
        FastJsonRedisSerializer fastJsonRedisSerializer = new FastJsonRedisSerializer(Object.class);
        /* value值的序列化采用fastJsonRedisSerializer */
        template.setValueSerializer(fastJsonRedisSerializer);
        template.setHashValueSerializer(fastJsonRedisSerializer);
        ParserConfig.getGlobalInstance().addAccept("com.xiye.xykc.config.httpaspect");
        ParserConfig.getGlobalInstance().addAccept("com.xiye.xykc.controller");
        /* key的序列化采用StringRedisSerializer */
        template.setKeySerializer(new StringRedisSerializer());
        template.setHashKeySerializer(new StringRedisSerializer());
        template.setConnectionFactory(redisConnectionFactory);
        return template;
    }

    /**
     * @Author ace
     * @Date 2020-08-29 23:34:46
     * @Description 自定义缓存key生成策略, 使用方法 @Cacheable(keyGenerator="keyGenerator")
     * @Param []
     * @Return org.springframework.cache.interceptor.KeyGenerator
     */
    @Bean
    @Override
    public KeyGenerator keyGenerator() {
        return (target, method, params) -> {
            StringBuilder sb = new StringBuilder();
            sb.append(target.getClass().getName());
            sb.append(method.getName());
            for (Object obj : params) {
                /* 由于参数可能不同, hashCode肯定不一样, 缓存的key也需要不一样 */
                sb.append(JSON.toJSONString(obj).hashCode());
            }
            return sb.toString();
        };
    }
}