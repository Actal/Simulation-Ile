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
@Table(name = "poste")
public class Poste {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "POS_ID")
	private int id;
	
	@Column (name = "POS_SALAIRE")
	private BigDecimal salaire;
	
	@OneToOne
	@JoinColumn(name = "POS_METIER_ID", nullable = false)
	private Metier metier;
	
	@OneToOne
	@JoinColumn(name = "POS_WORKPLACE_ID", nullable = false)
	private Workplace workplace;
	
	@OneToOne
	@JoinColumn(name = "POS_PERSONNE_ID")
	private Personne personne;

	public Poste(){
		
	}
	public Poste(BigDecimal salaire) {
		this.salaire = salaire;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public BigDecimal getSalaire() {
		return salaire;
	}
	public void setSalaire(BigDecimal salaire) {
		this.salaire = salaire;
	}
	
	public Metier getMetier() {
		return metier;
	}
	public void setMetier(Metier metier) {
		this.metier = metier;
	}
	
	public Workplace getWorkplace() {
		return workplace;
	}
	public void setWorkplace(Workplace workplace) {
		this.workplace = workplace;
	}
	
	public Personne getPersonne() {
		return personne;
	}
	public void setPersonne(Personne personne) {
		this.personne = personne;
	}
	
	
}
