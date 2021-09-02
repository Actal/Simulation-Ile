package fr.formation.controller;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
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
import fr.formation.model.Proprietaire;
import fr.formation.service.BiomeService;

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
	
	@GetMapping("/liste-habitations")
	public String findAll(Model model) {
		model.addAttribute("habitations", daoHabitation.findAll());
		model.addAttribute("proprietaires", daoProprietaire.findAll());
		return "habitation-liste";
	}
	
	@PostMapping("/liste-habitations")
	public String save(@RequestParam String nom, @RequestParam int longueur, @RequestParam BigDecimal prix, @RequestParam BigDecimal coutEntretienBase, @RequestParam BigDecimal loyer, @RequestParam int nbPlace, @RequestParam int proprietaireId,  @RequestParam int x, @RequestParam int y) {
		
		Coordonnees c = new Coordonnees(x,y);
		daoCoordonnees.save(c);
		
		Proprietaire p = new Proprietaire();
		p.setId(proprietaireId);
		
		Habitation h = new Habitation(longueur, nom, prix, coutEntretienBase, nbPlace, c, serviceBiome.findBiomeContaining(c), p, loyer);
		daoHabitation.save(h);
		
		return "redirect:/liste-habitations";
	}
	
	@GetMapping("/supprimer-habitation")
	public String deleteById(@RequestParam int id) {
		daoHabitation.deleteById(id);
		return "redirect:/liste-habitations";
	}
}
