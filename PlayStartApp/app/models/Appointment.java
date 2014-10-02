package models;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;

import org.joda.time.DateTime;

import play.db.ebean.Model;

@Entity
public class Appointment extends Model{
	@Id
	private Long id;
	
	private Long doctorId;
	
	private Long patientId;
	
	private Date dateStart;
	
	private Date dateEnd;
	
	private int doctorRate;

}
