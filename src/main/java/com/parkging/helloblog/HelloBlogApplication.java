package com.parkging.helloblog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class HelloBlogApplication {

	public static void main(String[] args) {
		SpringApplication.run(HelloBlogApplication.class, args);
	}

}
