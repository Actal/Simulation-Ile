package fr.formation.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonView;

import fr.formation.api.Views;

@Entity
@Table(name = "biome")
public class Biome {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "BIO_ID")
	@JsonView(Views.Commons.class)
	private int id;
	
	@Column(name = "BIO_TYPE")
	@JsonView(Views.Biomes.class)
	private String type;
	
	@Column(name = "BIO_SUPERFICIE")
	@JsonView(Views.Biomes.class)
	private int longueur;

	@OneToOne
	@JoinColumn(name = "BIO_COORDONNEES_ID", unique = true)
	@JsonView(Views.Biomes.class)
	private Coordonnees coordonnees;
	
	@OneToMany(mappedBy = "biome")
	private List<Batiment> batiments;

	public Biome() {
	}

	public Biome(String type, int longueur, Coordonnees c) {
		this.type = type;
		this.longueur = longueur;
		this.coordonnees = c;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getLongueur() {
		return longueur;
	}

	public void setLongueur(int longueur) {
		this.longueur = longueur;
	}

	public Coordonnees getCoordonnees() {
		return coordonnees;
	}

	public void setCoordonnees(Coordonnees coordonnees) {
		this.coordonnees = coordonnees;
	}

	public List<Batiment> getBatiment() {
		return batiments;
	}

	public void setBatiment(List<Batiment> batiments) {
		this.batiments = batiments;
	}
	
}
