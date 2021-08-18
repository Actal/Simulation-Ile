package fr.formation.model;

import java.math.BigDecimal;
import java.time.LocalDate;
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

	public Proprietaire() {
	}

	public Proprietaire(String nom, String prenom, LocalDate dateNaissance, Sexe sexe, BigDecimal argent) {
		super(nom, prenom, dateNaissance, sexe, argent);
	}

	public List<Batiment> getBatiments() {
		return batiments;
	}

	public void setBatiments(List<Batiment> batiments) {
		this.batiments = batiments;
	}
}
