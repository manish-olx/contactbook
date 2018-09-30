package com.myproject.contactbook;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class ContactbookApplication {

	public static void main(String[] args) {
		SpringApplication.run(ContactbookApplication.class, args);
	}
}
