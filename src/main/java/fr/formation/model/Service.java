package fr.formation.model;

import java.math.BigDecimal;
import java.time.LocalTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table (name = "service")
public class Service extends Workplace{

	@Column(name = "SER_ID")
	private BigDecimal prixEntree;
	
	@OneToMany(mappedBy ="service")
	private List<Personne> clients;

	
	public Service(){
		
	}
	public Service(String nom, BigDecimal superficie, BigDecimal prix, BigDecimal coutEntretien, int nbPlace,
			LocalTime heureOuverture, LocalTime heureFermeture, BigDecimal prixEntree) {
		super(nom, superficie, prix, coutEntretien, nbPlace, heureOuverture, heureFermeture);
		this.prixEntree = prixEntree;
	}


	public BigDecimal getPrixEntree() {
		return prixEntree;
	}

	public void setPrixEntree(BigDecimal prixEntree) {
		this.prixEntree = prixEntree;
	}

	public List<Personne> getClients() {
		return clients;
	}

	public void setClients(List<Personne> clients) {
		this.clients = clients;
	}
	
	
}
