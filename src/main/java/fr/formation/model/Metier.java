package fr.formation.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "metier")
public class Metier {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "MET_ID")
	private int id;

	@Column(name = "MET_INTITULE")
	private String intitule;

	@OneToOne(mappedBy = "metier")
	private Poste poste;
	
	Metier() {
	}
	public Metier(String poste, BigDecimal salaire) {
		this.intitule = poste;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getIntitule() {
		return intitule;
	}

	public void setIntitule(String poste) {
		this.intitule = poste;
	}
	
	public Poste getPoste() {
		return poste;
	}
	public void setPoste(Poste poste) {
		this.poste = poste;
	}

}
