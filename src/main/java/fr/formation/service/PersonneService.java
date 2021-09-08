package fr.formation.service;

import java.math.BigDecimal;
import java.time.LocalTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.formation.dao.IPersonneDao;
import fr.formation.dao.IPrestationDao;
import fr.formation.model.Personne;
import fr.formation.model.Prestation;

@Service
public class PersonneService {
	@Autowired
	private IPersonneDao daoPersonne;
	
	@Autowired
	private IPrestationDao daoPrestation;
	
	@Autowired
	private PrestationService prestationService;

	public Boolean payer(int idPersonne, BigDecimal somme) {
		Personne personne = daoPersonne.findById(idPersonne).get();
		if(personne.getArgent().compareTo(somme) >= 0) {
			personne.setArgent(personne.getArgent().subtract(somme));
			daoPersonne.save(personne);
			return true;
		}
		daoPersonne.save(personne);
		return false;
	}
	
	public void gagnerArgent(int idPersonne, BigDecimal somme) {
		Personne personne = daoPersonne.findById(idPersonne).get();
		personne.setArgent(personne.getArgent().add(somme));
		daoPersonne.save(personne);
	}
	
	public void chercherPrestation(int idPersonne, LocalTime time){
		Personne personne = daoPersonne.findById(idPersonne).get();
		List<Prestation> services = daoPrestation.findAll();
		for (Prestation s: services){
			if (time.isAfter(s.getHeureOuverture()) && time.isBefore(s.getHeureFermeture())){
				if (personne.getArgent().compareTo(s.getPrixEntree()) > 0){
					personne.setCoordonnees(s.getCoordonnees());
					prestationService.ajoutClient(s.getId(), personne);
					daoPersonne.save(personne);
					daoPrestation.save(s);
					break;
				}
			}
		}
	}

}
