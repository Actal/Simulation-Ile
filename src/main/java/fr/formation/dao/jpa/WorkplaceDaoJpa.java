package fr.formation.dao.jpa;

import java.util.List;

import fr.formation.dao.IWorkplaceDao;
import fr.formation.model.Workplace;

public class WorkplaceDaoJpa extends AbstractDaoJpa <Workplace> implements IWorkplaceDao{

	@Override
	public List<Workplace> findAll() {
		return em.createQuery("SELECT w FROM Workplace w", Workplace.class)
				.getResultList();
	}

	@Override
	public Workplace findById(Integer id) {
		return em.find(Workplace.class, id);
	}

	@Override
	public Workplace save(Workplace entity) {
		if (entity.getId() > 0) return this.update(entity);
		else return this.insert(entity);
	}

}
