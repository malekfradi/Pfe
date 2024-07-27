package com.promosport.app.controller;

import com.promosport.app.model.Utilisateur;
import com.promosport.app.service.UtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

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
    public String loginPage(Model model) {
        Utilisateur user = new Utilisateur();
        model.addAttribute("user", user);
        return "login";
    }
/*
    @PostMapping("/perform_login")
    public String loginAction(Utilisateur user, RedirectAttributes redirectAttributes) {
        try {
            redirectAttributes.addFlashAttribute("message", "");
        } catch (Exception e) {
            redirectAttributes.addAttribute("message", e.getMessage());
        }

        return "redirect:/dashboard";
    }*/


    @GetMapping("/inscription")
    public String inscriptionPage(Model model) {
        Utilisateur user = new Utilisateur();
        model.addAttribute("user", user);
        return "register";
    }

    @GetMapping("/dashboard")
    public String dashboardPage(Model model) {
        return "dashboard";
    }
}
