package fr.formation.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.formation.dao.ICitoyenDao;
import fr.formation.dao.IProprietaireDao;
import fr.formation.model.Batiment;
import fr.formation.model.Citoyen;
import fr.formation.model.Poste;
import fr.formation.model.Proprietaire;
import fr.formation.model.Workplace;

@Service
public class ProprietaireService extends CitoyenService {

	@Autowired
	private IProprietaireDao daoProprietaire;
	
	@Autowired
	private ICitoyenDao daoCitoyen;
	
	@Transactional
	public void payerEmployes(int id) {
		Proprietaire p = daoProprietaire.findById(id).get();
		for (Batiment b : p.getBatiments()) {
			if (b instanceof Workplace) {
				for (Poste poste : ((Workplace) b).getPostes()) {
					if (poste.getCitoyen() != null) {
						p.payer(poste.getSalaire());
						System.out.println(poste.getSalaire());
						Citoyen c = poste.getCitoyen();
						c.gagnerArgent(poste.getSalaire());
						daoCitoyen.save(c);
						
					}
				}
			}
		}
		
		daoProprietaire.save(p);
	}

	@Transactional
	public void percevoirBenefice(int id) {
		Proprietaire p = daoProprietaire.findById(id).get();
		for (Batiment b : p.getBatiments()) {
			p.gagnerArgent(b.valeurBenefice());
		}
		daoProprietaire.save(p);
	}
}
