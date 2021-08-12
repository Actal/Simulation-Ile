package fr.formation.dao.jpa;

import java.util.List;

import fr.formation.dao.IPersonneDao;
import fr.formation.model.Personne;

public class PersonneDaoJpa extends AbstractDaoJpa <Personne> implements IPersonneDao{

	@Override
	public List<Personne> findAll() {
		return em.createQuery("SELECT p FROM Personne p", Personne.class)
				.getResultList();
	}

	@Override
	public Personne findById(Integer id) {
		return em.find(Personne.class, id);
	}

	@Override
	public Personne save(Personne entity) {
		if (entity.getId() > 0) return this.update(entity);
		else return this.insert(entity);
	}

}
