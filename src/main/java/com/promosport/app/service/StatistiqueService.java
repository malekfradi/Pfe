package com.promosport.app.service;

import com.promosport.app.model.Statistique;
import java.util.List;
import java.util.Optional;

public interface StatistiqueService {
    Statistique creerStatistique(Statistique statistique);
    List<Statistique> listerStatistiques();
    Optional<Statistique> trouverParId(Long id); // Assurez-vous que cette méthode retourne un Optional
    Statistique modifierStatistique(Long id, Statistique statistiqueDetails);
    void supprimerStatistique(Long id);
}
