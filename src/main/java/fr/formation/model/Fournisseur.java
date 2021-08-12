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
@Table(name = "fournisseur")
public class Fournisseur {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "FOU_ID")
	private int id;

	@Column(name = "FOU_NOM", length = 50, nullable = false)
	private String nom;

	@Column(name = "FOU_SIRET", length = 14, nullable = false)
	private String siret;

	// Relation secondaire qui n'est pas obligatoire
	@OneToMany(mappedBy = "fournisseur") // Si ajout attribut "produits", devient obligatoire
	// Sur chaque relation, possible de preciser la strategie de chargement
	// -> Lazy Loading (par defaut, sur toute relation de type @...ToMany)
	// -> Eager Loading (par defaut, sur toute relation de type @...ToOne)
	private List<Produit> produits;

	public Fournisseur() {
	}

	public Fournisseur(String nom, String siret) {
		this.nom = nom;
		this.siret = siret;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getSiret() {
		return siret;
	}

	public void setSiret(String siret) {
		this.siret = siret;
	}

	public List<Produit> getProduits() {
		return produits;
	}

	public void setProduits(List<Produit> produits) {
		this.produits = produits;
	}

}
