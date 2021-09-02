package fr.formation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import fr.formation.dao.IPrestationDao;
import fr.formation.model.Prestation;

@Controller
public class PrestationController {

	@Autowired
	private IPrestationDao daoPrestation;
	
	@GetMapping("/liste-prestations")
	public String findAll(Model model) {
		model.addAttribute("workplaces", daoPrestation.findAll());
		return "prestation-liste";
	}
	
	@PostMapping({ "/liste-prestations" })
	public String save(Prestation prestation) {
		daoPrestation.save(prestation);
		return "redirect:/liste-prestations";
	}
	
	@GetMapping("/supprimer-prestation")
	public String deleteById(@RequestParam int id) {
		daoPrestation.deleteById(id);
		return "redirect:/liste-prestations";
	}
}
