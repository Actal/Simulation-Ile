package fr.formation.model;

import java.math.BigDecimal;

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
@Table(name="batiment")
@Inheritance(strategy=InheritanceType.JOINED)
public abstract class Batiment {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "BAT_ID")
	private int id;
	
	@Column(name = "BAT_LONGUEUR", nullable = false)
	private int longueur;
	
	@Column(name = "BAT_NOM", nullable = false)
	private String nom;
	
	@Column(name = "BAT_PRIX")
	private BigDecimal prix;
	
	@Column(name = "BAT_COUT_ENTRETIEN_BASE")
	private BigDecimal coutEntretienBase;
	
	@Column(name = "BAT_NB_PLACES")
	private int nbPlace;
	
	@OneToOne
	@JoinColumn(name = "BAT_COORDONEE_ID", nullable = false, unique = true)
	private Coordonnees coordonnees;
	
	@ManyToOne
	@JoinColumn(name = "BAT_BIOME_ID", nullable = false)
	private Biome biome;
	
	@ManyToOne
	@JoinColumn(name = "BAT_PROPRIETAIRE", nullable = false)
	private Proprietaire proprietaire;
	
	public Coordonnees getCoordonnees() {
		return coordonnees;
	}

	public void setCoordonnees(Coordonnees coordonnees) {
		this.coordonnees = coordonnees;
	}

	public int getLongueur() {
		return longueur;
	}

	public void setLongueur(int longueur) {
		this.longueur = longueur;
	}

	public Biome getBiome() {
		return biome;
	}

	public void setBiome(Biome biome) {
		this.biome = biome;
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

	public BigDecimal getPrix() {
		return prix;
	}

	public void setPrix(BigDecimal prix) {
		this.prix = prix;
	}

	public BigDecimal getCoutEntretienBase() {
		return coutEntretienBase;
	}

	public void setCoutEntretienBase(BigDecimal coutEntretienBase) {
		this.coutEntretienBase = coutEntretienBase;
	}

	public int getNbPlace() {
		return nbPlace;
	}

	public void setNbPlace(int nbPlace) {
		this.nbPlace = nbPlace;
	}

	public Proprietaire getProprietaire() {
		return proprietaire;
	}

	public void setProprietaire(Proprietaire proprietaire) {
		this.proprietaire = proprietaire;
	}

	public Batiment(int longueur, String nom, BigDecimal prix, BigDecimal coutEntretienBase, int nbPlace,
			Coordonnees coordonnees, Biome biome, Proprietaire proprietaire) {
		this.longueur = longueur;
		this.nom = nom;
		this.prix = prix;
		this.coutEntretienBase = coutEntretienBase;
		this.nbPlace = nbPlace;
		this.coordonnees = coordonnees;
		this.biome = biome;
		this.proprietaire = proprietaire;
	}

	public Batiment() {
	}
}
