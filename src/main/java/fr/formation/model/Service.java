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
	
	@Column(name = "SER_NB_CLI_MENSUEL")
	private int nbClientMensuel;
	
	@OneToMany(mappedBy ="service")
	private List<Personne> clients;

	public void ajoutClient(Personne nouveauClient) {
		if(!clients.contains(nouveauClient)) {
			clients.add(nouveauClient);
			nouveauClient.setService(this);
		}
		nbClientMensuel++;
	}
	
	public void supprimerClient(Personne client) {
		if(client.getService() == this) {
			clients.remove(client);
			client.setService(null);
		}
	}
	
	public BigDecimal valeurRecette() {
		BigDecimal nbClients = new BigDecimal(nbClientMensuel);
		return super.valeurRecette().add(prixEntree.multiply(nbClients));
	}
	
	public BigDecimal valeurEntretien(){
		BigDecimal nbClients = new BigDecimal(nbClientMensuel);
		BigDecimal coeffEntretienClients = new BigDecimal(0.005);
		BigDecimal coutEntretienService = nbClients.multiply(coeffEntretienClients).multiply(getCoutEntretienBase());
		BigDecimal entretienTotal = super.valeurEntretien().add(coutEntretienService);
		return entretienTotal;
	}
	
	public Service(){
		
	}

	public Service(BigDecimal superficie, String nom, BigDecimal prix, BigDecimal coutEntretienBase, int nbPlace,
			Adresse adresse, Biome biome, Proprietaire proprietaire, LocalTime heureOuverture, LocalTime heureFermeture,
			BigDecimal prixEntree, int nbClientMensuel) {
		super(superficie, nom, prix, coutEntretienBase, nbPlace, adresse, biome, proprietaire, heureOuverture,
				heureFermeture);
		this.prixEntree = prixEntree;
		this.nbClientMensuel = nbClientMensuel;
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
