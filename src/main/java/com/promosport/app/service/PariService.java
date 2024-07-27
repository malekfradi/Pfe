package com.promosport.app.service;

import com.promosport.app.model.Pari;
import com.promosport.app.model.Match;
import com.promosport.app.model.Utilisateur;
import java.util.List;
import java.util.Optional;

public interface PariService {
    Pari creerPari(Pari pari);
    Optional<Pari> trouverParId(Long id);
    Pari modifierPari(Long id, Pari pariDetails);
    void supprimerPari(Long id);
    List<Pari> listerParis();
    void mettreAJourResultatPari(Long matchId);
    double calculerGains(double montant, double cote);
    double calculerProbabilite(double cote);
}
