package fr.formation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import fr.formation.dao.IBatimentDao;

@Controller
public class SimulationController {
	
	@Autowired
	private IBatimentDao daoBatiment;

	@RequestMapping("/simulation")
	//@GetMapping("home-get") pour requete
	public String Home(Model model){
		model.addAttribute("batiments", daoBatiment.findAll());
		return "simulation";
	}
}
