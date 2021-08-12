package fr.formation.dao.jpa;

import java.util.List;

import fr.formation.dao.IAdresseDao;
import fr.formation.model.Adresse;

public class AdresseDaoJpa extends AbstractDaoJpa <Adresse> implements IAdresseDao{

	@Override
	public List<Adresse> findAll() {
		return em.createQuery("SELECT a FROM Adresse a", Adresse.class)
				.getResultList();
	}

	@Override
	public Adresse findById(Integer id) {
		return em.find(Adresse.class, id);
	}

	@Override
	public Adresse save(Adresse entity) {
		if (entity.getId() > 0) return this.update(entity);
		else return this.insert(entity);
	}

}
