package com.example.mydemoapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class MydemoappApplication {

	public static void main(String[] args) {
		SpringApplication.run(MydemoappApplication.class, args);
	}

}
