package com.confetystudios.redisspringboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;

@SpringBootApplication
@EnableRedisRepositories
public class RedisSpringBootApplication {

	public static void main(String[] args) {
		SpringApplication.run(RedisSpringBootApplication.class, args);
	}

}
