package fr.formation.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import fr.formation.model.Citoyen;
import fr.formation.model.Poste;

public interface ICitoyenDao extends JpaRepository<Citoyen, Integer>{
	
	@Query("select c from Citoyen c where c.id not in (select p.id from Proprietaire p)")
	public List<Citoyen> findAllCitoyens();

	public Citoyen findByPoste(Poste poste);
}
