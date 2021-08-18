package fr.formation.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.formation.model.Service;

public interface IServiceDao extends JpaRepository<Service, Integer>{

}
