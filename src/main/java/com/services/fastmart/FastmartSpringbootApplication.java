package com.services.fastmart;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
public class FastmartSpringbootApplication {

	public static void main(String[] args) {
		SpringApplication.run(FastmartSpringbootApplication.class, args);
	} 
 
}
