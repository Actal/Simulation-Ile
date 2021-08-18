package fr.formation.config;


import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement //Activer l'annotation @Transactional
@EnableJpaRepositories("fr.formation.dao") //Activer les repositories DATA-JPA
public class JpaConfig {
	//Ici, configuration des 3 beans DataSource, EntityManagerFactory & TransactionManager
	
	//CONFIGURATION DATASOURCE
	@Bean //Donner le résultat de cette méthode au contexte de SPRING
	public DataSource dataSource() {
		BasicDataSource dataSource = new BasicDataSource();

		dataSource.setDriverClassName("org.postgresql.Driver"); //Pilote JDBC (postgre)
		dataSource.setUrl("jdbc:postgresql://127.0.0.1:5432/Simulation-Ile"); //URL SQL (postgre)
		dataSource.setUsername("postgres"); //Username SQL
		dataSource.setPassword("postgres"); //Mot de passe SQL
		dataSource.setMaxTotal(10); //Nombre total de connexions SQL simultanées
		
		return dataSource;
	}
	
	//CONFIGURATION DE L'ENTITYMANAGERFACTORY
	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource) {
		//Container EntityManagerFactory
		LocalContainerEntityManagerFactoryBean emf = new LocalContainerEntityManagerFactoryBean();
		
		//Fournisseur JPA : Hibernate
		JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
		
		//Configurer l'emf
		emf.setDataSource(dataSource);
		emf.setPackagesToScan("fr.formation.model");
		emf.setJpaVendorAdapter(vendorAdapter);
		
		//Configurer les propriétés liés au vendor, ici à Hibernate
		Properties hibernateProperties = new Properties();
		
//		hibernateProperties.setProperty("propertyName", "propertyValue");
		hibernateProperties.setProperty("hibernate.hbm2ddl.auto", "update");
		hibernateProperties.setProperty("hibernate.show_sql", "true");
		hibernateProperties.setProperty("hibernate.format_sql", "false");
		
		//Associer ces properties à l'emf
		emf.setJpaProperties(hibernateProperties);
		
		
		return emf;
	}
	
	
	//CONFIGURATION TRANSACTIONMANAGER
	@Bean
	public JpaTransactionManager transactionManager(EntityManagerFactory emf) {
		JpaTransactionManager transactionManager = new JpaTransactionManager();
		
		//Associer emf au gestionnaire de transactions
		transactionManager.setEntityManagerFactory(emf);
		
		return transactionManager;
	}
}