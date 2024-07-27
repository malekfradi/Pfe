package com.promosport.app.service.impl;

import com.promosport.app.Exception.ResourceNotFoundException;
import com.promosport.app.model.Match;
import com.promosport.app.model.Match;
import com.promosport.app.model.Pari;
import com.promosport.app.repository.MatchRepository;
import com.promosport.app.repository.PariRepository;
import com.promosport.app.service.MatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MatchServiceImpl implements MatchService {

    @Autowired
    private MatchRepository matchRepository;

    @Autowired
    private PariRepository pariRepository;

    @Override
    public Optional<Match> trouverParId(Long id) {
        return matchRepository.findById(id);
    }

    @Override
    public Match mettreAJourResultat(Long matchId, String resultatEquipeA, String resultatEquipeB) {
        Optional<Match> matchOpt = matchRepository.findById(matchId);
        if (matchOpt.isPresent()) {
            Match Match = matchOpt.get();
            Match.setResultatEquipeA(resultatEquipeA);
            Match.setResultatEquipeB(resultatEquipeB);
            matchRepository.save(Match);

            mettreAJourResultatPari(Match);
            return Match;
        } else {
            throw new ResourceNotFoundException("Match non trouvé pour cet id :: " + matchId);
        }
    }

    @Override
    public void mettreAJourResultatPari(Match Match) {
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
    }

    @Override
    public Match creerMatch(Match Match) {
        return matchRepository.save(Match);
    }

    @Override
    public Match modifierMatch(Long id, Match MatchDetails) {
        Optional<Match> matchOpt = matchRepository.findById(id);
        if (matchOpt.isPresent()) {
            Match Match = matchOpt.get();
            Match.setDate(MatchDetails.getDate());
            Match.setEquipeA(MatchDetails.getEquipeA());
            Match.setEquipeB(MatchDetails.getEquipeB());
            Match.setResultatEquipeA(MatchDetails.getResultatEquipeA());
            Match.setResultatEquipeB(MatchDetails.getResultatEquipeB());
            return matchRepository.save(Match);
        } else {
            throw new ResourceNotFoundException("Match non trouvé pour cet id :: " + id);
        }
    }

    @Override
    public void supprimerMatch(Long id) {
        Optional<Match> matchOpt = matchRepository.findById(id);
        if (matchOpt.isPresent()) {
            matchRepository.deleteById(id);
        } else {
            throw new ResourceNotFoundException("Match non trouvé pour cet id :: " + id);
        }
    }

    @Override
    public List<Match> listerMatchs() {
        return matchRepository.findAll();
    }
}
