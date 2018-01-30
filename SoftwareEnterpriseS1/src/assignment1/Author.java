package assignment1;
import javafx.beans.property.SimpleStringProperty;

public class Author {
	
	private SimpleStringProperty authorFirst;
	private SimpleStringProperty authorDOB;
	private SimpleStringProperty authorwebsite;
	private SimpleStringProperty authorLast;
	private SimpleStringProperty authorGender;
	
	public Author() {
		authorFirst = new SimpleStringProperty();
		authorDOB = new SimpleStringProperty();
		authorwebsite = new SimpleStringProperty();
		authorLast = new SimpleStringProperty();
		authorGender = new SimpleStringProperty();
		
	}

	public String getAuthorDOB() {
		return authorDOB.get();
	}

	public void setAuthorDOB(String authorDOB) {
		this.authorDOB.set(authorDOB);
	}

	public String getAuthorwebsite() {
		return authorwebsite.get();
	}

	public void setAuthorwebsite(String authorwebsite) {
		this.authorwebsite.set(authorwebsite);
	}

	public String getAuthorLast() {
		return authorLast.get();
	}

	public void setAuthorLast(String authorLast) {
		this.authorLast.set(authorLast);
	}

	public String getAuthorGender() {
		return authorGender.get();
	}

	public void setAuthorGender(String authorGender) {
		this.authorGender.set(authorGender);;
	}

	public void setAuthorFirst(String authorFirst) {
		this.authorFirst.set(authorFirst);
	}
	public Author(String authorFirst, String authorDOB, String authorwebsite,String authorLast,String authorGender) {
		this();
		this.authorFirst.set(authorFirst);
		this.authorDOB.set(authorDOB);
		this.authorwebsite.set(authorwebsite);
		this.authorLast.set(authorLast);
		this.authorGender.set(authorGender);
		
		
	}
	


	public String getAuthorFirst() {
		// TODO Auto-generated method stub
		return this.authorFirst.get();
	}
	@Override
	public String toString() {
		return  getAuthorFirst() + " " + getAuthorLast() + " ";
	}
	
}
