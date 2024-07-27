package com.promosport.app.service.impl;

import com.promosport.app.Exception.ResourceNotFoundException;
import com.promosport.app.model.Utilisateur;
import com.promosport.app.repository.UtilisateurRepository;
import com.promosport.app.service.UtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class UtilisateurServiceImpl implements UtilisateurService {
    @Autowired
    private UtilisateurRepository utilisateurRepository;

    @Override
    public Utilisateur inscrireUtilisateur(Utilisateur utilisateur) {
        return utilisateurRepository.save(utilisateur);
    }

    @Override
    public Optional<Utilisateur> trouverParLogin(String login) {
        return utilisateurRepository.findByLogin(login);
    }

    @Override
    public Optional<Utilisateur> trouverParEmail(String email) {
        return utilisateurRepository.findByEmail(email);
    }

    @Override
    public Utilisateur modifierUtilisateur(Long id, Utilisateur utilisateurDetails) {
        Utilisateur utilisateur = utilisateurRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Utilisateur non trouvé pour cet id :: " + id));

        utilisateur.setLogin(utilisateurDetails.getLogin());
        utilisateur.setMotDePasse(utilisateurDetails.getMotDePasse());
        utilisateur.setRole(utilisateurDetails.getRole());
        utilisateur.setEmail(utilisateurDetails.getEmail());
        utilisateur.setSolde(utilisateurDetails.getSolde());

        return utilisateurRepository.save(utilisateur);
    }

    @Override
    public void supprimerUtilisateur(Long id) {
        Utilisateur utilisateur = utilisateurRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Utilisateur non trouvé pour cet id :: " + id));

        utilisateurRepository.delete(utilisateur);
    }

    @Override
    public Optional<Utilisateur> trouverParId(Long id) {
        return utilisateurRepository.findById(id);
    }
}
