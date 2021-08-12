package fr.formation.dao.jpa;

import java.util.List;

import fr.formation.dao.IProduitDao;
import fr.formation.model.Produit;

public class ProduitDaoJpa extends AbstractDaoJpa<Produit> implements IProduitDao {
	@Override
	public List<Produit> findAll() {
		return em.createQuery("select p from Produit p", Produit.class).getResultList();
	}

	@Override
	public Produit findById(Integer id) {
		return em.find(Produit.class, id);
	}

}
