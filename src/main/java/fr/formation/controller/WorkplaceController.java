package fr.formation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import fr.formation.dao.ICoordonneesDao;
import fr.formation.dao.IProprietaireDao;
import fr.formation.dao.IWorkplaceDao;
import fr.formation.model.Coordonnees;
import fr.formation.model.Workplace;
import fr.formation.service.BiomeService;
import fr.formation.service.WorkplaceService;

@Controller
public class WorkplaceController {

	@Autowired
	private IWorkplaceDao daoWorkplace;
	@Autowired
	private ICoordonneesDao daoCoordonnees;
	@Autowired
	private IProprietaireDao daoProprietaire;
	@Autowired
	private BiomeService serviceBiome;
	@Autowired
//	@Qualifier("WorkplaceService")
	WorkplaceService workplaceService;
	
	@GetMapping("/liste-workplaces")
	public String findAll(Model model) {

		model.addAttribute("workplaces", daoWorkplace.findAllWorkplaces());
		model.addAttribute("proprietaires", daoProprietaire.findAll());
		return "workplace-liste";
	}
	
	@PostMapping("/liste-workplaces" )
	public String save(Workplace workplace, @RequestParam int x, @RequestParam int y) {
		Coordonnees c = new Coordonnees(x,y);
		daoCoordonnees.save(c);
		
		workplace.setCoordonnees(c);
		workplace.setBiome(serviceBiome.findBiomeContaining(c));

		if(workplaceService.estConstructible(workplace) ) {
			daoWorkplace.save(workplace);
		}
		
		return "redirect:/liste-workplaces";
	}
	
	@GetMapping("/supprimer-workplace")
	public String deleteById(@RequestParam int id) {
		daoWorkplace.deleteById(id);
		return "redirect:/liste-workplaces";
	}
}
