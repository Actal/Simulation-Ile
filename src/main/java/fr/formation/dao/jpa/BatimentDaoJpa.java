package fr.formation.dao.jpa;

import java.util.List;

import fr.formation.dao.IBatimentDao;
import fr.formation.model.Batiment;

public class BatimentDaoJpa extends AbstractDaoJpa <Batiment> implements IBatimentDao{

	@Override
	public List<Batiment> findAll() {
		return em.createQuery("SELECT b FROM Batiment b", Batiment.class)
				.getResultList();
	}

	@Override
	public Batiment findById(Integer id) {
		return em.find(Batiment.class, id);
	}

	@Override
	public Batiment save(Batiment entity) {
		if (entity.getId() > 0) return this.update(entity);
		else return this.insert(entity);
	}
	
}
