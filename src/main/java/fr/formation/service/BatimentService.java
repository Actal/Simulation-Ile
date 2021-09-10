package fr.formation.service;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;

import fr.formation.dao.IBatimentDao;
import fr.formation.model.Batiment;

public abstract class BatimentService {

	@Autowired
	private IBatimentDao daoBatiment;
	
	public abstract BigDecimal valeurEntretien(int id);
	
	public abstract BigDecimal valeurBenefice(int id);
	
	public boolean estConstructible(Batiment batimentAConstruire) {
		
		for( Batiment b : daoBatiment.findAll()) {
			if (b.getId() == batimentAConstruire.getId()) continue;
				if( b.getCoordonnees().getX() + b.getLongueur()< batimentAConstruire.getCoordonnees().getX()) continue;
				if( b.getCoordonnees().getY() + b.getLongueur()< batimentAConstruire.getCoordonnees().getY()) continue;
				if( b.getCoordonnees().getX() > batimentAConstruire.getCoordonnees().getX() + batimentAConstruire.getLongueur()) continue;
				if( b.getCoordonnees().getY() > batimentAConstruire.getCoordonnees().getY() + batimentAConstruire.getLongueur()) continue;
			
			return false;
		}
		
		return true;
	}
}
