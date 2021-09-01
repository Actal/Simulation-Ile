package fr.formation.service;

import java.math.BigDecimal;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;

import fr.formation.dao.IPersonneDao;
import fr.formation.dao.IPrestationDao;
import fr.formation.model.Personne;
import fr.formation.model.Prestation;

public class PrestationService extends WorkplaceService {

	@Autowired
	private IPrestationDao daoPrestation;

	@Autowired
	private IPersonneDao daoPersonne;

	@Transactional
	public void ajoutClient(int idPrestation, Personne nouveauClient) {
		Prestation prestation = daoPrestation.findById(idPrestation).get();
		List<Personne> clients = prestation.getClients();

		if (!clients.contains(nouveauClient)) {
			clients.add(nouveauClient);
			nouveauClient.setPrestation(prestation);
			prestation.setNbClientMensuel(prestation.getNbClientMensuel() + 1);
			daoPersonne.save(nouveauClient);
			daoPrestation.save(prestation);
		}
	}

	@Transactional
	public void supprimerClient(int idPrestation, Personne client) {
		Prestation prestation = daoPrestation.findById(idPrestation).get();
		List<Personne> clients = prestation.getClients();

		if (client.getPrestation() == prestation) {
			clients.remove(client);
			client.setPrestation(null);
			daoPersonne.save(client);
			daoPrestation.save(prestation);
		}
	}

	public BigDecimal valeurRecette(int idPrestation) {
		Prestation prestation = daoPrestation.findById(idPrestation).get();
		BigDecimal nbClients = new BigDecimal(prestation.getNbClientMensuel());
		return super.valeurRecette(idPrestation).add(prestation.getPrixEntree().multiply(nbClients));
	}

	public BigDecimal valeurEntretien(int idPrestation) {
		Prestation prestation = daoPrestation.findById(idPrestation).get();
		BigDecimal nbClients = new BigDecimal(prestation.getNbClientMensuel());
		BigDecimal coeffEntretienClients = new BigDecimal(0.005);
		BigDecimal coutEntretienPrestation = nbClients.multiply(coeffEntretienClients)
				.multiply(prestation.getCoutEntretienBase());
		BigDecimal entretienTotal = super.valeurEntretien(idPrestation).add(coutEntretienPrestation);
		return entretienTotal;
	}
}
