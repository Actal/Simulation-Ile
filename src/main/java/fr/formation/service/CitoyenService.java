package fr.formation.service;

import java.time.LocalTime;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.formation.dao.ICitoyenDao;
import fr.formation.dao.IHabitationDao;
import fr.formation.dao.IPosteDao;
import fr.formation.dao.IWorkplaceDao;
import fr.formation.model.Citoyen;
import fr.formation.model.Habitation;
import fr.formation.model.Poste;
import fr.formation.model.Workplace;

@Service
@Transactional
public class CitoyenService extends PersonneService {
	@Autowired
	private ICitoyenDao daoCitoyen;

	@Autowired
	private IHabitationDao daoHabitation;

	@Autowired
	private IPosteDao daoPoste;

	@Autowired
	private IWorkplaceDao daoWorkplace;

	@Autowired
	private HabitationService habitationService;

	public void allerTravailler(int idCitoyen) {
		Citoyen citoyen = daoCitoyen.findById(idCitoyen).get();
		if (this.isTravailleur(idCitoyen)) {
			citoyen.setCoordonnees(citoyen.getPoste().getWorkplace().getCoordonnees());
			daoCitoyen.save(citoyen);
		}
	}

	public void rentrer(int idCitoyen) {
		Citoyen citoyen = daoCitoyen.findById(idCitoyen).get();
		if (this.isLocataire(idCitoyen)) {
			citoyen.setCoordonnees(citoyen.getHabitation().getCoordonnees());
			daoCitoyen.save(citoyen);
		}
	}

	public Boolean isTravailleur(int idCitoyen) {
		Citoyen citoyen = daoCitoyen.findById(idCitoyen).get();
		return (citoyen.getPoste() != null);
	}

	public Boolean isLocataire(int idCitoyen) {
		Citoyen citoyen = daoCitoyen.findById(idCitoyen).get();
		return (citoyen.getHabitation() != null);
	}

	public void chercherLogement(int idCitoyen) {
		Citoyen citoyen = daoCitoyen.findById(idCitoyen).get();
		List<Habitation> habitations = daoHabitation.findAll();

		for (Habitation h : habitations) {
			if (citoyen.getArgent().compareTo(h.getLoyer()) > 0) {
				if(habitationService.ajouterHabitant(h.getId(), citoyen)) {
					break;
				}
			}
		}
	}

	public void chercherTravail(int idCitoyen) {
		Citoyen citoyen = daoCitoyen.findById(idCitoyen).get();
		List<Workplace> workplaces = daoWorkplace.findAll();
		boolean aTrouve = false;

		for (Workplace w : workplaces) {
			for (Poste p : w.getPostes()) {
				if (p.getCitoyen() == null) {
					citoyen.setPoste(p);
					p.setCitoyen(citoyen);
					daoCitoyen.save(citoyen);
					daoPoste.save(p);
					aTrouve = true;
					break;
				}
			}
			if (aTrouve == true) {
				daoWorkplace.save(w);
				break;
			}
		}
	}

	//Simule toutes les heures donc on travaille sur les heures
	public void faireAction(int idCitoyen, LocalTime time){
		// Definit les horraires d action
		Citoyen citoyen = daoCitoyen.findById(idCitoyen).get();
		int timeHour = time.getHour();
		int workStart = 8;
		int workStop = 9;
		if (citoyen.getPoste() != null){
			workStart = citoyen.getPoste().getWorkplace().getHeureOuverture().getHour();
			workStop = citoyen.getPoste().getWorkplace().getHeureFermeture().getHour();
		}
		int sleepStart = workStart - 8;

		//Actions ponctuelles
		if (timeHour == workStart){
			allerTravailler(idCitoyen);
		}
		else if (timeHour == sleepStart){
			rentrer(idCitoyen);
		}
		else if (timeHour == workStop){
			if (citoyen.getPoste() == null){
				chercherTravail(idCitoyen);
			}
			if (citoyen.getHabitation() == null){
				chercherLogement(idCitoyen);
			}
		}
		//Si ne travaille pas et ne dort pas
		else if (timeHour > workStop && timeHour < sleepStart){
			chercherPrestation(idCitoyen, time);
		}
	}
}
