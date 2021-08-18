package fr.formation.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.formation.model.Adresse;

public interface IAdresseDao extends JpaRepository<Adresse, Integer>{

}
