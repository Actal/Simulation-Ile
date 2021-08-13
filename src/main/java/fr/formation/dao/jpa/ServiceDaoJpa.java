package fr.formation.dao.jpa;

import java.util.List;

import fr.formation.dao.IProprietaireDao;
import fr.formation.model.Service;

public class ServiceDaoJpa extends AbstractDaoJpa <Service> implements IServiceDao{

	@Override
	public List<Service> findAll() {
		return em.createQuery("SELECT s FROM Service s", Service.class)
				.getResultList();
	}

	@Override
	public Service findById(Integer id) {
		return em.find(Service.class, id);
	}

	@Override
	public Service save(Service entity) {
		if (entity.getId() > 0) return this.update(entity);
		else return this.insert(entity);
	}

}
