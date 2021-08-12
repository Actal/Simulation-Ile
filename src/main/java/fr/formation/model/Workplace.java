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
	@Column(name = "WOR_HEURE_OUVERTURE")
	private LocalTime heureOuverture;
	
	@Column(name = "WOR_HEURE_FERMETURE")
	private LocalTime heureFermeture;
	
	@Column(name = "WOR_EST_OUVERT")
	private boolean isOuvert;

	@ManyToMany
	@JoinTable(
			name = "wor_pos", // nom table de correspondance
			joinColumns = @JoinColumn(name="ID_WORKPLACE", referencedColumnName = "WOR_ID"),
			inverseJoinColumns = @JoinColumn(name = "ID_POSTE", referencedColumnName = "POS_ID")
	)
	private List<Poste> postes;
	
	public void ouvrir() {
		isOuvert = true;
	}
	
	public void fermer() {
		isOuvert = false;
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

	public boolean isOuvert() {
		return isOuvert;
	}

	public void setOuvert(boolean isOuvert) {
		this.isOuvert = isOuvert;
	}

	public List<Poste> getPostes() {
		return postes;
	}

	public void setPostes(List<Poste> postes) {
		this.postes = postes;
	}

	public Workplace(Adresse adresse, BigDecimal superficie, Biome biome, LocalTime heureOuverture,
			LocalTime heureFermeture) {
		super(adresse, superficie, biome);
		this.heureOuverture = heureOuverture;
		this.heureFermeture = heureFermeture;
	}

	public Workplace() {
	}
	
}
