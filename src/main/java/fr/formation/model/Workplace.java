package fr.formation.model;

import java.math.BigDecimal;
import java.time.LocalTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "workplace")
@PrimaryKeyJoinColumn(name="WOR_ID", referencedColumnName="BAT_ID")
public class Workplace extends Batiment {

	@ManyToMany
	@JoinTable(
			name = "wor_met", // nom table de correspondance
			joinColumns = @JoinColumn(name="ID_WORKPLACE", referencedColumnName = "BAT_ID"),
			inverseJoinColumns = @JoinColumn(name = "ID_METIER", referencedColumnName = "MET_ID")
	)
	private List<Metier> metiers;
	
	@Column(name = "WOR_HEURE_OUVERTURE")
	private LocalTime heureOuverture;
	
	@Column(name = "WOR_HEURE_FERMETURE")
	private LocalTime heureFermeture;

	public List<Metier> getMetiers() {
		return metiers;
	}

	public void setMetiers(List<Metier> metiers) {
		this.metiers = metiers;
	}

	public LocalTime getHeureOuverture() {
		return heureOuverture;
	}

	public void setHeureOuverture(LocalTime heureOuverture) {
		this.heureOuverture = heureOuverture;
	}

	public LocalTime getHeureFermeture() {
		return heureFermeture;
	}

	public void setHeureFermeture(LocalTime heureFermeture) {
		this.heureFermeture = heureFermeture;
	}

	public Workplace(Adresse adresse, BigDecimal superficie, Biome biome, List<Metier> metiers,
			LocalTime heureOuverture, LocalTime heureFermeture) {
		super(adresse, superficie, biome);
		this.metiers = metiers;
		this.heureOuverture = heureOuverture;
		this.heureFermeture = heureFermeture;
	}

	public Workplace() {
	}
	
	
}
