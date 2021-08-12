package fr.formation.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "[attribut]") // Si le nom est un mot-clé comme attribute, l'encadrer par les crochets []
public class Attribut {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ATTR_ID")
	private int id;

	@Column(name = "ATTR_NOM", length = 20, nullable = false)
	private String nom;

	@Column(name = "ATTR_VAL", length = 50)
	private String valeur;

	@ManyToMany(mappedBy = "attributs")
	private List<Produit> produits;

	public Attribut() {
	}

	public Attribut(String nom, String valeur) {
		this.nom = nom;
		this.valeur = valeur;
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

	public String getValeur() {
		return valeur;
	}

	public void setValeur(String valeur) {
		this.valeur = valeur;
	}

	public List<Produit> getProduits() {
		return produits;
	}

	public void setProduits(List<Produit> produits) {
		this.produits = produits;
	}

}
