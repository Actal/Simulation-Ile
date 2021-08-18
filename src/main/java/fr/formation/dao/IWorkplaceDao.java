package fr.formation.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.formation.model.Workplace;

public interface IWorkplaceDao extends JpaRepository<Workplace, Integer>{

}
