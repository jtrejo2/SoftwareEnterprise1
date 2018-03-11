package model;

//import java.sql.Date;
//import java.time.LocalDate;
import java.util.Date;

public class AuditTrail {
	
	int id;
	private String message;
	private Date dateAdded;
	private int bookID;
	
	public AuditTrail() {
		
		this.id = 0;
		this.bookID = 0;
		this.message = null;
		this.dateAdded = null;
		
	}

	public AuditTrail(int id, int bookID, Date dateAdded, String message){
		this.id = id;
		this.bookID = bookID;
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

	public void setBookId(int id) {
		// TODO Auto-generated method stub
		this.bookID = id;
		
	}

}
