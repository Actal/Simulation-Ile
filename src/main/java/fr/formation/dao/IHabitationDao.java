package fr.formation.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.formation.model.Habitation;

public interface IHabitationDao extends JpaRepository<Habitation, Integer>{

}
