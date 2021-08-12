package fr.formation.dao;

import java.util.List;

// T & Id = templates, types generiques
// Template : evite d'avoir un type Object puisqu'on ne connait pas le type concret

public interface IDAO<T, Id> {
	public List<T> findAll();
	public T findById(Id id);

	public T insert(T entity);
	public T update(T entity);
	public T save(T entity);

	public void delete(T entity);
	public void deletebyId(Id id);
}
