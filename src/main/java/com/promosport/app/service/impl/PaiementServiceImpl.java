package com.promosport.app.service.impl;

import com.promosport.app.Exception.ResourceNotFoundException;
import com.promosport.app.model.Paiement;
import com.promosport.app.model.Utilisateur;
import com.promosport.app.repository.PaiementRepository;
import com.promosport.app.repository.UtilisateurRepository;
import com.promosport.app.service.PaiementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class PaiementServiceImpl implements PaiementService {
    @Autowired
    private PaiementRepository paiementRepository;

    @Autowired
    private UtilisateurRepository utilisateurRepository;

    @Override
    public Paiement creerPaiement(Paiement paiement) {
        return paiementRepository.save(paiement);
    }

    @Override
    public List<Paiement> listerPaiements() {
        return paiementRepository.findAll();
    }

    @Override
    public Optional<Paiement> trouverParId(Long id) {
        return paiementRepository.findById(id);
    }

    @Override
    public Paiement modifierPaiement(Long id, Paiement paiementDetails) {
        Paiement paiement = paiementRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Paiement non trouvé pour cet id :: " + id));

        paiement.setMontant(paiementDetails.getMontant());
        paiement.setDateHeure(paiementDetails.getDateHeure());
        paiement.setType(paiementDetails.getType());
        paiement.setUtilisateur(paiementDetails.getUtilisateur());

        return paiementRepository.save(paiement);
    }

    @Override
    public void supprimerPaiement(Long id) {
        Paiement paiement = paiementRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Paiement non trouvé pour cet id :: " + id));

        paiementRepository.delete(paiement);
    }

    @Override
    @Transactional
    public Paiement effectuerDepot(Long utilisateurId, double montant) {
        Utilisateur utilisateur = utilisateurRepository.findById(utilisateurId)
                .orElseThrow(() -> new ResourceNotFoundException("Utilisateur non trouvé pour cet id :: " + utilisateurId));

        utilisateur.setSolde(utilisateur.getSolde() + montant);
        utilisateurRepository.save(utilisateur);

        Paiement paiement = new Paiement();
        paiement.setMontant(montant);
        paiement.setDateHeure(LocalDateTime.now());
        paiement.setType("DEPOT");
        paiement.setUtilisateur(utilisateur);

        return paiementRepository.save(paiement);
    }

    @Override
    @Transactional
    public Paiement effectuerRetrait(Long utilisateurId, double montant) {
        Utilisateur utilisateur = utilisateurRepository.findById(utilisateurId)
                .orElseThrow(() -> new ResourceNotFoundException("Utilisateur non trouvé pour cet id :: " + utilisateurId));

        if (utilisateur.getSolde() < montant) {
            throw new IllegalArgumentException("Solde insuffisant pour le retrait");
        }

        utilisateur.setSolde(utilisateur.getSolde() - montant);
        utilisateurRepository.save(utilisateur);

        Paiement paiement = new Paiement();
        paiement.setMontant(montant);
        paiement.setDateHeure(LocalDateTime.now());
        paiement.setType("RETRAIT");
        paiement.setUtilisateur(utilisateur);

        return paiementRepository.save(paiement);
    }
}
