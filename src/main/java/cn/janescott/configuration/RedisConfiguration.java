package cn.janescott.configuration;

import cn.janescott.common.constant.ConfigConstants;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.jasypt.encryption.StringEncryptor;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import redis.clients.jedis.JedisPoolConfig;

import javax.annotation.Resource;
import java.util.Arrays;

/**
 * 配置redis和缓存
 * Created by scott on 2017/7/21.
 */
@Configuration
@EnableCaching
public class RedisConfiguration extends CachingConfigurerSupport {
    @Resource
    private Environment env;

    @Resource(name = "encryptor")
    private StringEncryptor encryptor;

    // 此处有加密解密逻辑
    @Bean
    public JedisConnectionFactory jedisConnectionFactory(){
        JedisPoolConfig config = new JedisPoolConfig();
        JedisConnectionFactory factory = new JedisConnectionFactory();
        factory.setHostName(encryptor.decrypt(env.getProperty(ConfigConstants.REDIS_HOST)));
        factory.setPort(Integer.parseInt(encryptor.decrypt(env.getProperty(ConfigConstants.REDIS_PORT))));
        config.setMaxIdle(Integer.parseInt(env.getProperty(ConfigConstants.REDIS_POOL_MAXIDLE)));
        config.setMinIdle(Integer.parseInt(env.getProperty(ConfigConstants.REDIS_POOL_MINIDLE)));
        config.setMaxWaitMillis(Long.parseLong(env.getProperty(ConfigConstants.REDIS_POOL_MAXWAIT)));
        config.setMaxTotal(Integer.parseInt(env.getProperty(ConfigConstants.REDIS_POOL_MAXACTIVE)));
        factory.setPoolConfig(config);
        return factory;
    }

    @Bean(name = "stringRedisTemplate")
    public StringRedisTemplate stringRedisTemplate(JedisConnectionFactory factory){
        return new StringRedisTemplate(factory);
    }

    @Bean(name = "redisTemplate")
    @SuppressWarnings({"unchecked","rawtypes"})
    public RedisTemplate<String, Object> redisTemplate(JedisConnectionFactory factory){
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(factory);
        // 序列化
        Jackson2JsonRedisSerializer jsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        objectMapper.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        jsonRedisSerializer.setObjectMapper(objectMapper);
        // 设置序列化
        redisTemplate.setValueSerializer(jsonRedisSerializer);
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        return redisTemplate;
    }

    @Bean
    public CacheManager cacheManager(RedisTemplate redisTemplate){
        RedisCacheManager cacheManager = new RedisCacheManager(redisTemplate, Arrays.asList(ConfigConstants.REDIS_CACHE_NAMES));
        cacheManager.setDefaultExpiration(ConfigConstants.REDIS_DEFAULT_EXPIRATION);
        return cacheManager;
    }
}
