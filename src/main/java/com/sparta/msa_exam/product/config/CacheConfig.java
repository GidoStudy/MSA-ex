package com.sparta.msa_exam.product.config;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.CacheKeyPrefix;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.serializer.RedisSerializer;

import java.time.Duration;

import static org.springframework.data.redis.serializer.RedisSerializationContext.SerializationPair.fromSerializer;

@Configuration
@EnableCaching
public class CacheConfig {

    @Bean
    public RedisCacheManager redisCacheManager(RedisConnectionFactory redisConnectionFactory) {
        // 설정 구성
        // 레디스를 이용해 스프링 캐시를 사용할떄 레디스 관련 설정을 모아두는 클래스
        RedisCacheConfiguration config = RedisCacheConfiguration.defaultCacheConfig()
                // null 을 캐싱하는지
                .disableCachingNullValues()
                // 기본 캐시 유지 시간 (Time to live)
                .entryTtl(Duration.ofSeconds(60))
                // 캐시를 구분하는 접두사 설정
                .computePrefixWith(CacheKeyPrefix.simple())
                .serializeValuesWith(
                        fromSerializer(RedisSerializer.java())
                );

        return RedisCacheManager
                .builder(redisConnectionFactory)
                .cacheDefaults(config)
                .build();
    }
}
