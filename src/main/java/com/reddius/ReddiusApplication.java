package com.reddius;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class ReddiusApplication {

	public static void main(String[] args) {
		SpringApplication.run(ReddiusApplication.class, args);
	}

}
