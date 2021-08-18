package fr.formation.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.formation.model.Poste;

public interface IPosteDao extends JpaRepository<Poste, Integer>{

}
