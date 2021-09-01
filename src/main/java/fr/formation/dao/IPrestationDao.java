package fr.formation.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.formation.model.Prestation;

public interface IPrestationDao extends JpaRepository<Prestation, Integer>{

}
