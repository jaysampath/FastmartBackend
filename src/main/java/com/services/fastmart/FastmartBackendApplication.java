package com.services.fastmart;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication
public class FastmartBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(FastmartBackendApplication.class, args);
	} 
 
}
