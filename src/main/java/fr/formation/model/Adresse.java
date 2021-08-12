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
	
	@Column(name = "ADR_CP", length = 5, nullable = false)
	private String cp;
	
	@Column(name = "ADR_VILLE", length = 50, nullable = false)
	private String ville;
	
	// Relation secondaire
	@OneToOne(mappedBy = "adresse")
	private Client client;
	
	public Adresse() {
	}

	public Adresse(String rue, String cp, String ville) {
		this.rue = rue;
		this.cp = cp;
		this.ville = ville;
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

	public String getCp() {
		return cp;
	}

	public void setCp(String cp) {
		this.cp = cp;
	}

	public String getVille() {
		return ville;
	}

	public void setVille(String ville) {
		this.ville = ville;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

}
