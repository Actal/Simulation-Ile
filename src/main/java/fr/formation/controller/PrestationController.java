package fr.formation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import fr.formation.dao.ICoordonneesDao;
import fr.formation.dao.IPrestationDao;
import fr.formation.dao.IProprietaireDao;
import fr.formation.model.Coordonnees;
import fr.formation.model.Prestation;
import fr.formation.service.BiomeService;
import fr.formation.service.PrestationService;

@Controller
public class PrestationController {

	@Autowired
	private IPrestationDao daoPrestation;
	@Autowired
	private ICoordonneesDao daoCoordonnees;
	@Autowired
	private IProprietaireDao daoProprietaire;
	@Autowired
	private BiomeService serviceBiome;
	@Autowired
	private PrestationService servicePrestation;
	
	@GetMapping("/liste-prestations")
	public String findAll(Model model) {
		model.addAttribute("prestations", daoPrestation.findAll());
		model.addAttribute("proprietaires", daoProprietaire.findAll());
		return "prestation-liste";
	}
	
	@PostMapping("/liste-prestations")
	@PreAuthorize("hasRole('ADMIN')")
	public String save(Prestation prestation, @RequestParam int x, @RequestParam int y) {
		Coordonnees c = new Coordonnees(x,y);
		daoCoordonnees.save(c);
				
		prestation.setCoordonnees(c);
		prestation.setBiome(serviceBiome.findBiomeContaining(c));

		if(servicePrestation.estConstructible(prestation) ) {
			daoPrestation.save(prestation);
		}
		
		return "redirect:/liste-prestations";
	}
	
	@GetMapping("/supprimer-prestation")
	@PreAuthorize("hasRole('ADMIN')")
	public String deleteById(@RequestParam int id) {
		daoPrestation.deleteById(id);
		return "redirect:/liste-prestations";
	}
}
