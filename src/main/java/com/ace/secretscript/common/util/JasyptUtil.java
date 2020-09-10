package com.ace.secretscript.common.util;


import org.jasypt.encryption.pbe.PooledPBEStringEncryptor;
import org.jasypt.encryption.pbe.config.SimpleStringPBEConfig;

/**
 * @author ace
 * @version V1.0
 * @title JasyptUtil.java
 * @package com.xiye.common.util
 * @description Java配置文件加密解密，
 * 基于jasypt-spring-boot-starter 3.0.0版本；与之前的版本加密解密方式不同
 * Spring Boot 1.5.X不再受支持
 * 将默认加密更改为PBEWITHHMACSHA512ANDAES_256
 * @date 2020-03-27
 */
public final class JasyptUtil {

    private JasyptUtil() {
        throw new IllegalStateException("Utility class");
    }

    public static void main(String[] args) {

        String slat = "xiye";
//        String data = "xiye_xykc";
        String data = "1";
        PooledPBEStringEncryptor encryptor = new PooledPBEStringEncryptor();
        SimpleStringPBEConfig config = new SimpleStringPBEConfig();
        config.setPassword(slat);
        config.setAlgorithm("PBEWITHHMACSHA512ANDAES_256");
        config.setKeyObtentionIterations("1000");
        config.setPoolSize("1");
        config.setProviderName("SunJCE");
        config.setProviderClassName(null);
        config.setSaltGeneratorClassName("org.jasypt.salt.RandomSaltGenerator");
        config.setIvGeneratorClassName("org.jasypt.iv.RandomIvGenerator");
        config.setStringOutputType("base64");
        encryptor.setConfig(config);
        LoggerUtil.info(encryptor.encrypt(data));
//        LoggerUtil.info(encryptor.decrypt("FNAjnNXZt0lnwAA+InqpZhPheCaam6trhtjmcAh2SI5rjQ8vs80fITL9DHXuZAtv"));
        LoggerUtil.info(encryptor.decrypt("{bcrypt}$2a$10$tLR7VnvCgu4cuaEAtHI4XuSnGjV8wapaDVJsVrEBSLczAeWrXx1yy"));
    }
}
