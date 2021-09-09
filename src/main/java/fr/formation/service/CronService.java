package fr.formation.service;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import fr.formation.dao.ICitoyenDao;
import fr.formation.dao.IProprietaireDao;
import fr.formation.model.Citoyen;
import fr.formation.model.SimulationEtat;

@Service
public class CronService {
	@Autowired
	private IProprietaireDao daoProprietaire;

	@Autowired
	private ProprietaireService proprietaireService;

	@Autowired
	private ICitoyenDao daoCitoyen;

	@Autowired
	private CitoyenService citoyenService;

	@Autowired
	SimulationEtat simulationEtat;

	// Toutes les 6 secondes, pour simuler une heure
	@Scheduled(fixedRate = 1000 * 6) // Scheduled : il faut activer cette annotation dans la configuration
	public void heureCron() {
		System.out.println("Coucou CRON heure");

		localTime time = simulationEtat.getTime().toLocalTime();

		for (Citoyen f : daoCitoyen.findAll()) {
			citoyenService.faireAction(f.getId(), time);
		}

		simulationEtat.getTime().plusHours(1);
	}

	// Simule une semaine, appele des fonctions de fin de semaine comme payer loyer
	@Scheduled(fixedRate = 1000 * 6 * 24 * 7) // Scheduled : il faut activer cette annotation dans la configuration
	public void semaineCron() {
		System.out.println("Coucou CRON semaine");

		List<Proprietaire> proprietaires = daoProprietaire.findAll(); for
		(Proprietaire p: proprietaires){
			proprietaireService.payerEmployes(p.getId());
			proprietaireService.percevoirBenefice(p.getId());
		}
		 

		LocalDate date = simulationEtat.getTime().toLocalDate();

		// BigDecimal[] desValues = new BigDecimal[3];
		// desValues[0] = new BigDecimal(40000);
		// desValues[1] = new BigDecimal(50000);
		// desValues[2] = new BigDecimal(90000);

		EditCsvService editCsv = new EditCsvService();
		editCsv.write(uneDate, desValues);

	}
}