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
		BigDecimal argentTotal = new BigDecimal(0);
		List<Personne> personnes = daoPersonne.findAll();

		for (Personne p : personnes) {
			argentTotal = argentTotal.add(p.getArgent());
		}
		
		model.addAttribute("argent-total", argentTotal);
		model.addAttribute("nb-habitants-total", personnes.size());
		
		return "statistiques";
	}
	
}
