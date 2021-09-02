package fr.formation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import fr.formation.dao.IWorkplaceDao;
import fr.formation.model.Workplace;

@Controller
public class WorkplaceController {

	@Autowired
	private IWorkplaceDao daoWorkplace;
	
	@GetMapping("/liste-workplaces")
	public String findAll(Model model) {
		model.addAttribute("workplaces", daoWorkplace.findAll());
		return "workplace-liste";
	}
	
	@PostMapping({ "/liste-workplaces" })
	public String save(Workplace workplace) {
		daoWorkplace.save(workplace);
		return "redirect:/liste-workplaces";
	}
	
	@GetMapping("/supprimer-workplace")
	public String deleteById(@RequestParam int id) {
		daoWorkplace.deleteById(id);
		return "redirect:/liste-workplaces";
	}
}
