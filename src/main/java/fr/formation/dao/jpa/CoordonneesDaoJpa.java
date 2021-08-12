package fr.formation.dao.jpa;

import java.util.List;

import fr.formation.dao.ICoordoneesDao;
import fr.formation.model.Coordonnees;

public class CoordonneesDaoJpa extends AbstractDaoJpa <Coordonnees> implements ICoordoneesDao{

	@Override
	public List<Coordonnees> findAll() {
		return em.createQuery("SELECT c FROM Coordonnees c", Coordonnees.class)
				.getResultList();
	}

	@Override
	public Coordonnees findById(Integer id) {
		return em.find(Coordonnees.class, id);
	}

	@Override
	public Coordonnees save(Coordonnees entity) {
		if (entity.getId() > 0) return this.update(entity);
		else return this.insert(entity);
	}

}
