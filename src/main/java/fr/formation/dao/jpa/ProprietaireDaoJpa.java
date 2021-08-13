package fr.formation.dao.jpa;

import java.util.List;

import fr.formation.dao.IProprietaireDao;
import fr.formation.model.Proprietaire;

public class ProprietaireDaoJpa extends AbstractDaoJpa <Proprietaire> implements IProprietaireDao{

	@Override
	public List<Proprietaire> findAll() {
		return em.createQuery("SELECT p FROM Proprietaire p", Proprietaire.class)
				.getResultList();
	}

	@Override
	public Proprietaire findById(Integer id) {
		return em.find(Proprietaire.class, id);
	}

	@Override
	public Proprietaire save(Proprietaire entity) {
		if (entity.getId() > 0) return this.update(entity);
		else return this.insert(entity);
	}

}
