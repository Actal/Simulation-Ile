package fr.formation.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.formation.model.SimulationEtat;


public interface ISimEtatDao extends JpaRepository<SimulationEtat, Integer>{
    
}
