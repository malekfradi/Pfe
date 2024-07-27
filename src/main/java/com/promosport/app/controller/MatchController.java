package com.promosport.app.controller;

import com.promosport.app.model.Match;
import com.promosport.app.service.MatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/matchs")
public class MatchController {

    @Autowired
    private MatchService matchService;

    @PutMapping("/resultat/{id}")
    public ResponseEntity<Match> mettreAJourResultat(@PathVariable Long id,
                                                           @RequestParam String resultatEquipeA,
                                                           @RequestParam String resultatEquipeB) {
        Match Match = matchService.mettreAJourResultat(id, resultatEquipeA, resultatEquipeB);
        return ResponseEntity.ok(Match);
    }
}
