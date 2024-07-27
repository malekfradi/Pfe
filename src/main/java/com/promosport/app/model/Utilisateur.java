package com.promosport.app.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Entity
@Setter
@Getter
public class Utilisateur {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String login;
    private String motDePasse;
    private String email;
    @Enumerated(EnumType.STRING)
    private RoleEnum role;
    private double solde; // Solde de l'utilisateur en dinar tunisien

    @OneToMany(mappedBy = "utilisateur")
    private Set<Paiement> paiements;

    @OneToMany(mappedBy = "utilisateur")
    private Set<Pari> paris;

}
