package com.example.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class HomeController {
	
	@GetMapping
	public String homeControllerHnadler() {
		return "I am Sutikshan Singh Sikarwar";
	}
	@GetMapping("/home")
	public String homeControllerHnadler2() {
		return "I am a Genius";
	}
	
	@GetMapping("/about")
	public String homeControllerHnadler3() {
		return "I can change this world";
	}

}
