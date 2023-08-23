package com.kk;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class GbpApplication {

	public static void main(String[] args) {
		SpringApplication.run(GbpApplication.class, args);
	}

}
