package fr.formation.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "biome")
public class Biome {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "BIO_ID")
	private int id;
	
	@Column(name = "BIO_TYPE")
	private String type;
	
	@Column(name = "BIO_SUPERFICIE")
	private BigDecimal superficie;
	
	@Column(name = "BIO_COORDONNEES")
	private Coordonnees localisation;
	
	@OneToOne
	@JoinColumn(name = "BIO_COORDONEES_ID")
	private Coordonnees coordonees;

	public Biome() {
	}

	public Biome(String type, BigDecimal superficie, Coordonnees localisation) {
		this.type = type;
		this.superficie = superficie;
		this.localisation = localisation;
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

	public BigDecimal getSuperficie() {
		return superficie;
	}

	public void setSuperficie(BigDecimal superficie) {
		this.superficie = superficie;
	}

	public Coordonnees getLocalisation() {
		return localisation;
	}

	public void setLocalisation(Coordonnees localisation) {
		this.localisation = localisation;
	}
}
