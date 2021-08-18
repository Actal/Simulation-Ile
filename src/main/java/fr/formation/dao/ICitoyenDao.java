package fr.formation.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.formation.model.Citoyen;

public interface ICitoyenDao extends JpaRepository<Citoyen, Integer>{

}
