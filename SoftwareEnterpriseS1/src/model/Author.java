package model;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import views.AppMain;

public class Author {
	private static Logger logger = LogManager.getLogger();
	
	private int id;
	private String lastName;
	private String dob;
	private String gender;
	private String webSite;
	private String firstName;
	
	public Author(){
		this.id = 0;
		this.firstName = "";
		this.lastName = "";
		this.dob = "";
		this.gender = "";
		this.webSite = "";
	}
	
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

	
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public boolean idValidate(int id){
		if(this.id < 0){
			return false;
		}
		else
			return true;
	}
	
	public String getFirst_name() {
		return firstName;
	}

	public void setFirst_name(String first_name) {
		this.firstName = first_name;
	}
	
	public boolean fnameValidate(String first_name){
		if(this.firstName == "" || this.firstName.length() > 100)
			return false;
		else 
			return true;
	}

	public String getLast_name() {
		return lastName;
	}

	public void setLast_name(String last_name) {
		this.lastName = last_name;
	}
	
	public boolean lnameValidate(String last_name){
		if(this.lastName == "" || this.lastName.length() > 100)
			return false;
		else
			return true;
	}

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}
	
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

	
	
	
}
