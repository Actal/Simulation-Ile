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
	
	@Column(name = "BAT_SUPERFICIE", nullable = false)
	private BigDecimal superficie;
	
	@Column(name = "BAT_NOM", nullable = false)
	private String nom;
	
	@Column(name = "BAT_PRIX")
	private BigDecimal prix;
	
	@Column(name = "BAT_COUT_ENTRETIEN_BASE")
	private BigDecimal coutEntretienBase;
	
	@Column(name = "BAT_NB_PLACES")
	private int nbPlace;
	
	@OneToOne
	@JoinColumn(name = "BAT_ADRESSE_ID", nullable = false)
	private Adresse adresse;
	
	@ManyToOne
	@JoinColumn(name = "BAT_BIOME_ID", nullable = false)
	private Biome biome;
	
	@ManyToOne
	@JoinColumn(name = "BAT_PROPRIETAIRE", nullable = false)
	private Proprietaire proprietaire;
	
	public Adresse getAdresse() {
		return adresse;
	}

	public void setAdresse(Adresse adresse) {
		this.adresse = adresse;
	}

	public BigDecimal getSuperficie() {
		return superficie;
	}

	public void setSuperficie(BigDecimal superficie) {
		this.superficie = superficie;
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

	public Batiment(BigDecimal superficie, String nom, BigDecimal prix, BigDecimal coutEntretienBase, int nbPlace,
			Adresse adresse, Biome biome, Proprietaire proprietaire) {
		super();
		this.superficie = superficie;
		this.nom = nom;
		this.prix = prix;
		this.coutEntretienBase = coutEntretienBase;
		this.nbPlace = nbPlace;
		this.adresse = adresse;
		this.biome = biome;
		this.proprietaire = proprietaire;
	}

	public Batiment() {
	}
}
