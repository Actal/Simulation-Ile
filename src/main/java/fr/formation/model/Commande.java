package fr.formation.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "commande")
public class Commande {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "CMD_ID")
	private int id;
	
	@Column(name = "CMD_DATE", nullable = false)
	private LocalDateTime date;
	
	@Column(name = "CMD_PRIX_TOTAL", precision = 10, scale = 2, nullable = false)
	private BigDecimal prixTotal;
	
	@ManyToOne
	@JoinColumn(name = "CMD_CLIENT_ID")
	private Client client;

	@Column(name = "CMD_ETAT")
	@Enumerated(EnumType.STRING) // Stocker la valeur sous forme de chaine de caracteres
//	@Enumerated(EnumType.ORDINAL) // Stocker la valeur sous forme d'entier
	private CommandeEtat etat;
	
	@OneToMany(mappedBy = "commande")
	private List<CommandeDetail> details;
	
	public Commande() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public LocalDateTime getDate() {
		return date;
	}

	public void setDate(LocalDateTime date) {
		this.date = date;
	}

	public BigDecimal getPrixTotal() {
		return prixTotal;
	}

	public void setPrixTotal(BigDecimal prixTotal) {
		this.prixTotal = prixTotal;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public CommandeEtat getEtat() {
		return etat;
	}

	public void setEtat(CommandeEtat etat) {
		this.etat = etat;
	}

	public List<CommandeDetail> getDetails() {
		return details;
	}

	public void setDetails(List<CommandeDetail> details) {
		this.details = details;
	}

}
