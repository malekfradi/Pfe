package com.promosport.app.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
@Entity
public class Match {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "sport")
    private Sport sport;

    private String equipeA;
    private String equipeB;
    private LocalDateTime date;

    private String resultat; // A, B, E
    private String resultatEquipeA; // Exemple : "2" (buts marqués par l'équipe A)
    private String resultatEquipeB; // Exemple : "1" (buts marqués par l'équipe B)

	@Override
    public String toString() {
        return "Match{" +
                "id=" + id +
                ", equipeA='" + equipeA + '\'' +
                ", equipeB='" + equipeB + '\'' +
                ", date=" + date +
                ", resultatEquipeA='" + resultatEquipeA + '\'' +
                ", resultatEquipeB='" + resultatEquipeB + '\'' +
                '}';
    }
}
