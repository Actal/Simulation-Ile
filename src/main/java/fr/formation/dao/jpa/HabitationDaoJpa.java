package fr.formation.dao.jpa;

import java.util.List;

import fr.formation.dao.IHabitationDao;
import fr.formation.model.Habitation;

public class HabitationDaoJpa extends AbstractDaoJpa <Habitation> implements IHabitationDao{

	@Override
	public List<Habitation> findAll() {
		return em.createQuery("SELECT h FROM Habitation h", Habitation.class)
				.getResultList();
	}

	@Override
	public Habitation findById(Integer id) {
		return em.find(Habitation.class, id);
	}

	@Override
	public Habitation save(Habitation entity) {
		if (entity.getId() > 0) return this.update(entity);
		else return this.insert(entity);
	}

}
