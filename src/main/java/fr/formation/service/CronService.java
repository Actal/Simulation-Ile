package fr.formation.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import fr.formation.dao.IProprietaireDao;
import fr.formation.model.Proprietaire;

@Service
public class CronService {
	@Autowired
	private IProprietaireDao daoProprietaire;
	
	@Autowired
	private ProprietaireService proprietaireService;
	
	//Toutes les minutes, pour simuler une heure
	@Scheduled(fixedRate = 1000*60) //Scheduled : il faut activer cette annotation dans la configuration
	public void heureCron() {
		/*
		 * Action personne
		 * */
		System.out.println("Coucou CRON heure");
	}
	
	//Simule un mois, appele des fonctions de fin de mois comme payer loyer
	@Scheduled(fixedRate = 1000*60*24*7) //Scheduled : il faut activer cette annotation dans la configuration
	public void moisCron() {
		System.out.println("Coucou CRON mois");

		/*List<Proprietaire> proprietaires = daoProprietaire.findAll();
		for (Proprietaire p: proprietaires){
			proprietaireService.payerEmployes(p.getId());
			proprietaireService.percevoirBenefice(p.getId());
		}*/
		
	}
}