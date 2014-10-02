package models;

import models.utils.AppException;
import models.utils.Hash;
import play.data.format.Formats;
import play.data.validation.Constraints;
import play.db.ebean.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import java.util.Date;

/**
 * User: yesnault
 * Date: 20/01/12
 */
@Entity
public class UserApp extends Model {

    public void setEmail(String email) {
		this.email = email;
	}

	public UserApp(String email, String fName, String lName,
			String confirmationToken, String passwordHash) {
		super();
		this.email = email;
		this.fName = fName;
		this.lName = lName;
		this.confirmationToken = confirmationToken;
		this.passwordHash = passwordHash;
		this.dateCreated = new Date();
		
	}



	@Id
	@GeneratedValue
    private Long id;

    @Constraints.Required
    @Formats.NonEmpty
    @Column(unique = true)
    private String email;

    @Constraints.Required
    @Formats.NonEmpty
    private String passwordHash;
    
    @Constraints.Required
    @Formats.NonEmpty
    private String fName;
    

    @Constraints.Required
    @Formats.NonEmpty
    private String lName;
    
    public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}
	
	@OneToOne
	private Doctor doctor;

	@OneToOne
    private Patient patient;
    
    private String confirmationToken;
    
    private String phoneNr;
    
    public void setPhoneNr(String phoneNr) {
		this.phoneNr = phoneNr;
	}

	
	private String address;
	
	private String photoUrl;
	
	public void setAddress(String address) {
		this.address = address;
	}

    @Formats.DateTime(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date dateCreated;

    @Formats.NonEmpty
    private boolean validated;

    public Long getId() {
		return id;
	}

	public String getfName() {
		return fName;
	}

	public String getlName() {
		return lName;
	}

	public String getEmail() {
		return email;
	}

	public String getConfirmationToken() {
		return confirmationToken;
	}
	

	public boolean isValidated() {
		return validated;
	}



	// -- Queries (long id, user.class)
    public static Model.Finder<Long, UserApp> find = new Model.Finder<Long, UserApp>(Long.class, UserApp.class);

    /**
     * Retrieve a user from an email.
     *
     * @param email email to search
     * @return a user
     */
    public static UserApp findByEmail(String email) {
        return find.where().eq("email", email).findUnique();
    }
//TODO remove
    /**
     * Retrieve a user from a fullname.
     *
     * @param fullname Full name
     * @return a user
     */
    public static UserApp findByFullname(String fullname) {
        return null;
    }

    /**
     * Retrieves a user from a confirmation token.
     *
     * @param token the confirmation token to use.
     * @return a user if the confirmation token is found, null otherwise.
     */
    public static UserApp findByConfirmationToken(String token) {
        return find.where().eq("confirmationToken", token).findUnique();
    }

    /**
     * Authenticate a User, from a email and clear password.
     *
     * @param email         email
     * @param clearPassword clear password
     * @return User if authenticated, null otherwise
     * @throws AppException App Exception
     */
    public static UserApp authenticate(String email, String clearPassword) throws AppException {

        // get the user with email only to keep the salt password
        UserApp user = find.where().eq("email", email).findUnique();
        if (user != null) {
            // get the hash password from the salt + clear password
            if (Hash.checkPassword(clearPassword, user.getPasswordHash())) {
                return user;
            }
        }
        return null;
    }

    public String getPasswordHash() {
		return passwordHash;
	}

	public void changePassword(String password) throws AppException {
        this.passwordHash = Hash.createPassword(password);
        this.save();
    }

    /**
     * Confirms an account.
     *
     * @return true if confirmed, false otherwise.
     * @throws AppException App Exception
     */
    public static boolean confirm(UserApp user) throws AppException {
        if (user == null) {
            return false;
        }

        user.setConfirmationToken(null);
        user.setValidated(true);
        user.save();
        return true;
    }

	public void setConfirmationToken(String confirmationToken) {
		this.confirmationToken = confirmationToken;
	}

	public void setValidated(boolean validated) {
		this.validated = validated;
	}

}
