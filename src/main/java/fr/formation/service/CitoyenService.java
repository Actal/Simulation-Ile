package fr.formation.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.formation.dao.ICitoyenDao;
import fr.formation.model.Citoyen;

@Service
@Transactional
public class CitoyenService extends PersonneService {
	@Autowired
	private ICitoyenDao daoCitoyen;

	public void allerTravailler(int id) {
		Citoyen citoyen = daoCitoyen.findById(id).get();
		citoyen.setCoordonnees(citoyen.getPoste().getWorkplace().getAdresse().getCoordonnees());
		daoCitoyen.save(citoyen);
	}
	
	public void rentrer(int id) {
		Citoyen citoyen = daoCitoyen.findById(id).get();
		citoyen.setCoordonnees(citoyen.getHabitation().getAdresse().getCoordonnees());
		daoCitoyen.save(citoyen);
	}

}
