package com.promosport.app.service.impl;

import com.promosport.app.Exception.ResourceNotFoundException;
import com.promosport.app.model.Statistique;
import com.promosport.app.repository.StatistiqueRepository;
import com.promosport.app.service.StatistiqueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class StatistiqueServiceImpl implements StatistiqueService {
    @Autowired
    private StatistiqueRepository statistiqueRepository;

    @Override
    public Statistique creerStatistique(Statistique statistique) {
        return statistiqueRepository.save(statistique);
    }

    @Override
    public List<Statistique> listerStatistiques() {
        return statistiqueRepository.findAll();
    }

    @Override
    public Optional<Statistique> trouverParId(Long id) {
        return statistiqueRepository.findById(id);
    }

    @Override
    public Statistique modifierStatistique(Long id, Statistique statistiqueDetails) {
        Statistique statistique = statistiqueRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Statistique non trouvée pour cet id :: " + id));

        statistique.setEquipe(statistiqueDetails.getEquipe());
        statistique.setResultatMatch(statistiqueDetails.getResultatMatch());
        statistique.setGagne(statistiqueDetails.isGagne());
        statistique.setMatch(statistiqueDetails.getMatch());

        return statistiqueRepository.save(statistique);
    }

    @Override
    public void supprimerStatistique(Long id) {
        Statistique statistique = statistiqueRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Statistique non trouvée pour cet id :: " + id));

        statistiqueRepository.delete(statistique);
    }
}
