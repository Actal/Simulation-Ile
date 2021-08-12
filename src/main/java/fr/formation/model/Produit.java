package fr.formation.model;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity // Annotation obligatoire pour chaque classe d'entite, que vous voulez persister
		// en BDD
@Table(name = "produit") // Optionnel, permet d'indiquer a JPA le nom de la table, et eventuellement
							// d'autres infos
public class Produit {
	@Id // Obligatoire, pour identifier l'attribut qui joue le role de cle primaire
	@GeneratedValue(strategy = GenerationType.IDENTITY) // Indique une generation AUTO_INCREMENT
	@Column(name = "PRO_ID") // Optionnel, permet d'indiquer a JPA le nom de la colonne, et eventuellement
								// d'autres infos
	private int id;

	@Column(name = "PRO_LIBELLE", length = 100, nullable = false)
	private String libelle;

	@Column(name = "PRO_PRIX", precision = 10, scale = 2)
	private BigDecimal prix;

//	private int fournisseurId;
	// Relation primaire, parce que c'est ici qu'on configure la cle etrangere
	@ManyToOne // Obligatoire sur toutes les relations
	@JoinColumn(name = "PRO_FOURNISSEUR_ID") // Optionnel, comme @Column
	private Fournisseur fournisseur;

//	private List<CategorieProduit> categories;

	@ManyToMany
	@JoinTable( // Optionnel, mais qui permet de donner le nom de la table de jointure et de ses
				// colonnes
			name = "cat_pro", // le nom de la table de jointure
			joinColumns = @JoinColumn(name = "IDPRODUIT", referencedColumnName = "PRO_ID"),
			// le nom de la colonne de jointure pour Produit
			inverseJoinColumns = @JoinColumn(name = "IDCATEGORIE", referencedColumnName = "CAT_ID")
	// le nom de la colonne de jointure pour Categorie
//			, uniqueConstraints = @UniqueConstraint(columnNames = { "IDPRODUIT", "IDCATEGORIE" })
	// si on veut forcer le fait de n'avoir qu'une seule fois la paire
	// d'enregistrement IDPRODUIT + IDCATEGORIE
	)
	private List<Categorie> categories;

	@OneToMany(mappedBy = "produit")
	private List<CommandeDetail> details;

	@ManyToMany
	@JoinTable(
			name = "attr_pro",
			joinColumns = @JoinColumn(
				name = "IDPRODUIT",
				referencedColumnName = "PRO_ID"),
			inverseJoinColumns = @JoinColumn(name = "IDATTRIBUT",
				referencedColumnName = "ATTR_ID"),
			uniqueConstraints = @UniqueConstraint(columnNames = { "IDPRODUIT", "IDATTRIBUT" })
	)
	private List<Attribut> attributs;

	// POJO : constructeur sans argument necessaire si un autre existe
	public Produit() {

	}

	public Produit(String libelle, BigDecimal prix) {
		this.libelle = libelle;
		this.prix = prix;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	public BigDecimal getPrix() {
		return prix;
	}

	public void setPrix(BigDecimal prix) {
		this.prix = prix;
	}

	public Fournisseur getFournisseur() {
		return fournisseur;
	}

	public void setFournisseur(Fournisseur fournisseur) {
		this.fournisseur = fournisseur;
	}

	public List<Categorie> getCategories() {
		return categories;
	}

	public void setCategories(List<Categorie> categories) {
		this.categories = categories;
	}

	public List<CommandeDetail> getDetails() {
		return details;
	}

	public void setDetails(List<CommandeDetail> details) {
		this.details = details;
	}

	public List<Attribut> getAttributs() {
		return attributs;
	}

	public void setAttributs(List<Attribut> attributs) {
		this.attributs = attributs;
	}

}
