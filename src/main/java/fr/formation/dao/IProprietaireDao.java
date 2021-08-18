package fr.formation.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.formation.model.Proprietaire;

public interface IProprietaireDao extends JpaRepository<Proprietaire, Integer>{

}
