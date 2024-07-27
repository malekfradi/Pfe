package com.promosport.app.service.impl;

import com.promosport.app.Exception.ResourceNotFoundException;
import com.promosport.app.model.Match;
import com.promosport.app.model.Pari;
import com.promosport.app.repository.MatchRepository;
import com.promosport.app.repository.PariRepository;
import com.promosport.app.service.PariService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PariServiceImpl implements PariService {

    @Autowired
    private PariRepository pariRepository;

    @Autowired
    private MatchRepository matchRepository; // Injecter MatchRepository

    @Override
    public Pari creerPari(Pari pari) {
        if (pari.getUtilisateur().getSolde() >= pari.getMontant()) {
            double nouveauSolde = pari.getUtilisateur().getSolde() - pari.getMontant();
            pari.getUtilisateur().setSolde(nouveauSolde);
            return pariRepository.save(pari);
        } else {
            throw new IllegalArgumentException("Solde insuffisant");
        }
    }

    @Override
    public Optional<Pari> trouverParId(Long id) {
        return pariRepository.findById(id);
    }

    @Override
    public List<Pari> listerParis() {
        return pariRepository.findAll();
    }

    @Override
    public Pari modifierPari(Long id, Pari pariDetails) {
        Pari pari = pariRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Pari non trouvé pour cet id :: " + id));
        pari.setMontant(pariDetails.getMontant());
        pari.setCote(pariDetails.getCote());
        pari.setTypePari(pariDetails.getTypePari());
        pari.setResultat(pariDetails.getResultat());
        pari.setGagne(pariDetails.isGagne());
        pari.setMontantGagne(pariDetails.getMontantGagne()); // Ajout de cette ligne
        return pariRepository.save(pari);
    }

    @Override
    public void supprimerPari(Long id) {
        Pari pari = pariRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Pari non trouvé pour cet id :: " + id));
        pariRepository.delete(pari);
    }

    @Override
    public double calculerGains(double montant, double cote) {
        return montant * cote;
    }

    @Override
    public double calculerProbabilite(double cote) {
        return (1 / cote) * 100;
    }

    @Override
    public void mettreAJourResultatPari(Long matchId) {
        Optional<Match> matchOpt = matchRepository.findById(matchId);
        if (matchOpt.isPresent()) {
            Match Match = matchOpt.get();
            List<Pari> paris = pariRepository.findByMatch(Match);
            for (Pari pari : paris) {
                boolean estGagnant = false;
                if (pari.getTypePari().equals("A") && Match.getResultatEquipeA().equals("victoire")) {
                    estGagnant = true;
                } else if (pari.getTypePari().equals("B") && Match.getResultatEquipeB().equals("victoire")) {
                    estGagnant = true;
                }

                if (estGagnant) {
                    double montantGagne = pari.getMontant() * pari.getCote();
                    pari.setResultat("gagné");
                    pari.setMontantGagne(montantGagne);
                } else {
                    pari.setResultat("perdu");
                }
                pariRepository.save(pari);
            }
        } else {
            throw new ResourceNotFoundException("Match non trouvé pour cet id :: " + matchId);
        }
    }
}
