package fr.formation.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.formation.model.Personne;

public interface IPersonneDao extends JpaRepository<Personne, Integer>{

}
