package fr.formation.dao.jpa;

import java.util.List;

import fr.formation.dao.IFournisseurDao;
import fr.formation.model.Fournisseur;

public class FournisseurDaoJpa extends AbstractDaoJpa<Fournisseur> implements IFournisseurDao {
	@Override
	public List<Fournisseur> findAll() {
		return em.createQuery("select f from Fournisseur f", Fournisseur.class).getResultList();
	}

	@Override
	public Fournisseur findById(Integer id) {
		return em.find(Fournisseur.class, id);
	}

}
