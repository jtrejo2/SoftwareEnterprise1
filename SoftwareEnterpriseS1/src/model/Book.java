package model;

import java.time.LocalDate;
import java.util.Calendar;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import views.AppMain;

public class Book{
	
	int id;
	private String title;
	private String summary;
	private int yearPublished;	
	private String isbn;
	private Publisher publisher;
	private LocalDate dateAdded;
	
	public Book() {
		this.id = 0;
		this.title = null;
		this.summary = null;
		this.yearPublished = 0;
		this.isbn= null;
		this.publisher = null;
		this.dateAdded = null;
	}	
	
	public Book(String title, String summary, int yearPublished, String isbn, LocalDate dateAdded) {
		this.id = 0;
		this.idValidate(id);
		
		this.title = title;
		this.titleValidate(title);
		
		this.summary = null;
		this.summaryValidate();
		
		this.yearPublished = 2018; 
		this.yearValidate();
		
		this.isbn = "unassigned";
		this.isbnValidate();
		
		this.publisher = publisher;
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
	
	public String getTitle() {
		return title;
	}

	
	public void setTitle(String title) {
		this.title = title;
	}
	public boolean titleValidate(String titleEntered) {
		boolean result = true;
		if(titleEntered.length() < 1 || titleEntered.length() > 255){
			result = false;
		}
		return result;
	}
	
	
	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}
	public boolean summaryValidate() {
		boolean result = true;
		if(this.summary.length() < 65536) {
			result = false;
		}
		return result;
	}
	
	
	public int getYearPublished() {
		return yearPublished;
	}

	public void setYearPublished(int yearPublished) {
		this.yearPublished = yearPublished;
	}
	public boolean yearValidate() {
		boolean result = true;
		int yearCurr = Calendar.getInstance().get(Calendar.YEAR);
		if(this.yearPublished > yearCurr) {
			result = false;
		}
		return result;
	}
	
	
	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	public boolean isbnValidate() {
		boolean result = true;
		if(this.isbn.length() > 13) {
			result = false;
		}
		return result;
	}
	
	
	public Publisher getPublisher() {
		return publisher;
	}

	public void setPublisher(Publisher publisher) {
		this.publisher = publisher;
	}
	

	public LocalDate getDateAdded() {
		return dateAdded;
	}

	public void setDateAdded(LocalDate dateAdded) {
		this.dateAdded = dateAdded;
	}
	
	public void Save(Book book) throws Exception {
		
		
		if(this.titleValidate(title) == false)
			throw new Exception("Validation failed");
		if(this.summaryValidate() == false)
			throw new Exception("Validation failed");
		if(this.yearValidate() == false)
			throw new Exception("Validation failed");
		if(this.isbnValidate() == false)
			throw new Exception("Validation failed");
		
		if(id != 0){
			AppMain.bookGateway.updateBook(this);
		}
		else
			AppMain.bookGateway.insertBook(this);
	    
	}
	
	@Override
	public String toString() {
		return this.title;
	}


}