package fr.formation.service;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class CronService {
	//Toutes les minutes, pour simuler une heure
	@Scheduled(fixedRate = 1000*60) //Scheduled : il faut activer cette annotation dans la configuration
	public void heureCron() {
		System.out.println("Coucou CRON minute");
	}
	
	//Simule un mois, appele des fonctions de fin de mois comme payer loyer
	@Scheduled(fixedRate = 1000*60*24*30) //Scheduled : il faut activer cette annotation dans la configuration
	public void moisCron() {
		System.out.println("Coucou CRON mois");
	}
}