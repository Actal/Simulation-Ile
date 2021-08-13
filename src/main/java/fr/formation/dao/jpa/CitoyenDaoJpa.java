package fr.formation.dao.jpa;

import java.util.List;

import fr.formation.dao.ICitoyenDao;
import fr.formation.model.Citoyen;

public class CitoyenDaoJpa extends AbstractDaoJpa <Citoyen> implements ICitoyenDao{

	@Override
	public List<Citoyen> findAll() {
		return em.createQuery("SELECT c FROM Citoyen c", Citoyen.class)
				.getResultList();
	}

	@Override
	public Citoyen findById(Integer id) {
		return em.find(Adresse.class, id);
	}

	@Override
	public Citoyen save(Citoyen entity) {
		if (entity.getId() > 0) return this.update(entity);
		else return this.insert(entity);
	}

}
