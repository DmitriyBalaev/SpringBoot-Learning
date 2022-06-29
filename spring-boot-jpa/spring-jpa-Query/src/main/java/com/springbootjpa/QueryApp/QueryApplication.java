package com.springbootjpa.QueryApp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.springbootjpa.QueryApp")
public class QueryApplication {
	public static void main(String[] args) {
		SpringApplication.run(QueryApplication.class, args);
	}
}
