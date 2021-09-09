package fr.formation.dao;

import java.math.BigDecimal;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import fr.formation.model.Personne;

public interface IPersonneDao extends JpaRepository<Personne, Integer>{

	@Query("select sum(p.argent) from Personne p")
	public BigDecimal countArgentTotal();
}
