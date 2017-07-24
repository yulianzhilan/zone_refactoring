package cn.janescott.common.constant;

/**
 * Created by scott on 2017/7/21.
 */
public class ConfigConstants {
    // jasypt
    public static final String JASYPT_KEY = "jWgGELCkuxRuCI2Aqa6cF9VCxYpuKEZr";
    public static final String JASYPT_ALGORITHM = "PBEWithSHA1AndRC2_128";
    public static final String ENCRYPT_POOL = "1";

    // datasource
    public static final String DATASOURCE_USERNAME = "datasource.username";
    public static final String DATASOURCE_PASSWORD = "datasource.password";
    public static final String DATASOURCE_URL = "datasource.url";
    public static final String DATASOURCE_DRIVER_CLASS_NAME = "datasource.driverClassName";

    // redis
    public static final String REDIS_HOST = "redis.host";
    public static final String REDIS_PORT = "redis.port";
    public static final String REDIS_POOL_MINIDLE = "redis.pool.min-idle";
    public static final String REDIS_POOL_MAXIDLE = "redis.pool.max-idle";
    public static final String REDIS_POOL_MAXWAIT = "redis.pool.max-wait";
    public static final String REDIS_POOL_MAXACTIVE = "redis.pool.max-active";
    public static final String [] REDIS_CACHE_NAMES = {"user", "sidebar"};
    public static final Long REDIS_DEFAULT_EXPIRATION = 1800L;

    // mail
    public static final String MAIL_PROTOCOL = "mail.transport.protocol";
    public static final String MAIL_HOST = "mail.smtp.host";
    public static final String MAIL_PORT = "mail.smtp.port";
    public static final String MAIL_AUTH = "mail.smtp.auth";
    public static final String MAIL_SOCKET_FACTORY = "mail.smtp.ssl.socketFactory";
    public static final String MAIL_SSL_ENABLE = "mail.smtp.ssl.enable";
    public static final String MAIL_PERSON = "ERROR NOTICE";
    public static final String MAIL_USERNAME = "mail.smtp.username";
    public static final String MAIL_PASSWORD = "mail.smtp.password";
    public static final String MAIL_TO = "mail.smtp.to";

    // encoding
    public static final String DEFAULT_ENCODING = "UTF-8";

}
