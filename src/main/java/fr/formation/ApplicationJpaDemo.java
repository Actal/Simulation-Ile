package fr.formation;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import fr.formation.model.Adresse;
import fr.formation.model.Attribut;
import fr.formation.model.Categorie;
import fr.formation.model.Client;
import fr.formation.model.Fournisseur;
import fr.formation.model.Produit;

public class ApplicationJpaDemo {

	public static void main(String[] args) {
		// Creation de l'EntityManagerFactory
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("EShopUnit");

		// Creation de l'EntityManager
		EntityManager em = emf.createEntityManager();

		// Appel d'un persist
//		persistClient(em);
//		persistFournisseur(em);
//		persistProduit(em);
//		persistCategorie(em);
//		persistAttribut(em);
//		removeProduit(em);
//		removeFournisseur(em);

//		findAllProduit(em);
//		findClientByNom(em, "BABAR");
//		findAllFournisseur(em);

//		updateFournisseurByProduitId(em, 2, "CDISCOUNT");
		
//		associateProduitAttribut(em);
		

		// Fermer l'EntityManager + l'EntityManagerFactory
		em.close();
		emf.close();
	}

	public static void persistClient(EntityManager em) {
		Client monClient = new Client();
		Adresse monAdresse = new Adresse();

		monClient.setPrenom("Pierre2");
		monClient.setNom("BABAR");
		monClient.setDateNaissance(LocalDate.of(1993, 4, 28));
		monClient.setAdresse(monAdresse);

		monAdresse.setRue("1 rue de Paris");
		monAdresse.setCp("75000");
		monAdresse.setVille("Paris");

		// Creation de la transaction (toute modif de data - INSERT, UPDATE, DELETE)
		em.getTransaction().begin();

		// On persiste (INSERT)
		em.persist(monAdresse); // On peut ne pas sauvegarder ici si on met une cascade PERSIST
								// sur la relation primaire Client->Adresse
		em.persist(monClient);

		// Appliquer les changements (COMMIT TRANSACTION)
		em.getTransaction().commit();

		// Operations essentielles de EntityManager
		// persist
		// merge
		// remove
		// find
		// createQuery
	}

	public static void persistFournisseur(EntityManager em) {
		Fournisseur monFournisseur = new Fournisseur();

		monFournisseur.setNom("AMAZON");
		monFournisseur.setSiret("01234567891234");

		em.getTransaction().begin();
		em.persist(monFournisseur);
		em.getTransaction().commit();

	}

	public static void persistProduit(EntityManager em) {
		Produit monProduit = new Produit();

		monProduit.setLibelle("GoPRO HERO 9");
		monProduit.setPrix(new BigDecimal(159.99));

		// #1 - Rechercher le fournisseur depuis EntityManager
//		Fournisseur monFournisseur = em.find(Fournisseur.class, 1);

		// #2 - Creer un fournisseur avec l'information essentielle : son ID
		Fournisseur monFournisseur = new Fournisseur();
		monFournisseur.setId(1);

		// Une fois qu'on a le fournisseur, il n'y a plus qu'a l'associer au produit
		monProduit.setFournisseur(monFournisseur);

		em.getTransaction().begin();
		em.persist(monProduit);
		em.getTransaction().commit();

	}

	public static void persistCategorie(EntityManager em) {
		Categorie maCategorie = new Categorie();

		maCategorie.setLibelle("Camera d'action");

		em.getTransaction().begin();
		em.persist(maCategorie);
		em.getTransaction().commit();
	}
	
	public static void persistAttribut(EntityManager em) {
		Attribut monAttribut = new Attribut("Couleur", "Bleu");
		
		em.getTransaction().begin();
		em.persist(monAttribut);
		em.getTransaction().commit();
	}
	
	public static void associateProduitAttribut(EntityManager em) {
		// Rechercher le produit 1
		Produit monProduit = em.find(Produit.class, 1);
		
		// Rechercher l'attribut par son nom & sa valeur
		Attribut monAttribut = em
				.createQuery("select a from Attribut a where a.nom = ?1 and a.valeur = ?2", Attribut.class)
				.setParameter(1, "Couleur")
				.setParameter(2, "Bleu")
				.getSingleResult();
		
		// Associer l'attribut au produit
		//!\\ ICI RISQUE POSSIBLE DE NullPointerException
		// monProduit.setAttributs(new ArrayList<>());
		monProduit.getAttributs().add(monAttribut);
		
		// Sauvegarder le produit (par persist ou merge)
		em.getTransaction().begin();
		em.merge(monProduit);
		em.getTransaction().commit();
	}

	public static void findAllProduit(EntityManager em) {
//		em.createNativeQuery("SELECT * FROM produit");
		List<Produit> mesProduits = em
//				.createQuery("SELECT p FROM Produit p WHERE p.id < 2", Produit.class)

		// Parametres nommes
//				.createQuery("SELECT p FROM Produit p WHERE p.id < :leparam", Produit.class)
//				.setParameter("leparam", 2) // nom parametre, valeur

		// Parametres indexes
				.createQuery("SELECT p FROM Produit p WHERE p.id < ?1", Produit.class)
				.setParameter(1, 2) // position,valeur

//			.getSingleResult() // Permet de recuperer le seul enregistrement de la requete
				.getResultList() // Permet de recuperer une liste d'enregistrements
		;

		for (Produit p : mesProduits) {
			System.out.println(p.getId() + " - " + p.getLibelle());
		}
	}

	public static void findClientByNom(EntityManager em, String nom) {
		// Rechercher le client par son nom
		// Afficher les infos du client (nom, prenom, id)

		// Cas de la liste de clients
//		List<Client> mesClients = em
//				.createQuery("select c from Client c where c.nom = ?1", Client.class)
//				.setParameter(1, nom)
//				.getResultList();
//
//		for (Client c : mesClients) {
//			System.out.println(c.getNom() + " " + c.getPrenom() + " " + c.getId());
//		}

		// Cas d'un seul client
		Client monClient = em.createQuery("SELECT c FROM Client c WHERE c.nom = ?1", Client.class).setParameter(1, nom)
				.getSingleResult();

		System.out.println(monClient.getNom() + " " + monClient.getPrenom() + " " + monClient.getId());
	}

	public static void findAllFournisseur(EntityManager em) {
		// Liste les fournisseurs (id + nom)

		List<Fournisseur> mesFournisseurs = em
//				.createQuery("select f from Fournisseur f", Fournisseur.class)
				// fetch permet de rattacher la liste des produits au Fournisseur
				.createQuery("select f from Fournisseur f left join fetch f.produits p", Fournisseur.class)
				.getResultList();

		// On charge la liste des produits pour chaque fournisseur (si ce n'est pas deja
		// fait)
//		for (Fournisseur f : mesFournisseurs) {
//			Hibernate.initialize(f.getProduits()); // Permet de charger les infos
//		}

		// Force sa fermeture pour simuler une architecture plus complexe
		em.close();

		for (Fournisseur f : mesFournisseurs) {
			System.out.println(f.getId() + " " + f.getNom());

			System.out.println(" - Les produits - ");
			for (Produit p : f.getProduits()) {
				System.out.println(p.getLibelle());
			}
		}

	}

	public static void removeProduit(EntityManager em) {
		// #1 - On recherche le produit a supprimer
		Produit monProduitASupprimer = em.find(Produit.class, 3);
		em.getTransaction().begin();
		em.remove(monProduitASupprimer);
		em.getTransaction().commit();

		// #2 - On fabrique un nouveau produit en donnant l'info essentielle : son ID
//		Produit monProduitASupprimer = new Produit();
//		monProduitASupprimer.setId(5);
//		//On attache le produit qu'on a cree a l'EntityManager
//		//Mais attention, le merge cree une nouvelle instance (clone)
//		monProduitASupprimer = em.merge(monProduitASupprimer);
//		
//		em.getTransaction().begin();
//		em.remove(monProduitASupprimer);
//		em.getTransaction().commit();
	}

	public static void removeFournisseur(EntityManager em) {
		Fournisseur monFournisseurASupprimer = em.find(Fournisseur.class, 2);
		em.getTransaction().begin();
		em.remove(monFournisseurASupprimer);
		em.getTransaction().commit();
	}

	public static void updateFournisseurByProduitId(EntityManager em, int produitId, String newNom) {
		// Récupérer le fournisseur qui livre le produit #produitId
		Fournisseur monFournisseur = em
				.createQuery("select f from Fournisseur f inner join f.produits p where p.id = ?1", Fournisseur.class)
				.setParameter(1, produitId).getSingleResult();

		if (monFournisseur != null) {
			// Affecte le nouveau nom au fournisseur
			monFournisseur.setNom(newNom);

			// Sauvegarder les changements
			em.getTransaction().begin();
			em.merge(monFournisseur);
			em.getTransaction().commit();
		}

	}

}
