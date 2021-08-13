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

@Entity
@Table(name = "personne")
@Inheritance(strategy=InheritanceType.JOINED)
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

	@Column(name = "PER_SEXE", nullable = false)
	private Sexe sexe;
	
	@Column(name = "PER_ARGENT", nullable = false)
	private BigDecimal argent;

	@ManyToOne
	@JoinColumn(name = "PER_SERVICE_ID")
	private Service service;
	
	@OneToOne
	@JoinColumn(name = "PER_COORDONNEES_ID")
	private Coordonnees coordonnees;

	public Boolean payer(BigDecimal somme) {
		if( this.getArgent().compareTo(somme) >= 0) {
			this.getArgent().subtract(somme);
			return true;
		}
		return false;
	}
	
	public void gagnerArgent(BigDecimal somme) {
		this.getArgent().add(somme);
	}
	
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

	public Service getService() {
		return service;
	}

	public void setService(Service service) {
		this.service = service;
	}
	
}
