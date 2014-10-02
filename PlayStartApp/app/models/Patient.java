package models;

import javax.persistence.Entity;
import javax.persistence.Id;

import play.db.ebean.Model;

@Entity
public class Patient extends Model{
	
	public Patient(String pesel) {
		super();
		this.pesel = pesel;
	}
	@Id
	private Long id;
	
	public String getPesel() {
		return pesel;
	}
	private String pesel;
	
}
