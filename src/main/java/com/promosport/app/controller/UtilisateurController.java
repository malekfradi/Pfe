package com.promosport.app.controller;

import com.promosport.app.Exception.ResourceNotFoundException;
import com.promosport.app.model.Utilisateur;
import com.promosport.app.service.UtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/utilisateurs")
public class UtilisateurController {
    @Autowired
    private UtilisateurService utilisateurService;

    @PostMapping("/inscription")
    public ResponseEntity<Utilisateur> inscrireUtilisateur(@RequestBody Utilisateur utilisateur) {
        Utilisateur nouvelUtilisateur = utilisateurService.inscrireUtilisateur(utilisateur);
        return ResponseEntity.ok(nouvelUtilisateur);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Utilisateur> trouverUtilisateurParId(@PathVariable Long id) {
        Utilisateur utilisateur = utilisateurService.trouverParId(id)
                .orElseThrow(() -> new ResourceNotFoundException("Utilisateur non trouvé pour cet id :: " + id));
        return ResponseEntity.ok(utilisateur);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Utilisateur> modifierUtilisateur(@PathVariable Long id, @RequestBody Utilisateur utilisateurDetails) {
        Utilisateur utilisateur = utilisateurService.modifierUtilisateur(id, utilisateurDetails);
        return ResponseEntity.ok(utilisateur);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Boolean>> supprimerUtilisateur(@PathVariable Long id) {
        utilisateurService.supprimerUtilisateur(id);
        Map<String, Boolean> response = new HashMap<>();
        response.put("supprimé", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }
}
