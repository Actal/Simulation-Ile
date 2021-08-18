package fr.formation.service;

import java.math.BigDecimal;

public abstract class BatimentService {

	public abstract BigDecimal valeurEntretien(int id);
	
	public abstract BigDecimal valeurBenefice(int id);
	
}
