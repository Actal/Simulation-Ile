package fr.formation.service;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import fr.formation.dao.IProprietaireDao;

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
		// LocalDate uneDate = LocalDate.of(2021, 1, 1);
		// BigDecimal[] desValues = new BigDecimal[3];
		// desValues[0] = new BigDecimal(40000);
		// desValues[1] = new BigDecimal(50000);
		// desValues[2] = new BigDecimal(90000);

		// EditCsvService editCsv = new EditCsvService();
		// editCsv.write(uneDate, desValues);

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