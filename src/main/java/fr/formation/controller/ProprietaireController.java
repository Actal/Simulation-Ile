package fr.formation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import fr.formation.dao.IProprietaireDao;
import fr.formation.model.Proprietaire;

@Controller
public class ProprietaireController {

	@Autowired
	private IProprietaireDao daoProprietaire;
	
	@GetMapping("/liste-proprietaires")
	public String findAll(Model model) {
		model.addAttribute("citoyens", daoProprietaire.findAll());
		model.addAttribute("nameEntity", "Propri&eacute;taire");
		return "citoyen-liste";
	}
	
	@PostMapping({ "/liste-proprietaires" })
	@PreAuthorize("hasRole('ADMIN')")
	public String save(Proprietaire proprietaire) {
		daoProprietaire.save(proprietaire);
		return "redirect:/liste-proprietaires";
	}
	
	@GetMapping("/supprimer-proprietaire")
	@PreAuthorize("hasRole('ADMIN')")
	public String deleteById(@RequestParam int id) {
		daoProprietaire.deleteById(id);
		return "redirect:/liste-proprietaires";
	}
}
