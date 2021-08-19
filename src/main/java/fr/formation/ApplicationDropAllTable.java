package fr.formation;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.List;

public class ApplicationDropAllTable {

	public static void main(String[] args) {
		try
		{
			String[] tablesName = {
					"adresse",
					"batiment",
					"biome",
					"citoyen",
					"coordonnees",
					"habitation",
					"metier",
					"personne",
					"poste",
					"proprietaire",
					"service",
					"workplace"
					};
			
			//�tape 1: charger la classe driver
			Class.forName("org.postgresql.Driver");
			//�tape 2: cr�er l'objet de connexion
			Connection conn = DriverManager.getConnection(
					"jdbc:postgresql://127.0.0.1:5432/Simulation-Ile", "postgres", "a");
			//�tape 3: cr�er l'objet statement 
			Statement stmt = conn.createStatement();
			//�tape 4: ex�cuter la requ�te
			for(String name: tablesName) {
				System.out.println("Suppression de la table " + name + "...");
				stmt.executeUpdate("DROP TABLE " + name + " cascade");
				System.out.println("Table " + name + " supprim�e avec succ�s...");
			}
			//�tape 5: fermez l'objet de connexion
			conn.close();
		}
		catch(Exception e){ 
			System.out.println(e);
		}
	}
}
