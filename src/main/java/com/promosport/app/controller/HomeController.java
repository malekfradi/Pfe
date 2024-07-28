package com.promosport.app.controller;

import com.promosport.app.model.RoleEnum;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {


	@GetMapping("/")
	String index(Authentication authentication) {
		return authentication.getAuthorities().contains(RoleEnum.ADMIN) ? "dashboard" : "home";
	}
}
