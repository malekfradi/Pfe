package com.promosport.app.controller;

import com.promosport.app.Exception.ResourceNotFoundException;
import com.promosport.app.model.Sport;
import com.promosport.app.service.SportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/sports")
public class SportController {
    @Autowired
    private SportService sportService;

    @PostMapping("/creer")
    public ResponseEntity<Sport> creerSport(@RequestBody Sport sport) {
        Sport nouveauSport = sportService.creerSport(sport);
        return ResponseEntity.ok(nouveauSport);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Sport> trouverSportParId(@PathVariable Long id) {
        Sport sport = sportService.trouverParId(id)
                .orElseThrow(() -> new ResourceNotFoundException("Sport non trouvé pour cet id :: " + id));
        return ResponseEntity.ok(sport);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Sport> modifierSport(@PathVariable Long id, @RequestBody Sport sportDetails) {
        Sport sport = sportService.modifierSport(id, sportDetails);
        return ResponseEntity.ok(sport);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Boolean>> supprimerSport(@PathVariable Long id) {
        sportService.supprimerSport(id);
        Map<String, Boolean> response = new HashMap<>();
        response.put("supprimé", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }
}