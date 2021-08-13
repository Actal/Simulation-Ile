package fr.formation.model;

import java.math.BigDecimal;
import java.time.LocalTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
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

	@OneToMany(mappedBy = "workplace")
	private List<Poste> postes;
	
	public void ouvrir() {
		isOuvert = true;
	}
	
	public void fermer() {
		isOuvert = false;
	}
	
	public void ajouterPoste(Poste poste){
		postes.add(poste);
	}
	public void supprimerPoste(Poste poste){
		postes.remove(poste);
	}
	
	public BigDecimal valeurRecette() {
		BigDecimal coeffRecettePoste = new BigDecimal(2);
		BigDecimal recetteTotale = new BigDecimal(0);
		for (Poste p: postes){
			BigDecimal salaire = p.getSalaire();
			recetteTotale = recetteTotale.add(salaire.multiply(coeffRecettePoste));
		}
	return recetteTotale;
	}
	public BigDecimal valeurEntretien(){
		BigDecimal nbPostes = new BigDecimal(postes.size());
		BigDecimal coeffEntretienEmployes = new BigDecimal(0.01);
		BigDecimal entretienTotal = this.getCoutEntretienBase().multiply(nbPostes).multiply(coeffEntretienEmployes);
		return entretienTotal;
	}
	public BigDecimal valeurBenefice(){
		BigDecimal entretien = valeurEntretien();
		BigDecimal recette = valeurRecette();
		BigDecimal benefice = recette.subtract(entretien);
		return benefice;
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

	

	public Workplace(BigDecimal superficie, String nom, BigDecimal prix, BigDecimal coutEntretienBase, int nbPlace,
			Adresse adresse, Biome biome, Proprietaire proprietaire, LocalTime heureOuverture,
			LocalTime heureFermeture) {
		super(superficie, nom, prix, coutEntretienBase, nbPlace, adresse, biome, proprietaire);
		this.heureOuverture = heureOuverture;
		this.heureFermeture = heureFermeture;
		this.isOuvert = false;
	}

	public Workplace() {
	}
	
}
