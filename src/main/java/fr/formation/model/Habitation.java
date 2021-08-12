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
}
