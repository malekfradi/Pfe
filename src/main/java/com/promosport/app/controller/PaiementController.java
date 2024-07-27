package com.promosport.app.controller;

import com.promosport.app.Exception.ResourceNotFoundException;
import com.promosport.app.model.Paiement;
import com.promosport.app.service.PaiementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/paiements")
public class PaiementController {
    @Autowired
    private PaiementService paiementService;

    @PostMapping("/creer")
    public ResponseEntity<Paiement> creerPaiement(@RequestBody Paiement paiement) {
        Paiement nouveauPaiement = paiementService.creerPaiement(paiement);
        return ResponseEntity.ok(nouveauPaiement);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Paiement> trouverPaiementParId(@PathVariable Long id) {
        Paiement paiement = paiementService.trouverParId(id)
                .orElseThrow(() -> new ResourceNotFoundException("Paiement non trouvé pour cet id :: " + id));
        return ResponseEntity.ok(paiement);
    }

    @GetMapping("/lister")
    public ResponseEntity<List<Paiement>> listerPaiements() {
        List<Paiement> paiements = paiementService.listerPaiements();
        return ResponseEntity.ok(paiements);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Paiement> modifierPaiement(@PathVariable Long id, @RequestBody Paiement paiementDetails) {
        Paiement paiement = paiementService.modifierPaiement(id, paiementDetails);
        return ResponseEntity.ok(paiement);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Boolean>> supprimerPaiement(@PathVariable Long id) {
        paiementService.supprimerPaiement(id);
        Map<String, Boolean> response = new HashMap<>();
        response.put("supprimé", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/depot")
    public ResponseEntity<Paiement> effectuerDepot(@RequestParam Long utilisateurId, @RequestParam double montant) {
        Paiement paiement = paiementService.effectuerDepot(utilisateurId, montant);
        return ResponseEntity.ok(paiement);
    }

    @PostMapping("/retrait")
    public ResponseEntity<Paiement> effectuerRetrait(@RequestParam Long utilisateurId, @RequestParam double montant) {
        Paiement paiement = paiementService.effectuerRetrait(utilisateurId, montant);
        return ResponseEntity.ok(paiement);
    }
}
