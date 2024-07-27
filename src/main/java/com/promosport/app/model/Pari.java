package com.promosport.app.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
public class Pari {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private double montant;
	private double cote;
	private String typePari;
	private String resultat;
	private boolean gagne;
	private double montantGagne;

	@ManyToOne
	private Utilisateur utilisateur;

	@ManyToOne
	private Match match;

}
