package fr.formation.controller;

import org.springframework.web.bind.annotation.RequestMapping;

public class SimulationController {

	@RequestMapping("/simulation")
	//@GetMapping("home-get") pour requete
	public String Home(){
		return "simulation";
	}
}
