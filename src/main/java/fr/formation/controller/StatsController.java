package fr.formation.controller;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import fr.formation.dao.IPersonneDao;
import fr.formation.model.Personne;

@Controller
public class StatsController {
	
	@Autowired
	private IPersonneDao daoPersonne;
	
	@GetMapping("/statistiques")
	public String findParameters(Model model) {
		BigDecimal argenttotal = new BigDecimal(0);
		int nbhabitants = 0;
		
		List<Personne> personnes = daoPersonne.findAll();

		for (Personne p : personnes) {
			argenttotal = argenttotal.add(p.getArgent());
			nbhabitants++;
		}
		
		model.addAttribute("argenttotal", argenttotal);
		model.addAttribute("nbhabitants", nbhabitants);
		
		return "statistiques";
	}
	
}
