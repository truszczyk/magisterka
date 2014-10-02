package models;

import javax.persistence.Entity;
import javax.persistence.Id;

import play.data.validation.Constraints;
import play.db.ebean.Model;

@Entity
public class Doctor extends Model{

	@Id
	private Long id;
	
	private String PWZ;
	
	private String descpription; 
	
	private Specialization specialization;
	
	public enum Specialization {
			Alergologia,
			Analityka_Medyczna,
			Chirurgia_Dziecięca,Chirurgia_Ogólna,Chirurgia_Plastyczna,Choroby_Wewnętrzne,Choroby_Zakaźne,Dermatologia_I_Wenerologia,Endokrynologia,Geriatria,Hematologia,Hepatologia,Kardiochirurgia,Kardiologia,Medycyna_Pracy,Medycyna_Sądowa,Neonatologia,Neurochirurgia,Neurologia,Neurologia_Dziecięca,Okulistyka,Onkologia_Kliniczna,Otolaryngologia,Patomorfologia,Pediatria,Psychiatria,Radiologia,Rehabilitacja_Medyczna,Reumatologia,Stomatologia,Urologia;
	    }
	
}
