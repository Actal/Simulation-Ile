package fr.formation.dao;

import java.math.BigDecimal;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import fr.formation.model.Proprietaire;

public interface IProprietaireDao extends JpaRepository<Proprietaire, Integer>{

	@Query("select sum(p.argent) from Proprietaire p")
	public BigDecimal countArgentTotal();
}
