package fr.formation.dao.jpa;

import java.util.List;

import fr.formation.dao.IPosteDAO;
import fr.formation.model.Poste;

public class PosteDaoJpa extends AbstractDaoJpa <Poste> implements IPosteDAO{

	@Override
	public List<Poste> findAll() {
		return em.createQuery("SELECT p FROM Poste p", Poste.class)
				.getResultList();
	}

	@Override
	public Poste findById(Integer id) {
		return em.find(Poste.class, id);
	}

	@Override
	public Poste save(Poste entity) {
		if (entity.getId() > 0) return this.update(entity);
		else return this.insert(entity);
	}

}
