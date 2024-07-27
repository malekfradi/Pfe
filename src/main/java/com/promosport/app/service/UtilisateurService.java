package com.promosport.app.service;

import com.promosport.app.model.Utilisateur;
import java.util.Optional;

public interface UtilisateurService {
    Utilisateur inscrireUtilisateur(Utilisateur utilisateur);
    Optional<Utilisateur> trouverParLogin(String login);
    Optional<Utilisateur> trouverParEmail(String email);
    Utilisateur modifierUtilisateur(Long id, Utilisateur utilisateurDetails);
    void supprimerUtilisateur(Long id);
    Optional<Utilisateur> trouverParId(Long id);
}
