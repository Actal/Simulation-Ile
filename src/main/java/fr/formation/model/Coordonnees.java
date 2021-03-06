package fr.formation.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonView;

import fr.formation.api.Views;

@Entity
@Table (name="coordonnees")
public class Coordonnees {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "COO_ID")
	@JsonView(Views.Commons.class)
	private int id;
	
	@Column(name = "COO_X")
	@JsonView(Views.Commons.class)
	private int x;
	
	@Column(name = "COO_Y")
	@JsonView(Views.Commons.class)
	private int y;
	
	public Coordonnees() {
	}
	
	public Coordonnees(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public int getX() {
		return x;
	}
	
	public void setX(int x) {
		this.x = x;
	}
	
	public int getY() {
		return y;
	}
	
	public void setY(int y) {
		this.y = y;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
}
