package models;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;

import play.db.ebean.Model;

@Entity
public class Message extends Model{
	@Id
	private Long id;
	
	private Long appointmentId;
	
	private Long userFromId;
	
	private Long userToId;
	
	private Date dateCreated;
	
	private Boolean read;
	
	private String content;
	
	

}
