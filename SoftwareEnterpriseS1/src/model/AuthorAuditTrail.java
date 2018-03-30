package model;

import java.util.Date;

public class AuthorAuditTrail {
	
	int id;
	private String message;
	private Date dateAdded;
	private int AuthorID;
	
	public AuthorAuditTrail() {
		
		this.id = 0;
		this.AuthorID = 0;
		this.message = null;
		this.dateAdded = null;
		
	}

	public AuthorAuditTrail(int id, int AuthorID, Date dateAdded, String message){
		this.id = id;
		this.AuthorID = AuthorID;
		this.message = message;
		this.dateAdded = dateAdded;
	}

	@Override
	public String toString() {
		return " " + dateAdded + " " + message ;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Date getDateAdded() {
		return dateAdded;
	}

	public void setDateAdded(Date dateAdded) {
		this.dateAdded = dateAdded;
	}

	public void setAuthorID(int id) {
		// TODO Auto-generated method stub
		this.AuthorID = id;
		
	}

}

