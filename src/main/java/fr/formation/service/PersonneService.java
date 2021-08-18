package fr.formation.service;

import java.math.BigDecimal;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.formation.dao.IPersonneDao;
import fr.formation.model.Personne;

@Service
@Transactional
public class PersonneService {
	@Autowired
	private IPersonneDao daoPersonne;

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
}
