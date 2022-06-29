package com.confetystudios.redisspringboot.beans;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class CacheConfig {
	@Autowired
	private RedisTemplate<?, ?> redisTemplate;
	//Confuguracion para tratar data.
	@Bean
	public RedisTemplate<?, ?> setRedisTemplate(RedisTemplate<?, ?> redisTemplate) {
		RedisSerializer<?> stringSerializer = new StringRedisSerializer();
		redisTemplate.setKeySerializer(stringSerializer);
		redisTemplate.setValueSerializer(stringSerializer);
		redisTemplate.setHashKeySerializer(stringSerializer);
		redisTemplate.setHashValueSerializer(stringSerializer);
		this.redisTemplate = redisTemplate;
		return this.redisTemplate;
	}
	
}
