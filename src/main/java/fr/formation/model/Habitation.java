package fr.formation.model;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "habitation")
@PrimaryKeyJoinColumn(name = "HAB_ID", referencedColumnName = "BAT_ID")
public class Habitation extends Batiment {
	@Column(name = "HAB_LOYER", nullable = false)
	private BigDecimal loyer;

	@OneToMany(mappedBy = "habitation")
	private List<Citoyen> habitants;

	public BigDecimal getLoyer() {
		return loyer;
	}

	public void setLoyer(BigDecimal loyer) {
		this.loyer = loyer;
	}

	public List<Citoyen> getHabitants() {
		return habitants;
	}

	public void setHabitants(List<Citoyen> habitants) {
		this.habitants = habitants;
	}

	public Habitation() {
	}

	public Habitation(int longueur, String nom, BigDecimal prix, BigDecimal coutEntretienBase, int nbPlace,
			Coordonnees coordonnees, Biome biome, Proprietaire proprietaire, BigDecimal loyer) {
		super(longueur, nom, prix, coutEntretienBase, nbPlace, coordonnees, biome, proprietaire);
		this.loyer = loyer;
	}

	
}
