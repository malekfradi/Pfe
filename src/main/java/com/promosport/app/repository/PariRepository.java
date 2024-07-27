package com.promosport.app.repository;

import com.promosport.app.model.Pari;
import com.promosport.app.model.Match;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PariRepository extends JpaRepository<Pari, Long> {
    // Trouver les paris associés à un match spécifique
    List<Pari> findByMatch(Match Match);

    // Trouver les paris d'un utilisateur spécifique
    List<Pari> findByUtilisateurId(Long utilisateurId);

    // Trouver les paris par leur résultat
    List<Pari> findByResultat(String resultat);

    // Trouver les paris d'un utilisateur sur un match spécifique
    List<Pari> findByMatchAndUtilisateurId(Match Match, Long utilisateurId);
}
