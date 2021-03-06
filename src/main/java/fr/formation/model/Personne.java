package fr.formation.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonView;

import org.springframework.format.annotation.DateTimeFormat;

import fr.formation.api.Views;

@Entity
@Table(name = "personne")
@Inheritance(strategy=InheritanceType.JOINED)
public class Personne {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "PER_ID")
	@JsonView(Views.Commons.class)
	private int id;

	@Column(name = "PER_NOM", nullable = false)
	@JsonView(Views.Personnes.class)
	private String nom;

	@Column(name = "PER_PRENOM", nullable = false)
	@JsonView(Views.Personnes.class)
	private String prenom;

	@Column(name = "PER_DATE_NAISSANCE", nullable = false)
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	private LocalDate dateNaissance;

	@Column(name = "PER_SEXE", nullable = false)
	private Sexe sexe;
	
	@Column(name = "PER_ARGENT", nullable = false)
	private BigDecimal argent;

	@ManyToOne
	@JoinColumn(name = "PER_SERVICE_ID")
	private Prestation prestation;
	
	@OneToOne
	@JoinColumn(name = "PER_COORDONNEES_ID")
	@JsonView(Views.Personnes.class)
	private Coordonnees coordonnees;

	public Personne() {
	}

	public Personne(String nom, String prenom, LocalDate dateNaissance, Sexe sexe, BigDecimal argent) {
		super();
		this.nom = nom;
		this.prenom = prenom;
		this.dateNaissance = dateNaissance;
		this.sexe = sexe;
		this.argent = argent;
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

	public Coordonnees getCoordonnees() {
		return coordonnees;
	}

	public void setCoordonnees(Coordonnees coordonnees) {
		this.coordonnees = coordonnees;
	}

	public Sexe getSexe() {
		return sexe;
	}

	public void setSexe(Sexe sexe) {
		this.sexe = sexe;
	}

	public Prestation getPrestation() {
		return prestation;
	}

	public void setPrestation(Prestation prestation) {
		this.prestation = prestation;
	}
	
}
