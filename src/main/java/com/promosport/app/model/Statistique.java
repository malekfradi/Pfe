package com.promosport.app.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
public class Statistique {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String equipe;
	private String resultatMatch;
	private boolean gagne;

	@ManyToOne
	@JoinColumn(name = "match_id")
	private Match match;
}
