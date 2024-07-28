package com.promosport.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {


	@GetMapping("/home")
	String home() {
		return "home";
	}

	@GetMapping("/matchs")
	String matchsIndex() {
		return "matchs";
	}

	@GetMapping("/results")
	String resultatsIndex() {
		return "results";
	}
}

