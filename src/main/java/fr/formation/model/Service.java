package fr.formation.model;

import java.math.BigDecimal;
import java.time.LocalTime;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table (name = "service")
public class Service extends Workplace{

	private BigDecimal prixEntree;
	
	private List<Personne> clients;

	
	public Service(){
		
	}
	public Service(Adresse adresse, BigDecimal superficie, Biome biome, LocalTime heureOuverture,
			LocalTime heureFermeture, BigDecimal prixEntree) {
		super(adresse, superficie, biome, heureOuverture, heureFermeture);
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
