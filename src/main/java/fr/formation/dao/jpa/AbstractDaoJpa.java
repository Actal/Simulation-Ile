package fr.formation.dao.jpa;

import java.lang.reflect.Method;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public abstract class AbstractDaoJpa<T> {
	// Avec static, on rend cet attribut commun a toutes les instances (premisse
	// Singleton)
//	public static int counter = 1;
//	
//	public AbstractDaoJpa() {
//		counter++;
//	}

	// Creation de l'EntityManagerFactory
	private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("EShopUnit");

	// Creation de l'EntityManager
	protected EntityManager em = emf.createEntityManager();

	// Fermeture de l'EMF et des EM associes (ceux crees a l'EMF)
	public static void close() {
		emf.close();
	}
	
	public abstract T findById(Integer id);

	public T insert(T entity) {
		// Demarrage de la transaction
		em.getTransaction().begin();

		// Persistance de l'entite
		em.persist(entity);

		// Commit de la transaction
		em.getTransaction().commit();

		return entity;
	}

	public T update(T entity) {
		em.getTransaction().begin();
		entity = em.merge(entity);
		em.getTransaction().commit();
		return entity;
	}

	public T save(T entity) {
		// Pour remonter save vers Abstract, il faut utiliser la reflexivite en Java
		int entityId = 0;
		try {
			// On recupere la methode getId de la classe
			Method method = entity.getClass().getMethod("getId");

			// On invoque, on execute cette methode sur l'instance qui nous interesse
			Object result = method.invoke(entity);
			
			entityId = (Integer) result;
		} catch (Exception e) {
			e.printStackTrace();
			// ici ca n'a pas marche, tant pis
		}
		if (entityId > 0) {
			// Update
			return this.update(entity);
		} else {
			// Insert
			return this.insert(entity);
		}
	}

	public void delete(T entity) {
		// Demarrage de la TX
		em.getTransaction().begin();

		// On attache l'entite a l'EntityManager, avant de supprimer
		// Au cas ou l'entite n'est pas deja managee
		em.remove(em.merge(entity));

		// Commit de la TX
		em.getTransaction().commit();
	}
	
	public void deletebyId(Integer id) {
		T monEntiteASupprimer = this.findById(id);
		this.delete(monEntiteASupprimer);
	}

}
