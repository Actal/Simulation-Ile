package fr.formation.controller;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import fr.formation.dao.IBatimentDao;
import fr.formation.dao.IPersonneDao;
import fr.formation.model.Batiment;
import fr.formation.model.Personne;
import fr.formation.model.Proprietaire;

@Controller
public class StatsController {
	
	@Autowired
	private IPersonneDao daoPersonne;
	
	@Autowired
	private IBatimentDao daoBatiment;
	
	@GetMapping("/statistiques")
	public String findParameters(Model model) {
		BigDecimal argenttotal = new BigDecimal(0);
		BigDecimal argentproprietaires = new BigDecimal(0);
		int nbhabitants = 0;
		int nbbatiments = 0;
		
		List<Personne> personnes = daoPersonne.findAll();
		List<Batiment> batiments = daoBatiment.findAll();

		for (Personne p : personnes) {
			argenttotal = argenttotal.add(p.getArgent());
			if (p instanceof Proprietaire) {
				argentproprietaires = argentproprietaires.add(p.getArgent());
			}
			nbhabitants++;
		}
		
		for (Batiment b : batiments) {
			nbbatiments++;
		}
		
		model.addAttribute("argenttotal", argenttotal);
		model.addAttribute("argentproprietaires", argentproprietaires);
		model.addAttribute("nbhabitants", nbhabitants);
		model.addAttribute("nbbatiments", nbbatiments);
		
		return "statistiques";
	}
	
}
