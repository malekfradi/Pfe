package com.promosport.app.service;

import com.promosport.app.model.Match;

import java.util.List;
import java.util.Optional;

public interface MatchService {

    Optional<Match> trouverParId(Long id);

    Match mettreAJourResultat(Long matchId, String resultatEquipeA, String resultatEquipeB);

    void mettreAJourResultatPari(Match Match);

    // Autres méthodes que vous pourriez avoir besoin d'ajouter
    Match creerMatch(Match Match);
    Match modifierMatch(Long id, Match MatchDetails);
    void supprimerMatch(Long id);
    List<Match> listerMatchs();
}
