package fr.formation.dao.jpa;

import java.util.List;

import fr.formation.dao.IBiomeDao;
import fr.formation.model.Biome;

public class BiomeDaoJpa extends AbstractDaoJpa <Biome> implements IBiomeDao{

	@Override
	public List<Biome> findAll() {
		return em.createQuery("SELECT b FROM Biome b", Biome.class)
				.getResultList();
	}

	@Override
	public Biome findById(Integer id) {
		return em.find(Biome.class, id);
	}

	@Override
	public Biome save(Biome entity) {
		if (entity.getId() > 0) return this.update(entity);
		else return this.insert(entity);
	}

}
