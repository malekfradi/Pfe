package com.promosport.app.controller;

import com.promosport.app.model.Utilisateur;
import com.promosport.app.service.UtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("")
public class AuthentificationController {
	@Autowired
	private UtilisateurService utilisateurService;

	@PostMapping("/inscription")
	public ResponseEntity<Utilisateur> inscrireUtilisateur(@RequestBody Utilisateur utilisateur) {
		Utilisateur nouvelUtilisateur = utilisateurService.inscrireUtilisateur(utilisateur);
		return ResponseEntity.ok(nouvelUtilisateur);
	}

	@GetMapping("/login")
	public String loginPage() {
		return "login";
	}


	@GetMapping("/register")
	public String inscriptionPage() {
		return "register";
	}

	@GetMapping("/dashboard")
	public String dashboardPage(Model model) {
		return "dashboard";
	}
}
