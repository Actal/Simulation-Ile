package fr.formation.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "commande_detail")
public class CommandeDetail {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "DET_ID")
	private int id;

	@Column(name = "DET_PRIX_UNITAIRE", precision = 10, scale = 2, nullable = false)
	private BigDecimal prixUnitaire;

	@Column(name = "DET_QUANTITE")
	private int quantite; //TODO: demo null value

	@ManyToOne
	@JoinColumn(name = "DET_COMMANDE_ID", nullable = false)
	private Commande commande;

	@ManyToOne
	@JoinColumn(name = "DET_PRODUIT_ID", nullable = false)
	private Produit produit;

	public CommandeDetail() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Commande getCommande() {
		return commande;
	}

	public void setCommande(Commande commande) {
		this.commande = commande;
	}

	public Produit getProduit() {
		return produit;
	}

	public void setProduit(Produit produit) {
		this.produit = produit;
	}

	public BigDecimal getPrixUnitaire() {
		return prixUnitaire;
	}

	public void setPrixUnitaire(BigDecimal prixUnitaire) {
		this.prixUnitaire = prixUnitaire;
	}

	public int getQuantite() {
		return quantite;
	}

	public void setQuantite(int quantite) {
		this.quantite = quantite;
	}
}
