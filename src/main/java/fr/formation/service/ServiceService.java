package fr.formation.service;

import java.math.BigDecimal;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;

import fr.formation.dao.ICitoyenDao;
import fr.formation.dao.IServiceDao;
import fr.formation.model.Citoyen;
import fr.formation.model.Personne;
import fr.formation.model.Service;

public class ServiceService extends WorkplaceService{

	@Autowired
	private IServiceDao daoService;
	
	@Autowired
	private ICitoyenDao daoCitoyen;
	
	@Transactional
	public void ajoutClient(int idService, Personne nouveauClient) {
		Service service = daoService.findById(idService).get();
		List<Personne> clients = service.getClients();
		
		if(!clients.contains(nouveauClient)) {
			clients.add(nouveauClient);
			nouveauClient.setService(service);
		}
		service.setNbClientMensuel(service.getNbClientMensuel()+1);
	}
	
	@Transactional
	public void supprimerClient(int idService, Personne client) {
		Service service = daoService.findById(idService).get();
		List<Personne> clients = service.getClients();
		
		if(client.getService() == service) {
			clients.remove(client);
			client.setService(null);
		}
	}
	
	public BigDecimal valeurRecette(int idService) {
		Service service = daoService.findById(idService).get();
		BigDecimal nbClients = new BigDecimal(service.getNbClientMensuel());
		return super.valeurRecette().add(service.getPrixEntree().multiply(nbClients));
	}
	
	public BigDecimal valeurEntretien(int idService){
		Service service = daoService.findById(idService).get();
		BigDecimal nbClients = new BigDecimal(service.getNbClientMensuel());
		BigDecimal coeffEntretienClients = new BigDecimal(0.005);
		BigDecimal coutEntretienService = nbClients.multiply(coeffEntretienClients).multiply(service.getCoutEntretienBase());
		BigDecimal entretienTotal = super.valeurEntretien().add(coutEntretienService);
		return entretienTotal;
	}
}
