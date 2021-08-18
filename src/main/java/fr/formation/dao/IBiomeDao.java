package fr.formation.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.formation.model.Biome;

public interface IBiomeDao extends JpaRepository<Biome, Integer>{

}
