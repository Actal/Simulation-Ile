package fr.formation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import fr.formation.dao.IHabitationDao;
import fr.formation.model.Habitation;

@Controller
public class HabitationController {

	@Autowired
	private IHabitationDao daoHabitation;
	
	@GetMapping("/liste-habitations")
	public String findAll(Model model) {
		model.addAttribute("habitations", daoHabitation.findAll());
		return "habitation-liste";
	}
	
	@PostMapping({ "/liste-habitations" })
	public String save(Habitation habitation) {
		daoHabitation.save(habitation);
		return "redirect:/liste-habitations";
	}
	
	@GetMapping("/supprimer-habitation")
	public String deleteById(@RequestParam int id) {
		daoHabitation.deleteById(id);
		return "redirect:/liste-habitations";
	}
}
