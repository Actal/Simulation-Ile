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

	public void ajouterHabitant(Citoyen nvHabitant) {
		int nbHab = habitants.size();
		if (!habitants.contains(nvHabitant)) {
			if (nbHab < this.getNbPlace()) {
				habitants.add(nvHabitant);
				nvHabitant.setHabitation(this);
			} else
				System.out.println("Habitation pleine ! Pas d'ajout d'habitant");
		} else
			System.out.println("L'habitation contient deja cet habitant !");
	}

	public BigDecimal valeurEntretien() {
		BigDecimal entretient = new BigDecimal(0);
		BigDecimal entretientParHabitant = new BigDecimal(0.01); // entretient pour chaque habitant en pourcentage de l'entretient de base
		
		entretient = entretient.add(this.getCoutEntretienBase());
		entretient = entretient.add(entretientParHabitant.multiply(this.getCoutEntretienBase()));
		
		return entretient;
	}

	public BigDecimal valeurBenefice() {
		return this.recolterLoyer().subtract(this.valeurEntretien());
	}

	public BigDecimal recolterLoyer() {
		BigDecimal somme = new BigDecimal(0);
		for (Citoyen c : habitants) {
			boolean aPaye = c.payer(loyer);
			if (aPaye) {
				somme.add(loyer);
			}
		}
		return somme;
	}

	public void supprimerHabitant(Citoyen habitant) {
		habitants.remove(habitant);
		habitant.setHabitation(null);
	}

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

	public Habitation(BigDecimal superficie, String nom, BigDecimal prix, BigDecimal coutEntretienBase, int nbPlace,
			Adresse adresse, Biome biome, Proprietaire proprietaire, BigDecimal loyer) {
		super(superficie, nom, prix, coutEntretienBase, nbPlace, adresse, biome, proprietaire);
		this.loyer = loyer;
	}

	
}
