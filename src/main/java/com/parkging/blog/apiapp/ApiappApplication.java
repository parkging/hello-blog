package com.parkging.blog.apiapp;

import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
@EnableEncryptableProperties
public class ApiappApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiappApplication.class, args);
	}

}
