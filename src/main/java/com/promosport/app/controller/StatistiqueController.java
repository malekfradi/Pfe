package com.promosport.app.controller;

import com.promosport.app.Exception.ResourceNotFoundException;
import com.promosport.app.model.Statistique;
import com.promosport.app.service.StatistiqueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/statistiques")
public class StatistiqueController {
    @Autowired
    private StatistiqueService statistiqueService;

    @PostMapping("/creer")
    public ResponseEntity<Statistique> creerStatistique(@RequestBody Statistique statistique) {
        Statistique nouvelleStatistique = statistiqueService.creerStatistique(statistique);
        return ResponseEntity.ok(nouvelleStatistique);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Statistique> trouverStatistiqueParId(@PathVariable Long id) {
        Statistique statistique = statistiqueService.trouverParId(id)
                .orElseThrow(() -> new ResourceNotFoundException("Statistique non trouvée pour cet id :: " + id));
        return ResponseEntity.ok(statistique);
    }

    @GetMapping("/lister")
    public ResponseEntity<List<Statistique>> listerStatistiques() {
        List<Statistique> statistiques = statistiqueService.listerStatistiques();
        return ResponseEntity.ok(statistiques);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Statistique> modifierStatistique(@PathVariable Long id, @RequestBody Statistique statistiqueDetails) {
        Statistique statistique = statistiqueService.modifierStatistique(id, statistiqueDetails);
        return ResponseEntity.ok(statistique);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Boolean>> supprimerStatistique(@PathVariable Long id) {
        statistiqueService.supprimerStatistique(id);
        Map<String, Boolean> response = new HashMap<>();
        response.put("supprimé", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }
}
