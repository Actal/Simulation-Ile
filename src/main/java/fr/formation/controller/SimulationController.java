package fr.formation.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SimulationController {

	@RequestMapping({"/simulation", "/", "/home"})
	//@GetMapping("home-get") pour requete
	public String Simulation(){
		return "simulation";
	}
}
