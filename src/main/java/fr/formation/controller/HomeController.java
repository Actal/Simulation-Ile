package fr.formation.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
	
	@RequestMapping("/home")
	//@GetMapping("home-get") pour requete
	public String Home(Model model){
		model.addAttribute("prenom","Romain");
		return "home";
	}

}
