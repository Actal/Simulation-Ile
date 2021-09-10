package fr.formation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import fr.formation.dao.IMetierDao;
import fr.formation.dao.IPosteDao;
import fr.formation.dao.IWorkplaceDao;
import fr.formation.model.Poste;

@Controller
public class PosteController {

	@Autowired
	private IPosteDao daoPoste;

	@Autowired
	private IMetierDao daoMetier;

	@Autowired
	private IWorkplaceDao daoWorkplace;
	
	@GetMapping("/liste-postes")
	public String findAll(Model model) {
		model.addAttribute("metiers", daoMetier.findAll());
		model.addAttribute("workplaces", daoWorkplace.findAll());
		model.addAttribute("postes", daoPoste.findAll());
		return "poste-liste";
	}
	
	@PostMapping({ "/liste-postes" })
	@PreAuthorize("hasRole('ADMIN')")
	public String save(Poste poste) {
		daoPoste.save(poste);
		return "redirect:/liste-postes";
	}
	
	@GetMapping("/supprimer-poste")
	@PreAuthorize("hasRole('ADMIN')")
	public String deleteById(@RequestParam int id) {
		daoPoste.deleteById(id);
		return "redirect:/liste-postes";
	}
}
