package fr.formation.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.formation.model.Batiment;

public interface IBatimentDao extends JpaRepository<Batiment, Integer>{

}
