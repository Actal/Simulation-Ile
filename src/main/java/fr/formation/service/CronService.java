package fr.formation.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import fr.formation.dao.ICitoyenDao;
import fr.formation.dao.IPersonneDao;
import fr.formation.dao.IProprietaireDao;
import fr.formation.dao.ISimEtatDao;
import fr.formation.model.Citoyen;
import fr.formation.model.Personne;
import fr.formation.model.Proprietaire;
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
	private IPersonneDao daoPersonne;

	@Autowired
	private ISimEtatDao daoSimEtat;

	// Toutes les 6 secondes, pour simuler une heure
	@Scheduled(fixedRate = 1000 * 6) // Scheduled : il faut activer cette annotation dans la configuration
	public void heureCron() {

		SimulationEtat simulationEtat = daoSimEtat.findById(1).get();
		LocalTime time = simulationEtat.getTime().toLocalTime();
		LocalDate date = simulationEtat.getTime().toLocalDate();

		System.out.println("CRON heure... Date : " + date.toString() + " Time : " + time.toString());

		for (Citoyen f : daoCitoyen.findAll()) {
			citoyenService.faireAction(f.getId(), time);
		}

		simulationEtat.setTime(simulationEtat.getTime().plusHours(1));
		daoSimEtat.save(simulationEtat);
	}

	// Simule une semaine, appele des fonctions de fin de semaine comme payer loyer
	@Scheduled(fixedRate = 1000 * 6 * 24 * 7) // Scheduled : il faut activer cette annotation dans la configuration
	public void semaineCron() {
		SimulationEtat simulationEtat = daoSimEtat.findById(1).get();
		LocalDate date = simulationEtat.getTime().toLocalDate();

		System.out.println("CRON semaine... Date : " + date.toString());

		List<Proprietaire> proprietaires = daoProprietaire.findAll();
		for (Proprietaire p: proprietaires){
			proprietaireService.payerEmployes(p.getId());
			proprietaireService.percevoirBenefice(p.getId());
		}
		
		BigDecimal argenttotal = new BigDecimal(0);
		BigDecimal argentproprietaires = new BigDecimal(0);
		List<Personne> personnes = daoPersonne.findAll();
		for (Personne p : personnes) {
			argenttotal = argenttotal.add(p.getArgent());
			if (p instanceof Proprietaire) {
				argentproprietaires = argentproprietaires.add(p.getArgent());
			}
		}

		BigDecimal[] desValues = new BigDecimal[3];
		desValues[0] = argenttotal.subtract(argentproprietaires);
		desValues[1] = argentproprietaires;
		desValues[2] = argenttotal;

		EditCsvService editCsv = new EditCsvService();
		editCsv.write(date, desValues);

	}
}