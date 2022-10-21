package com.services.fastmart.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomePageController {
	
	@GetMapping("/")
	public String showHomePage() {
		String home = "<h1> Fastmart Web Services using MongoDB</h1>" + " <h2> Welcome </h2>";
		
		return home;
	}

}
