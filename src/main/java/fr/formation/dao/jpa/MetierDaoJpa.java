package fr.formation.dao.jpa;

import java.util.List;

import fr.formation.dao.IMetierDao;
import fr.formation.model.Metier;

public class MetierDaoJpa extends AbstractDaoJpa <Metier> implements IMetierDao{

	@Override
	public List<Metier> findAll() {
		return em.createQuery("SELECT m FROM Metier m", Metier.class)
				.getResultList();
	}

	@Override
	public Metier findById(Integer id) {
		return em.find(Metier.class, id);
	}

	@Override
	public Metier save(Metier entity) {
		if (entity.getId() > 0) return this.update(entity);
		else return this.insert(entity);
	}

}
