package fr.formation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import fr.formation.dao.ICitoyenDao;
import fr.formation.model.Citoyen;

@Controller
public class CitoyenController {

	@Autowired
	private ICitoyenDao daoCitoyen;
	
	@GetMapping("/liste-citoyens")
	public String findAll(Model model) {
		model.addAttribute("citoyens", daoCitoyen.findAllCitoyens());
		model.addAttribute("nameEntity", "Citoyen");
		return "citoyen-liste";
	}
	
	@PostMapping({ "/liste-citoyens" })
	public String save(Citoyen citoyen) {
		daoCitoyen.save(citoyen);
		return "redirect:/liste-citoyens";
	}
	
	@GetMapping("/supprimer-citoyen")
	public String deleteById(@RequestParam int id) {
		daoCitoyen.deleteById(id);
		return "redirect:/liste-citoyens";
	}
}
