package fr.formation.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "personne")
public class Personne {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "PER_ID")
	private int id;

	@Column(name = "PER_NOM", nullable = false)
	private String nom;

	@Column(name = "PER_PRENOM", nullable = false)
	private String prenom;

	@Column(name = "PER_DATE_NAISSANCE", nullable = false)
	private LocalDate dateNaissance;

	@Column(name = "PER_ARGENT", nullable = false)
	private BigDecimal argent;

	@OneToOne
	@JoinColumn(name = "PER_POSTE_ID")
	private Poste poste;

	@OneToOne
	@JoinColumn(name = "PER_COORDONNEES_ID")
	private Coordonnees coordonnees;
	
	@ManyToOne
	@JoinColumn(name = "PER_HABITATION_ID")
	private Habitation habitation;

	public Personne() {
	}

	public Personne(String nom, String prenom, LocalDate dateNaissance, BigDecimal argent) {
		this.nom = nom;
		this.prenom = prenom;
		this.dateNaissance = dateNaissance;
		this.argent = argent;
	}

	public void payerLoyer() {
		this.setArgent(this.getArgent().subtract(this.getHabitation().getLoyer()));
	}
	
	public void allerTravailler() {
		this.setCoordonnees(this.getPoste().getWorkplace().getAdresse().getCoordonnees());
	}
	
	public void rentrer() {
		this.setCoordonnees(this.getHabitation().getAdresse().getCoordonnees());
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public LocalDate getDateNaissance() {
		return dateNaissance;
	}

	public void setDateNaissance(LocalDate dateNaissance) {
		this.dateNaissance = dateNaissance;
	}

	public BigDecimal getArgent() {
		return argent;
	}

	public void setArgent(BigDecimal argent) {
		this.argent = argent;
	}

	public Poste getPoste() {
		return poste;
	}

	public void setPoste(Poste poste) {
		this.poste = poste;
	}

	public Habitation getHabitation() {
		return habitation;
	}

	public void setHabitation(Habitation habitation) {
		this.habitation = habitation;
	}

	public Coordonnees getCoordonnees() {
		return coordonnees;
	}

	public void setCoordonnees(Coordonnees coordonnees) {
		this.coordonnees = coordonnees;
	}
	
}
