package fr.formation.service;

import java.math.BigDecimal;
import java.time.DayOfWeek;
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
	@Scheduled(fixedDelay = 1000 * 6) // Scheduled : il faut activer cette annotation dans la configuration
	public void heureCron() {

		SimulationEtat simulationEtat = daoSimEtat.findById(1).get();
		LocalTime time = simulationEtat.getTime().toLocalTime();
		LocalDate date = simulationEtat.getTime().toLocalDate();

		System.out.println("CRON heure... Date : " + date.toString() + " Time : " + time.toString());

		for (Citoyen f : daoCitoyen.findAll()) {
			citoyenService.faireAction(f.getId(), time);
		}

		//(date.getDayOfWeek() ==  DayOfWeek.MONDAY) &&
		if( (time.compareTo(LocalTime.of(0, 0)) == 0) ) {
			System.out.println("CRON semaine... Date : " + date.toString());

			List<Proprietaire> proprietaires = daoProprietaire.findAll();
			for (int i=0; i<proprietaires.size(); i++){
				Proprietaire p = proprietaires.get(i);
				proprietaireService.payerEmployes(p.getId());
				proprietaireService.percevoirBenefice(p.getId());
			}
	
			BigDecimal argenttotal = daoPersonne.countArgentTotal();
			BigDecimal argentproprietaires = daoProprietaire.countArgentTotal();
			
			BigDecimal[] desValues = new BigDecimal[3];
			desValues[0] = argenttotal.subtract(argentproprietaires);
			desValues[1] = argentproprietaires;
			desValues[2] = argenttotal;
	
			EditCsvService editCsv = new EditCsvService();
			editCsv.write(date, desValues);
		}

		simulationEtat.setTime(simulationEtat.getTime().plusHours(1));
		daoSimEtat.save(simulationEtat);
	}

}