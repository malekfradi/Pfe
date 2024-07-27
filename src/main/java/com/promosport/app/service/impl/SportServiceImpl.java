package com.promosport.app.service.impl;

import com.promosport.app.Exception.ResourceNotFoundException;
import com.promosport.app.model.Sport;
import com.promosport.app.repository.SportRepository;
import com.promosport.app.service.SportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SportServiceImpl implements SportService {
	@Autowired
	private SportRepository sportRepository;

	@Override
	public Sport creerSport(Sport sport) {
		return sportRepository.save(sport);
	}

	@Override
	public Optional<Sport> trouverParId(Long id) {
		return sportRepository.findById(id);
	}

	@Override
	public Sport modifierSport(Long id, Sport sportDetails) {
		Sport sport = sportRepository.findById(id)
			.orElseThrow(() -> new ResourceNotFoundException("Sport non trouvé pour cet id :: " + id));
		sport.setNom(sportDetails.getNom());
		return sportRepository.save(sport);
	}

	@Override
	public void supprimerSport(Long id) {
		Sport sport = sportRepository.findById(id)
			.orElseThrow(() -> new ResourceNotFoundException("Sport non trouvé pour cet id :: " + id));
		sportRepository.delete(sport);
	}

	@Override
	public List<Sport> listerSports() {
		return sportRepository.findAll();
	}
}
