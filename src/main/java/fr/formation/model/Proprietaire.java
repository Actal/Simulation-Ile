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

	public void payerEmployes() {
		for (Batiment b : batiments) {
			if (b instanceof Workplace) {
				for (Poste p : ((Workplace) b).getPostes()) {
					if (p.getCitoyen() != null) {
						payer(p.getSalaire());
						p.getCitoyen().gagnerArgent(p.getSalaire());
					}
				}
			}
		}
	}

	public void percevoirBenefice() {
		for (Batiment b : batiments) {
			this.gagnerArgent(b.valeurBenefice());
		}
	}

//	public void entretenirBatiments() {
//		for (Batiment b : batiments) {
//			this.payer(b.valeurEntretien());
//		}
//	}

//	public void percevoirLoyer() {
//		for (Batiment b : batiments) {
//			if (b instanceof Habitation) {
//				this.gagnerArgent(((Habitation) b).recolterLoyer());
//			}
//		}
//	}

	Proprietaire() {
	}

	public List<Batiment> getBatiments() {
		return batiments;
	}

	public void setBatiments(List<Batiment> batiments) {
		this.batiments = batiments;
	}
}
