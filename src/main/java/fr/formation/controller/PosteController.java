package fr.formation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import fr.formation.dao.IPosteDao;
import fr.formation.model.Poste;

@Controller
public class PosteController {

	@Autowired
	private IPosteDao daoPoste;
	
	@GetMapping("/liste-postes")
	public String findAll(Model model) {
		model.addAttribute("postes", daoPoste.findAll());
		return "poste-liste";
	}
	
	@PostMapping({ "/liste-workplaces" })
	public String save(Poste poste) {
		daoPoste.save(poste);
		return "redirect:/liste-postes";
	}
	
	@GetMapping("/supprimer-workplace")
	public String deleteById(@RequestParam int id) {
		daoPoste.deleteById(id);
		return "redirect:/liste-postes";
	}
}
