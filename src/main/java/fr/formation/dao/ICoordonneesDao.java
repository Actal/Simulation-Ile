package fr.formation.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.formation.model.Coordonnees;

public interface ICoordonneesDao extends JpaRepository<Coordonnees, Integer>{

}
