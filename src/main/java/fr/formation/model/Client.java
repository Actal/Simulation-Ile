package fr.formation.model;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "client")
public class Client {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "CLI_ID")
	private int id;

	@Column(name = "CLI_NOM", length = 50, nullable = false)
	private String nom;

	@Column(name = "CLI_PRENOM", length = 50, nullable = false)
	private String prenom;

	@Column(name = "CLI_DATE_NAISSANCE")
	private LocalDate dateNaissance;
	
	// Si utilisation des dates Date ou Calendar au lieu de LocalDate, LocalDateTime
	// ou LocalTime
//	@Column(name = "CLI_DATE_NAISSANCE")
//	@Temporal(TemporalType.DATE)
//	private Date dateNaissance;
	
	@OneToMany(mappedBy = "client")
	private List<Commande> commandes;

//	@Column(name = "CLI_ADRESSE", length = 150)
//	private String adresse;
//
//	@Column(name = "CLI_CODE_POSTAL", length = 5)
//	private String cp;
//
//	@Column(name = "CLI_VILLE", length = 100)
//	private String ville;

	// Relation primaire
	@OneToOne //(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "CLI_ADRESSE_ID")
	private Adresse adresse;
	
	public Client() {
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

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public LocalDate getDateNaissance() {
		return dateNaissance;
	}

	public void setDateNaissance(LocalDate dateNaissance) {
		this.dateNaissance = dateNaissance;
	}

//	public String getAdresse() {
//		return adresse;
//	}
//
//	public void setAdresse(String adresse) {
//		this.adresse = adresse;
//	}
//
//	public String getCp() {
//		return cp;
//	}
//
//	public void setCp(String cp) {
//		this.cp = cp;
//	}
//
//	public String getVille() {
//		return ville;
//	}
//
//	public void setVille(String ville) {
//		this.ville = ville;
//	}
	
	public Adresse getAdresse() {
		return adresse;
	}

	public void setAdresse(Adresse adresse) {
		this.adresse = adresse;
	}

	public List<Commande> getCommandes() {
		return commandes;
	}

	public void setCommandes(List<Commande> commandes) {
		this.commandes = commandes;
	}

}
