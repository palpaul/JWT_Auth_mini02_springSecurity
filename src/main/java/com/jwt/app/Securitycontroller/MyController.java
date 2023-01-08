package com.jwt.app.Securitycontroller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyController {
	@GetMapping("/wel")
	public String Welcome() {
		return "Welcome to Springboot Security websecurityConfigurationAdapter";
	}
}
