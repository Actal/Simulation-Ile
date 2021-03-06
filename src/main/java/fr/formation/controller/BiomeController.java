package fr.formation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import fr.formation.dao.IBiomeDao;
import fr.formation.dao.ICoordonneesDao;
import fr.formation.model.Biome;
import fr.formation.model.Coordonnees;

@Controller

public class BiomeController {

	@Autowired
	private IBiomeDao daoBiome;
	@Autowired
	private ICoordonneesDao daoCoordonnees;
	
	@GetMapping("/liste-biomes")
	public String findAll(Model model) {
		model.addAttribute("biomes", daoBiome.findAll());
		return "biome-liste";
	}
	
	@PostMapping("/liste-biomes")
	@PreAuthorize("hasRole('ADMIN')")
	public String save(Biome b, int x, int y) {
		
		Coordonnees c = new Coordonnees(x,y);
		daoCoordonnees.save(c);
		
		b.setCoordonnees(c);
		daoBiome.save(b);
		
		return "redirect:/liste-biomes";
	}
	
	@GetMapping("/supprimer-biome")
	@PreAuthorize("hasRole('ADMIN')")
	public String deleteById(@RequestParam int id) {
		daoBiome.deleteById(id);
		return "redirect:/liste-biomes";
	}
}
