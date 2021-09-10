package fr.formation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import fr.formation.dao.IMetierDao;
import fr.formation.model.Metier;

@Controller
public class MetierController {

	@Autowired
	private IMetierDao daoMetier;
	
	@GetMapping("/liste-metiers")
	public String findAll(Model model) {
		model.addAttribute("metiers", daoMetier.findAll());
		return "metier-liste";
	}
	
	@PostMapping({ "/liste-metiers" })
	@PreAuthorize("hasRole('ADMIN')")
	public String save(Metier metier) {
		daoMetier.save(metier);
		return "redirect:/liste-metiers";
	}
	
	@GetMapping("/supprimer-metier")
	@PreAuthorize("hasRole('ADMIN')")
	public String deleteById(@RequestParam int id) {
		daoMetier.deleteById(id);
		return "redirect:/liste-metiers";
	}
}
