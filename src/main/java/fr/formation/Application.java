package fr.formation;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

import fr.formation.config.AppConfig;
import fr.formation.dao.IAdresseDao;
import fr.formation.dao.IBiomeDao;
import fr.formation.dao.ICitoyenDao;
import fr.formation.dao.ICoordonneesDao;
import fr.formation.dao.IHabitationDao;
import fr.formation.dao.IMetierDao;
import fr.formation.dao.IPersonneDao;
import fr.formation.dao.IPosteDao;
import fr.formation.dao.IProprietaireDao;
import fr.formation.dao.IWorkplaceDao;
import fr.formation.model.Adresse;
import fr.formation.model.Biome;
import fr.formation.model.Citoyen;
import fr.formation.model.Coordonnees;
import fr.formation.model.Habitation;
import fr.formation.model.Metier;
import fr.formation.model.Personne;
import fr.formation.model.Poste;
import fr.formation.model.Proprietaire;
import fr.formation.model.Sexe;
import fr.formation.model.Workplace;
import fr.formation.service.ProprietaireService;

@Component
public class Application {

	@Autowired
	IAdresseDao daoAdresse;
	@Autowired
	IBiomeDao daoBiome;
	@Autowired
	ICitoyenDao daoCitoyen;
	@Autowired
	ICoordonneesDao daoCoordonnees;
	@Autowired
	IHabitationDao daoHabitation;
	@Autowired
	IMetierDao daoMetier;
	@Autowired
	IPersonneDao daoPersonne;
	@Autowired
	IPosteDao daoPoste;
	@Autowired
	IProprietaireDao daoProprietaire;
	@Autowired
	IWorkplaceDao daoWorkplace;
	
	@Autowired
	ProprietaireService proprioService; 
	
	public static void main(String[] args) {

		AnnotationConfigApplicationContext myContext = new AnnotationConfigApplicationContext(AppConfig.class);
		
		Application app = myContext.getBean(Application.class);
		
		app.createEntity();
		app.printEntity();
		app.testBenefice();
		
		myContext.close();

	}

	public void testBenefice() {
		System.out.println("\n=========================\n");
		
		
		for(int i=0; i<3; i++) {
		
			for( Personne p : daoPersonne.findAll() ) {
				System.out.println("Personne (" + p.getId() +"): " + p.getNom() + " " + p.getPrenom() + " " + p.getArgent());
			}
			
			proprioService.payerEmployes(1);
			proprioService.percevoirBenefice(1);
			
			System.out.println("=========");
		}
		
		for( Personne p : daoPersonne.findAll() ) {
			System.out.println("Personne (" + p.getId() +"): " + p.getNom() + " " + p.getPrenom() + " " + p.getArgent());
		}
	}
	
	public void printEntity() {
		for (Poste p : daoPoste.findAll()) {
			System.out.println("Poste (" + p.getId() + "): " + p.getSalaire());
		}

		for (Habitation h : daoHabitation.findAll()) {
			System.out.println("Habitation (" + h.getId() + "): " + h.getLoyer());
		}

		for (Workplace w : daoWorkplace.findAll()) {
			System.out.println("Workplace w (" + w.getId() + "): " + w.getCoutEntretienBase());
		}
	}
	
	public void createEntity() {
		Citoyen citoyen1 = new Citoyen("Bruno", "Aimeric", LocalDate.of(2010, Month.FEBRUARY, 10), Sexe.Homme,
				new BigDecimal(100));
		Citoyen citoyen2 = new Citoyen("Luck", "Romain", LocalDate.of(2008, Month.FEBRUARY, 15), Sexe.Homme,
				new BigDecimal(10000));
		Citoyen citoyen3 = new Citoyen("Schickele", "Pierre", LocalDate.of(2010, Month.FEBRUARY, 10), Sexe.Homme,
				new BigDecimal(646));

		Proprietaire proprio = new Proprietaire("Truc", "Machin", LocalDate.of(1985, Month.FEBRUARY, 10), Sexe.Femme,
				new BigDecimal(1000000));

		Coordonnees coor1 = new Coordonnees(10, 10);
		Coordonnees coor2 = new Coordonnees(45, 456);

		Adresse adr1 = new Adresse("rue de la Paix", 45);
		adr1.setCoordonnees(coor1);
		Adresse adr2 = new Adresse("avenue des champs elysees", 46);
		adr2.setCoordonnees(coor2);

		Biome b = new Biome("plaine", new BigDecimal(1666));
		b.setCoordonnees(coor2);

		Habitation h1 = new Habitation(new BigDecimal(45), "Maison", new BigDecimal(1000), new BigDecimal(100), 4, adr1,
				b, proprio, new BigDecimal(50));

		citoyen1.setHabitation(h1);
		citoyen2.setHabitation(h1);
		citoyen3.setHabitation(h1);

		Workplace w1 = new Workplace(new BigDecimal(45), "Maison", new BigDecimal(1000), new BigDecimal(100), 4, adr2,
				b, proprio, LocalTime.of(9, 0), LocalTime.of(18, 0));

		Metier m1 = new Metier("Informaticien");

		Poste poste1 = new Poste(new BigDecimal(100));
		Poste poste2 = new Poste(new BigDecimal(150));
		Poste poste3 = new Poste(new BigDecimal(200));

		poste1.setMetier(m1);
		poste2.setMetier(m1);
		poste3.setMetier(m1);

		citoyen1.setPoste(poste1);
		citoyen2.setPoste(poste2);
		citoyen3.setPoste(poste3);

		poste1.setWorkplace(w1);
		poste2.setWorkplace(w1);
		poste3.setWorkplace(w1);

		daoCoordonnees.save(coor1);
		daoCoordonnees.save(coor2);

		daoAdresse.save(adr1);
		daoAdresse.save(adr2);

		daoBiome.save(b);

		daoProprietaire.save(proprio);

		daoHabitation.save(h1);
		daoWorkplace.save(w1);

		daoMetier.save(m1);

		daoPoste.save(poste1);
		daoPoste.save(poste2);
		daoPoste.save(poste3);

		daoCitoyen.save(citoyen1);
		daoCitoyen.save(citoyen2);
		daoCitoyen.save(citoyen3);
	}
}
