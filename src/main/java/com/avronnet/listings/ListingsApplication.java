package com.avronnet.listings;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;

@SpringBootApplication
public class ListingsApplication {

	public static void main(String[] args) {
		SpringApplication.run(ListingsApplication.class, args);
	}

}
