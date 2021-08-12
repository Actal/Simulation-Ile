package fr.formation.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
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

	@OneToMany(mappedBy = "metier")
	private List<Poste> postes;
	
	Metier() {
	}
	
	public Metier(String intitule) {
		this.intitule = intitule;
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
	
	public List<Poste> getPostes() {
		return postes;
	}
	
	public void setPostes(List<Poste> postes) {
		this.postes = postes;
	}

}
