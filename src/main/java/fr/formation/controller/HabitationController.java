package fr.formation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import fr.formation.dao.ICoordonneesDao;
import fr.formation.dao.IHabitationDao;
import fr.formation.dao.IProprietaireDao;
import fr.formation.model.Coordonnees;
import fr.formation.model.Habitation;
import fr.formation.service.BiomeService;
import fr.formation.service.HabitationService;

@Controller
public class HabitationController {

	@Autowired
	private IHabitationDao daoHabitation;
	@Autowired
	private ICoordonneesDao daoCoordonnees;
	@Autowired
	private IProprietaireDao daoProprietaire;
	@Autowired
	private BiomeService serviceBiome;
	@Autowired
	private HabitationService serviceHabitation;
	
	@GetMapping("/liste-habitations")
	public String findAll(Model model) {
		model.addAttribute("habitations", daoHabitation.findAll());
		model.addAttribute("proprietaires", daoProprietaire.findAll());
		return "habitation-liste";
	}
	
	@PostMapping("/liste-habitations")
	@PreAuthorize("hasRole('ADMIN')")
	public String save(Habitation habitation, @RequestParam int x, @RequestParam int y) {
		
		Coordonnees c = new Coordonnees(x,y);
		daoCoordonnees.save(c);
				
		habitation.setCoordonnees(c);
		habitation.setBiome(serviceBiome.findBiomeContaining(c));
		
		if(serviceHabitation.estConstructible(habitation) ) {
			daoHabitation.save(habitation);
		}
		
		return "redirect:/liste-habitations";
	}
	
	@GetMapping("/supprimer-habitation")
	@PreAuthorize("hasRole('ADMIN')")
	public String deleteById(@RequestParam int id) {
		daoHabitation.deleteById(id);
		return "redirect:/liste-habitations";
	}
}
