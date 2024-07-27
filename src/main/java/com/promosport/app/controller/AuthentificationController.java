package com.promosport.app.controller;

import com.promosport.app.model.Utilisateur;
import com.promosport.app.service.UtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
public class AuthentificationController {
    @Autowired
    private UtilisateurService utilisateurService;

    @PostMapping("/inscription")
    public ResponseEntity<Utilisateur> inscrireUtilisateur(@RequestBody Utilisateur utilisateur) {
        Utilisateur nouvelUtilisateur = utilisateurService.inscrireUtilisateur(utilisateur);
        return ResponseEntity.ok(nouvelUtilisateur);
    }

    @PostMapping("/connexion")
    public ResponseEntity<String> connecterUtilisateur(@RequestBody Utilisateur utilisateur) {
        Optional<Utilisateur> utilisateurOpt = utilisateurService.trouverParLogin(utilisateur.getLogin());
        if (utilisateurOpt.isPresent() && utilisateurOpt.get().getMotDePasse().equals(utilisateur.getMotDePasse())) {
            return ResponseEntity.ok("Connexion réussie");
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Échec de la connexion");
    }
}
