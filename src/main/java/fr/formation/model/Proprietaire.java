package fr.formation.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "proprietaire")
@PrimaryKeyJoinColumn(name = "PRO_ID", referencedColumnName = "CIT_ID")
public class Proprietaire extends Citoyen {

	@OneToMany(mappedBy = "proprietaire")
	private List<Batiment> batiments;

	public void entretenirBatiments() {
		for (Batiment b : batiments) {
			this.payer(b.getCoutEntretien());
		}
	}

	public void payerEmployes() {

	}

	public void percevoirLoyer() {
		for (Batiment b : batiments) {
			if (b instanceof Habitation) {
				this.getArgent().add(((Habitation) b).recolterLoyer());
			}
		}
	}

	Proprietaire() {
	}

	public List<Batiment> getBatiments() {
		return batiments;
	}

	public void setBatiments(List<Batiment> batiments) {
		this.batiments = batiments;
	}
}
