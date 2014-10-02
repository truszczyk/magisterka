package models;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;

import play.db.ebean.Model;

@Entity
public class Chat extends Model{
	@Id
	private Long id;
	
	private Long doctorId;
	
	private Date dateStart;
	
	private Date dateEnd;
	

}
