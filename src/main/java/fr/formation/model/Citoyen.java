package fr.formation.model;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "citoyen")
public class Citoyen extends Personne {

	@OneToOne
	@JoinColumn(name = "CIT_POSTE_ID")
	private Poste poste;
	
	@ManyToOne
	@JoinColumn(name = "CIT_HABITATION_ID")
	private Habitation habitation;
	
	public void payerLoyer() {
		this.setArgent(this.getArgent().subtract(this.getHabitation().getLoyer()));
	}
	
	public void allerTravailler() {
		this.setCoordonnees(this.getPoste().getWorkplace().getAdresse().getCoordonnees());
	}
	
	public void rentrer() {
		this.setCoordonnees(this.getHabitation().getAdresse().getCoordonnees());
	}

	public Boolean isTravailleur() {
		return this.getPoste() != null;
	}
	
	public Boolean isLocataire() {
		return this.getHabitation() != null;
	}
	
	public Poste getPoste() {
		return poste;
	}

	public void setPoste(Poste poste) {
		this.poste = poste;
	}

	public Habitation getHabitation() {
		return habitation;
	}

	public void setHabitation(Habitation habitation) {
		this.habitation = habitation;
	}
	
	
}
