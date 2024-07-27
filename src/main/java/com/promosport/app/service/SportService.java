package com.promosport.app.service;

import com.promosport.app.model.Sport;
import java.util.List;
import java.util.Optional;

public interface SportService {
    Sport creerSport(Sport sport);
    Optional<Sport> trouverParId(Long id);
    Sport modifierSport(Long id, Sport sportDetails);
    void supprimerSport(Long id);
    List<Sport> listerSports();
}
