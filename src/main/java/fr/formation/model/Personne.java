package fr.formation.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "personne")
public class Personne {
	
	
	private int id;
	private String nom;
	private String prenom;
	private LocalDate dateNaissance;
	private Habitation habitation;
	private Metier metier;
	private BigDecimal argent;
	
}
