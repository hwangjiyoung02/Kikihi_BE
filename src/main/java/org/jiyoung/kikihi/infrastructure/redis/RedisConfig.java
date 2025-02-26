package org.jiyoung.kikihi.infrastructure.redis;

import org.springframework.cache.CacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.time.Duration;

@Configuration
public class RedisConfig {
    // LettuceConnectionFactory 빈을 생성합니다. 이 팩토리는 Redis 서버와의 연결을 관리합니다.
    @Bean
    LettuceConnectionFactory lettuceConnectionFactory() {
        return new LettuceConnectionFactory();
    }

    // RedisTemplate 빈을 생성합니다. 이 템플릿은 Redis 서버와 상호작용하는 데 사용됩니다.
    @Bean
    public RedisTemplate<String,Object> redisTemplate(){
        RedisTemplate<String,Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(lettuceConnectionFactory());
        return redisTemplate;
    }

    // CacheManager 빈을 생성합니다. 이 관리자는 Redis를 캐시 저장소로 사용합니다.
    @Bean
    public CacheManager cacheManager(RedisConnectionFactory factory){
        RedisCacheConfiguration cacheConfiguration=RedisCacheConfiguration.defaultCacheConfig()
                // key, value
                .serializeKeysWith(RedisSerializationContext.SerializationPair.fromSerializer(new StringRedisSerializer()))
                .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(
                        new GenericJackson2JsonRedisSerializer())).entryTtl(Duration.ofMinutes(1L));// 캐시 항목의 TTL(시간 제한)을 1분으로 설정합니다.
        //캐시 매니저 빌더로
        return RedisCacheManager.RedisCacheManagerBuilder
                .fromConnectionFactory(factory)
                .cacheDefaults(cacheConfiguration)
                .build();


    }

}
