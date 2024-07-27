package com.promosport.app.controller;

import com.promosport.app.Exception.ResourceNotFoundException;
import com.promosport.app.model.Pari;
import com.promosport.app.service.PariService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/paris")
public class PariController {
    @Autowired
    private PariService pariService;

    @PostMapping("/creer")
    public ResponseEntity<Pari> creerPari(@RequestBody Pari pari) {
        Pari nouveauPari = pariService.creerPari(pari);
        return ResponseEntity.ok(nouveauPari);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pari> trouverPariParId(@PathVariable Long id) {
        Pari pari = pariService.trouverParId(id)
                .orElseThrow(() -> new ResourceNotFoundException("Pari non trouvé pour cet id :: " + id));
        return ResponseEntity.ok(pari);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Pari> modifierPari(@PathVariable Long id, @RequestBody Pari pariDetails) {
        Pari pari = pariService.modifierPari(id, pariDetails);
        return ResponseEntity.ok(pari);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Boolean>> supprimerPari(@PathVariable Long id) {
        pariService.supprimerPari(id);
        Map<String, Boolean> response = new HashMap<>();
        response.put("supprimé", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/calculerGains")
    public ResponseEntity<Double> calculerGains(@RequestParam double montant, @RequestParam double cote) {
        double gains = pariService.calculerGains(montant, cote);
        return ResponseEntity.ok(gains);
    }

    @GetMapping("/calculerProbabilite")
    public ResponseEntity<Double> calculerProbabilite(@RequestParam double cote) {
        double probabilite = pariService.calculerProbabilite(cote);
        return ResponseEntity.ok(probabilite);
    }

    @PutMapping("/mettreAJourResultat/{matchId}")
    public ResponseEntity<Void> mettreAJourResultatPari(@PathVariable Long matchId) {
        pariService.mettreAJourResultatPari(matchId);
        return ResponseEntity.ok().build();
    }
}
