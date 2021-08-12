package fr.formation.model;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "metier")
public class Metier {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "MET_ID")
	private int id;

	@Column(name = "MET_POSTE")
	private String poste;

	@Column(name = "MET_SALAIRE")
	private BigDecimal salaire;

	@ManyToMany(mappedBy = "metiers")
	private List<Workplace> workplaces;

	@OneToMany(mappedBy = "metier")
	private List<Personne> employes;

	public Metier() {
	}

	public Metier(String poste, BigDecimal salaire) {
		this.poste = poste;
		this.salaire = salaire;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPoste() {
		return poste;
	}

	public void setPoste(String poste) {
		this.poste = poste;
	}

	public BigDecimal getSalaire() {
		return salaire;
	}

	public void setSalaire(BigDecimal salaire) {
		this.salaire = salaire;
	}

	public List<Workplace> getWorkplaces() {
		return workplaces;
	}

	public void setWorkplaces(List<Workplace> workplaces) {
		this.workplaces = workplaces;
	}

	public List<Personne> getEmployes() {
		return employes;
	}

	public void setEmployes(List<Personne> employes) {
		this.employes = employes;
	}

}
