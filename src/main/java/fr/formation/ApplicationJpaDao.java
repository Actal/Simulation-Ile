package fr.formation;

import java.math.BigDecimal;
import java.util.List;

import fr.formation.dao.IFournisseurDao;
import fr.formation.dao.IProduitDao;
import fr.formation.dao.jpa.AbstractDaoJpa;
import fr.formation.dao.jpa.FournisseurDaoJpa;
import fr.formation.dao.jpa.ProduitDaoJpa;
import fr.formation.model.Fournisseur;
import fr.formation.model.Produit;

public class ApplicationJpaDao {

	public static void main(String[] args) {
//		ProduitDaoJpa demoCounter = new ProduitDaoJpa();
//		System.out.println(AbstractDaoJpa.counter);
//		
//		demoCounter = new ProduitDaoJpa();
//		System.out.println(AbstractDaoJpa.counter);
		
		// Fabrication de la DAO Produit concrete, stockage dans son interface dediee
		IProduitDao daoProduit = new ProduitDaoJpa();
		// Fabrication de la DAO Fournisseur concrete, stockage dans son interface dediee
		IFournisseurDao daoFournisseur = new FournisseurDaoJpa();

		// Creation d'un nouveau produit, qu'on sauvegarde
//		creerProduit(daoProduit);

		// Recuperation et stockage de la liste des produits
		List<Produit> mesProduits = daoProduit.findAll();

		// Parcours de la liste des produits
		System.out.println(" - Liste des produits - ");
		for (Produit p : mesProduits) {
			System.out.println(p.getLibelle());
		}
		
		// Parcours de la liste des fournisseurs
		System.out.println(" - Liste des fournisseurs - ");
		for (Fournisseur f : daoFournisseur.findAll()) {
			System.out.println(f.getNom());
		}
		
		// Fermer la connexion
		AbstractDaoJpa.close();

	}

	public static void creerProduit(IProduitDao daoProduit) {
		// Creation du nouveau produit
		Produit produit = new Produit("Parachute de France", new BigDecimal(7999));

		// Sauvegarde du produit
		daoProduit.save(produit);
	}

}
