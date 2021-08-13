package fr.formation.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "citoyen")
@Inheritance(strategy = InheritanceType.JOINED)
@PrimaryKeyJoinColumn(name = "CIT_ID", referencedColumnName = "PER_ID")
public class Citoyen extends Personne {

	@OneToOne
	@JoinColumn(name = "CIT_POSTE_ID")
	private Poste poste;
	
	@ManyToOne
	@JoinColumn(name = "CIT_HABITATION_ID")
	private Habitation habitation;
	
	public Citoyen() {
		super();
	}
	
	public Citoyen(String nom, String prenom, LocalDate dateNaissance, Sexe sexe, BigDecimal argent) {
		super(nom, prenom, dateNaissance, sexe, argent);
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
