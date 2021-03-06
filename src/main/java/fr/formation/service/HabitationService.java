package fr.formation.service;

import java.math.BigDecimal;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.formation.dao.ICitoyenDao;
import fr.formation.dao.IHabitationDao;
import fr.formation.model.Citoyen;
import fr.formation.model.Habitation;

@Service
public class HabitationService extends BatimentService{

	@Autowired
	private IHabitationDao daoHabitation;
	
	@Autowired
	private ICitoyenDao daoCitoyen;
	
	@Autowired
	private CitoyenService citoyenService;
	
	@Transactional
	public boolean ajouterHabitant(int idHabitation, Citoyen nvHabitant) {
		Habitation habitation = daoHabitation.findById(idHabitation).get();
		List<Citoyen> habitants = habitation.getHabitants();
				
		int nbHab = habitants.size();
		if (!habitants.contains(nvHabitant)) {
			if (nbHab < habitation.getNbPlace()) {
				habitants.add(nvHabitant);
				nvHabitant.setHabitation(habitation);
				daoCitoyen.save(nvHabitant);
				daoHabitation.save(habitation);
				return true;
			} // pas de place
		} // citoyen deja locataire
		return false;
	}
	
	public BigDecimal valeurEntretien(int idHabitation) {
		Habitation habitation = daoHabitation.findById(idHabitation).get();
		
		BigDecimal entretient = new BigDecimal(0);
		BigDecimal coeffEntretientParHabitant = new BigDecimal(0.01); // entretient pour chaque habitant en pourcentage de l'entretient de base
		
		BigDecimal nbHabitant = new BigDecimal(habitation.getHabitants().size());
		
		entretient = entretient.add(habitation.getCoutEntretienBase());
		entretient = entretient.add(coeffEntretientParHabitant.multiply(habitation.getCoutEntretienBase().multiply(nbHabitant)));
		
		return entretient;
	}
	
	public BigDecimal valeurBenefice(int idHabitation) {
		Habitation habitation = daoHabitation.findById(idHabitation).get();
		return this.recolterLoyer(habitation.getId()).subtract(valeurEntretien(habitation.getId()));
	}

	public BigDecimal recolterLoyer(int idHabitation) {
		Habitation habitation = daoHabitation.findById(idHabitation).get();
		List<Citoyen> habitants = habitation.getHabitants();
		BigDecimal somme = new BigDecimal(0);
		for (int i=0; i<habitants.size(); i++) {
			boolean aPaye = citoyenService.payer(habitants.get(i).getId(), habitation.getLoyer());
			if (aPaye) {
				somme = somme.add(habitation.getLoyer());
			}
		}
		return somme;
	}

	@Transactional
	public void supprimerHabitant(Citoyen habitant, int idHabitation) {
		Habitation habitation = daoHabitation.findById(idHabitation).get();
		List<Citoyen> habitants = habitation.getHabitants();
		habitants.remove(habitant);
		habitant.setHabitation(null);
		daoCitoyen.save(habitant);
	}
	
}
