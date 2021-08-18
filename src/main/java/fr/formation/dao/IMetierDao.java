package fr.formation.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.formation.model.Metier;

public interface IMetierDao extends JpaRepository<Metier, Integer>{

}
