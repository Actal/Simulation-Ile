package fr.formation.service;

import java.math.BigDecimal;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.formation.dao.ICitoyenDao;
import fr.formation.dao.IProprietaireDao;
import fr.formation.model.Batiment;
import fr.formation.model.Citoyen;
import fr.formation.model.Habitation;
import fr.formation.model.Poste;
import fr.formation.model.Proprietaire;
import fr.formation.model.Workplace;

@Service
public class ProprietaireService extends CitoyenService {

	@Autowired
	private IProprietaireDao daoProprietaire;
	
	@Autowired
	private ICitoyenDao daoCitoyen;
		
	@Autowired
	private WorkplaceService workplaceService;
	
	@Autowired
	private HabitationService habitationService;
	
	@Transactional
	public void payerEmployes(int id) {
		Proprietaire p = daoProprietaire.findById(id).get();
		List<Batiment> batiments = p.getBatiments();
		for (int i=0; i<batiments.size(); i++) {
			Batiment b = batiments.get(i);
			if (b instanceof Workplace) {
				List<Poste> postes = ((Workplace) b).getPostes();
				for (int j=0; j<postes.size(); j++) {
					Poste poste = postes.get(j);
					if (poste.getCitoyen() != null) {
						payer(id, poste.getSalaire());
						Citoyen c = poste.getCitoyen();
						gagnerArgent(c.getId(), poste.getSalaire());
						daoCitoyen.save(c);
					}
				}
			}
		}
		
		daoProprietaire.save(p);
	}

	public void percevoirBenefice(int id) {
		Proprietaire p = daoProprietaire.findById(id).get();
		BigDecimal benefTot = new BigDecimal(0);
		
		List<Batiment> batiments = p.getBatiments();

		for (int i=0; i<batiments.size(); i++ ) {
			Batiment b = batiments.get(i);
			BigDecimal benef = new BigDecimal(0);
			
			if( b instanceof Habitation) {
				benef = habitationService.valeurBenefice(b.getId());
			}
			else if( b instanceof Workplace) {
				benef = workplaceService.valeurBenefice(b.getId());
			}
			
			benefTot = benefTot.add(benef);
			
		}
		gagnerArgent(id, benefTot);
	}
}
