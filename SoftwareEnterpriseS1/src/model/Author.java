package model;

import java.util.List;

import javafx.beans.property.SimpleObjectProperty;

import java.time.LocalDateTime;

import views.AppMain;

public class Author {
	
	private int id;
	private String lastName;
	private String dob;
	private String gender;
	private String webSite;
	private String firstName;
	private LocalDateTime lastModified;
	
	//assign initial values
	public Author(){
		this.id = 0;
		this.firstName = "";
		this.lastName = "";
		this.dob = "";
		this.gender = "";
		this.webSite = "";
		lastModified = null;
	}
	//assign Author values
	public Author(int id, String first_name, String last_name, String dob, String gender, String web_site){
		this.id = id;
		this.idValidate(id);
		
		this.firstName = first_name;
		this.fnameValidate(first_name);
		
		this.lastName = last_name;
		this.lnameValidate(last_name);
		
		this.dob = dob;
		
		this.gender = gender;
		this.genderValidate(gender);
		
		this.webSite = web_site;
		this.webValidate(web_site);
		
		
	}

	
	//return Id
	public int getId() {
		return id;
	}
	//set Id
	public void setId(int id) {
		this.id = id;
	}
	//make sure Id is valid
	public boolean idValidate(int id){
		if(this.id < 0){
			return false;
		}
		else
			return true;
	}
	//return Author first name
	public String getFirst_name() {
		return firstName;
	}
	//set Author first name
	public void setFirst_name(String first_name) {
		this.firstName = first_name;
	}
	//validate author first name
	public boolean fnameValidate(String first_name){
		if(this.firstName == "" || this.firstName.length() > 100)
			return false;
		else 
			return true;
	}
	//retun author last name
	public String getLast_name() {
		return lastName;
	}
	//set author last name
	public void setLast_name(String last_name) {
		this.lastName = last_name;
	}
	//validate author last name
	public boolean lnameValidate(String last_name){
		if(this.lastName == "" || this.lastName.length() > 100)
			return false;
		else
			return true;
	}
	//return author dob
	public String getDob() {
		return dob;
	}
	//set author dob
	public void setDob(String dob) {
		this.dob = dob;
	}
	//return gender
	public String getGender() {
		return gender;
	}
	//set author gender
	public void setGender(String gender) {
		this.gender = gender;
	}
	//validate author gender
	public boolean genderValidate(String gender){
		if(this.gender.equals("M") || this.gender.equals("Male") || this.gender.equals("F") || this.gender.equals("Female") || this.gender.equals("Unknown"))
			return true;
		else
			return false;
	}

	public String getWeb_site() {
		return webSite;
	}

	public void setWeb_site(String web_site) {
		this.webSite = web_site;
	}
	
	public boolean webValidate(String web_site){
		if(this.webSite.length() > 100)
			return false;
		else
			return true;
	}
	
	public LocalDateTime getLastModified() {
		return lastModified;
	}

	public void setLastModified(LocalDateTime lastModified) {
		this.lastModified = lastModified;
	}
	
	//save author information after it is validated
	public void Save(Author author) throws Exception {
			
			
			if(this.idValidate(id) == false)
				throw new Exception("Validation failed");
			if(this.fnameValidate(firstName) == false)
				throw new Exception("Validation failed");
			if(this.lnameValidate(lastName) == false)
				throw new Exception("Validation failed");
			if(this.genderValidate(gender) == false)
				throw new Exception("Validation failed");
			if(this.webValidate(webSite) == false)
				throw new Exception("Validation failed");
			if(id != 0){
				AppMain.authorGateway.updateAuthor(this);
			}
			else
				AppMain.authorGateway.insertAuthor(this);
		    
	}

	@Override
	public String toString() {
		return id + " : " + firstName + " " + lastName + " : " + dob + " : " + gender + " : " + webSite; 
	}
	//public static List<AuthorAuditTrail> GetBookAuditTrail() {
		// TODO Auto-generated method stub
	//	return null;
	//}
	public List<AuthorAuditTrail> GetAuthorAuditTrail() {
		// TODO Auto-generated method stub
		 List<AuthorAuditTrail> AuditTrails = AppMain.authorGateway.GetAuthorAuditTrail(this);
		 for(AuthorAuditTrail a: AuditTrails) {
			 //System.out.println("this is a" + a);
		 }
		 return AuditTrails;
	}

}
