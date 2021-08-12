package fr.formation.model;

import java.math.BigDecimal;

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
@Table(name = "batiment")
public class Batiment {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "BAT_ID")
	private int id;
	
	@OneToOne
	@JoinColumn(name = "BAT_ADRESSE_ID", nullable = false)
	private Adresse adresse;
	
	@Column(name = "BAT_SUPERFICIE", nullable = false)
	private BigDecimal superficie;
	
	@ManyToOne
	@JoinColumn(name = "BAT_BIOME_ID", nullable = false)
	private Biome biome;
	
	@OneToOne
	@JoinColumn(name = "BAT_COORDONNEES_ID")
	private Coordonnees coordonnees;

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

	public Coordonnees getCoordonnees() {
		return coordonnees;
	}

	public void setCoordonnees(Coordonnees coordonnees) {
		this.coordonnees = coordonnees;
	}

	public Batiment(Adresse adresse, BigDecimal superficie, Biome biome) {
		this.adresse = adresse;
		this.superficie = superficie;
		this.biome = biome;
	}

	public Batiment() {
	}
}
