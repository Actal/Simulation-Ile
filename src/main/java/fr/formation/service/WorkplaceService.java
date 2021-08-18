package fr.formation.service;

import java.math.BigDecimal;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.formation.dao.IPosteDao;
import fr.formation.dao.IWorkplaceDao;
import fr.formation.model.Poste;
import fr.formation.model.Workplace;

@Service
public class WorkplaceService {

	@Autowired
	private IWorkplaceDao daoWorplace;
	
	@Autowired
	private IPosteDao daoPoste;

	@Transactional
	public void ouvrir(int id) {
		Workplace w = daoWorplace.findById(id).get();
		w.setOuvert(true);
		daoWorplace.save(w);
	}

	@Transactional
	public void fermer(int id) {
		Workplace w = daoWorplace.findById(id).get();
		w.setOuvert(false);
		daoWorplace.save(w);
	}

	@Transactional
	public void ajouterPoste(int id, Poste poste) {
		Workplace w = daoWorplace.findById(id).get();
		w.getPostes().add(poste);
		poste.setWorkplace(w);

		daoPoste.save(poste);
		daoWorplace.save(w);
	}

	@Transactional
	public void supprimerPoste(int id, Poste poste) {
		Workplace w = daoWorplace.findById(id).get();
		w.getPostes().remove(poste);
		poste.setWorkplace(null);

		daoPoste.save(poste);
		daoWorplace.save(w);
	}

	@Transactional
	public BigDecimal valeurRecette(int id) {
		Workplace w = daoWorplace.findById(id).get();
		BigDecimal coeffRecettePoste = new BigDecimal(2);
		BigDecimal recetteTotale = new BigDecimal(0);
		for (Poste p : w.getPostes()) {
			recetteTotale = recetteTotale.add(p.getSalaire().multiply(coeffRecettePoste));
		}
		return recetteTotale;
	}

	@Transactional
	public BigDecimal valeurEntretien(int id) {
		Workplace w = daoWorplace.findById(id).get();
		BigDecimal nbPostes = new BigDecimal(w.getPostes().size());
		BigDecimal coeffEntretienEmployes = new BigDecimal(0.01);
		BigDecimal entretienTotal = w.getCoutEntretienBase().multiply(nbPostes).multiply(coeffEntretienEmployes);
		return entretienTotal;
	}

	public BigDecimal valeurBenefice(int id) {
		return this.valeurRecette(id).subtract(this.valeurEntretien(id));
	}

}
