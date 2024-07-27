package com.promosport.app.service;

import com.promosport.app.model.Paiement;

import java.util.List;
import java.util.Optional;

public interface PaiementService {
    Paiement creerPaiement(Paiement paiement);
    List<Paiement> listerPaiements();
    Optional<Paiement> trouverParId(Long id);
    Paiement modifierPaiement(Long id, Paiement paiementDetails);
    void supprimerPaiement(Long id);
    Paiement effectuerDepot(Long utilisateurId, double montant);
    Paiement effectuerRetrait(Long utilisateurId, double montant);
}
