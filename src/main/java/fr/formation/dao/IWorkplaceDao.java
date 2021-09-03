package fr.formation.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import fr.formation.model.Workplace;

public interface IWorkplaceDao extends JpaRepository<Workplace, Integer>{

	@Query("select w from Workplace w where w.id not in (select p.id from Prestation p)")
	public List<Workplace> findAllWorkplaces();
}
