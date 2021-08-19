package fr.formation.service;

import java.math.BigDecimal;
import java.time.LocalTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.formation.dao.IPersonneDao;
import fr.formation.dao.IServiceDao;
import fr.formation.model.Personne;

@Service
public class PersonneService {
	@Autowired
	private IPersonneDao daoPersonne;
	
	@Autowired
	private IServiceDao daoService;
	
	@Autowired
	private ServiceService serviceService;

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
	
	public void chercherService(int idPersonne){
		LocalTime time = LocalTime.of(6,0); // A remplacer par l heure de la simulation
		Personne personne = daoPersonne.findById(idPersonne).get();
		List<fr.formation.model.Service> services = daoService.findAll();
		for (fr.formation.model.Service s: services){
			if (time.isAfter(s.getHeureOuverture()) && time.isBefore(s.getHeureFermeture())){
				if (personne.getArgent().compareTo(s.getPrixEntree()) > 0){
					personne.setCoordonnees(s.getAdresse().getCoordonnees());
					serviceService.ajoutClient(s.getId(), personne);
					daoPersonne.save(personne);
					daoService.save(s);
					break;
				}
			}
		}
	}
}
