package fr.formation.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.formation.dao.IBiomeDao;
import fr.formation.model.Biome;
import fr.formation.model.Coordonnees;

@Service
@Transactional
public class BiomeService {

	@Autowired
	private IBiomeDao daoBiome;
	
	public Biome findBiomeContaining(Coordonnees c) {
		
		for( Biome b : daoBiome.findAll()) {
			Coordonnees coordBiome = b.getCoordonnees();
			if(coordBiome.getX() <= c.getX() 
			&& coordBiome.getY() <= c.getY()) {
				if(coordBiome.getX() + b.getLongueur() >= c.getX() 
				&& coordBiome.getY() + b.getLongueur() >= c.getY()) {
					return b;
				}
			}
		}
		
		return null;
	}
}
