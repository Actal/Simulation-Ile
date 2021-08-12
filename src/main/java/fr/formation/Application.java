package fr.formation;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;

import fr.formation.dao.IAdresseDao;
import fr.formation.dao.IBiomeDao;
import fr.formation.dao.ICoordonneesDao;
import fr.formation.dao.IHabitationDao;
import fr.formation.dao.IMetierDao;
import fr.formation.dao.IPersonneDao;
import fr.formation.dao.IPosteDao;
import fr.formation.dao.IWorkplaceDao;
import fr.formation.dao.jpa.AdresseDaoJpa;
import fr.formation.dao.jpa.BiomeDaoJpa;
import fr.formation.dao.jpa.CoordonneesDaoJpa;
import fr.formation.dao.jpa.HabitationDaoJpa;
import fr.formation.dao.jpa.MetierDaoJpa;
import fr.formation.dao.jpa.PersonneDaoJpa;
import fr.formation.dao.jpa.PosteDaoJpa;
import fr.formation.dao.jpa.WorkplaceDaoJpa;
import fr.formation.model.*;

public class Application {

	public static void main(String[] args) {
		IAdresseDao daoA = new AdresseDaoJpa();
		IBiomeDao daoB = new BiomeDaoJpa();
		ICoordonneesDao daoC = new CoordonneesDaoJpa();
		IHabitationDao daoH = new HabitationDaoJpa();
		IMetierDao daoM = new MetierDaoJpa();
		IPersonneDao daoP = new PersonneDaoJpa();
		IPosteDao daoPoste = new PosteDaoJpa();
		IWorkplaceDao daoW = new WorkplaceDaoJpa();
				
		Personne p1 = new Personne("Bruno", "Aimeric", LocalDate.of(2010,Month.FEBRUARY,10), new BigDecimal(100));
		Personne p2 = new Personne("Luck", "Romain", LocalDate.of(2008,Month.FEBRUARY,15), new BigDecimal(10000));
		Personne p3 = new Personne("Schickele", "Pierre", LocalDate.of(2010,Month.FEBRUARY,10), new BigDecimal(646));
		
		Coordonnees c1 = new Coordonnees(10,10);
		Coordonnees c2 = new Coordonnees(45,456);
		
		Adresse a1 = new Adresse("Paix", 45);
		a1.setCoordonnees(c1);
		Adresse a2 = new Adresse("Paix", 46);
		a2.setCoordonnees(c2);
		
		Biome b = new Biome("plaine", new BigDecimal(1666));
		b.setCoordonnees(c2); 
		
		Habitation h1 = new Habitation(a1, new BigDecimal(45), b, 4, new BigDecimal(50));
		Workplace w1 = new Workplace(a2, new BigDecimal(45), b, LocalTime.of(9, 0), LocalTime.of(17, 0));
		
		Metier m1 = new Metier("Informaticien");
		
		p1.setHabitation(h1);
		p2.setHabitation(h1);
		p3.setHabitation(h1);
		
		Poste poste1 = new Poste(new BigDecimal(100));
		Poste poste2 = new Poste(new BigDecimal(150));
		Poste poste3 = new Poste(new BigDecimal(200));
		
		poste1.setMetier(m1);
		poste2.setMetier(m1);
		poste3.setMetier(m1);
		
		p1.setPoste(poste1);
		p2.setPoste(poste2);
		p3.setPoste(poste3);
		
		poste1.setWorkplace(w1);
		poste2.setWorkplace(w1);
		poste3.setWorkplace(w1);
		
		daoC.save(c1);
		daoC.save(c2);
		
		daoA.save(a1);
		daoA.save(a2);
		
		daoB.save(b);
		
		daoH.save(h1);
		daoW.save(w1);
		
		daoM.save(m1);

		daoPoste.save(poste1);
		daoPoste.save(poste2);
		daoPoste.save(poste3);
		
		daoP.save(p1);
		daoP.save(p2);
		daoP.save(p3);
	}

}
