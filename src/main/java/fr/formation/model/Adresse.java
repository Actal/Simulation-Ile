package fr.formation.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "adresse")
public class Adresse {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ADR_ID")
	private int id;
	
	@Column(name = "ADR_RUE", length = 150,  nullable = false)
	private String rue;
	
	@Column(name = "ADR_NUMERO", nullable = false)
	private int numero;
	
	@OneToOne(mappedBy = "adresse")
	private Batiment batiment;
	
	public Adresse() {
	}

	public Adresse(String rue, int numero) {
		this.rue = rue;
		this.numero = numero;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getRue() {
		return rue;
	}

	public void setRue(String rue) {
		this.rue = rue;
	}

	public int getNum() {
		return numero;
	}

	public void setNum(int numero) {
		this.numero = numero;
	}

	public Batiment getBatiment() {
		return batiment;
	}

	public void setBatiment(Batiment batiment) {
		this.batiment = batiment;
	}
	
	

}
