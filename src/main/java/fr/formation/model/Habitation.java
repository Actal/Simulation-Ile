package fr.formation.model;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "habitation")
public class Habitation extends Batiment {

	@Column(name = "HAB_NB_PLACE", nullable = false)
	private int nbPlace;
	
	@Column(name = "HAB_LOYER", nullable = false)
	private BigDecimal loyer;
	
	@OneToMany(mappedBy = "habitation")
	private List<Personne> habitants;

	public int getNbPlace() {
		return nbPlace;
	}

	public void setNbPlace(int nbPlace) {
		this.nbPlace = nbPlace;
	}

	public BigDecimal getLoyer() {
		return loyer;
	}

	public void setLoyer(BigDecimal loyer) {
		this.loyer = loyer;
	}

	public List<Personne> getHabitants() {
		return habitants;
	}

	public void setHabitants(List<Personne> habitants) {
		this.habitants = habitants;
	}

	public Habitation(Adresse adresse, BigDecimal superficie, Biome biome, int nbPlace, BigDecimal loyer,
			List<Personne> habitants) {
		super(adresse, superficie, biome);
		this.nbPlace = nbPlace;
		this.loyer = loyer;
		this.habitants = habitants;
	}

	public Habitation() {
	}
	
	
}
