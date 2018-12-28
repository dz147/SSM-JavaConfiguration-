package javaConfiguration.root;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisClientConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableCaching
public class RedisConfig {

    @Bean
    RedisConnectionFactory redisFactory() {
        RedisStandaloneConfiguration config = new RedisStandaloneConfiguration();
        return new JedisConnectionFactory(config);
    }

    @Bean
    RedisTemplate redisTemplate() {
        StringRedisTemplate template = new StringRedisTemplate(redisFactory());
        template.setValueSerializer(RedisSerializer.json());
        return template;
    }

    @Bean
    RedisCacheManager cacheManager() {
        RedisCacheConfiguration rcc = RedisCacheConfiguration
                .defaultCacheConfig()
                .computePrefixWith(cacheName -> "dz147." + cacheName)
                .serializeKeysWith(RedisSerializationContext.SerializationPair.
                        fromSerializer(RedisSerializer.string()))
                .serializeValuesWith(RedisSerializationContext.SerializationPair.
                        fromSerializer(RedisSerializer.json()));
        return RedisCacheManager.builder(redisFactory()).cacheDefaults(rcc).build();
    }
}