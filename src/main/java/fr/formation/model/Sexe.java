package fr.formation.model;

public enum Sexe {
	Homme,
	Femme;
	
	public boolean isHomme() {
		return this == Homme;
	}
	
	public boolean isFemme() {
		return this == Femme;
	}
}
