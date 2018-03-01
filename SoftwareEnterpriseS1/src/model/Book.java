package model;

import java.time.LocalDate;
import java.util.Calendar;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import views.AppMain;

public class Book{
	
	int id;
	private SimpleStringProperty title;
	private SimpleStringProperty summary;
	private SimpleIntegerProperty yearPublished;	
	private SimpleStringProperty isbn;
	private SimpleObjectProperty<Publisher> publisher;
	private SimpleObjectProperty<LocalDate> dateAdded;
	
	
	public Book(String title) {
		this.title = new SimpleStringProperty(title);
		this.summary = new SimpleStringProperty(null);
		this.yearPublished = new SimpleIntegerProperty(2018); 
		this.isbn = new SimpleStringProperty("un-assigned");
		this.publisher = new SimpleObjectProperty<Publisher>(new Publisher());
	}
	
	public Book() {
		this("Untitled Book");
	}	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title.get();
	}

	
	public void setTitle(String title) {
		this.title.set(title);;
	}
	public boolean titleValidate(String titleEntered) {
		boolean result = true;
		if(titleEntered.length() < 1 || titleEntered.length() > 255){
			result = false;
		}
		return result;
	}
	
	
	public String getSummary() {
		return summary.get();
	}

	public void setSummary(String summary) {
		this.summary.set(summary);;
	}
	public boolean summaryValidate() {
		boolean result = true;
		if(this.summary.get().length() < 65536) {
			result = false;
		}
		return result;
	}
	
	
	public int getYearPublished() {
		return yearPublished.get();
	}

	public void setYearPublished(int yearPublished) {
		this.yearPublished.set(yearPublished);
	}
	public boolean yearValidate() {
		boolean result = true;
		int yearCurr = Calendar.getInstance().get(Calendar.YEAR);
		if(this.yearPublished.get() > yearCurr) {
			result = false;
		}
		return result;
	}
	
	
	public String getIsbn() {
		return isbn.get();
	}

	public void setIsbn(String isbn) {
		this.isbn.set(isbn);
	}
	public boolean isbnValidate() {
		boolean result = true;
		if(this.isbn.get().length() > 13) {
			result = false;
		}
		return result;
	}
	
	
	public Publisher getPublisher() {
		return publisher.getValue();
	}

	public void setPublisher(Publisher publisher) {
		this.publisher.setValue(publisher);
	}

	public LocalDate getDateAdded() {
		return dateAdded.get();
	}

	public void setDateAdded(LocalDate dateAdded) {
		this.dateAdded.setValue(dateAdded);
	}
	/*
	public void Save(Book book) throws Exception {
		
		
		if(this.titleValidate(title) == false)
			throw new Exception("Validation failed");
		if(this.summaryValidate(summary) == false)
			throw new Exception("Validation failed");
		if(this.yearValidate(year) == false)
			throw new Exception("Validation failed");
		if(this.isbnValidate(isbn) == false)
			throw new Exception("Validation failed");
		
		if(id != 0){
			AppMain.bookGateway.updateBook(this);
		}
		else
			AppMain.bookGateway.insertBook(this);
	    
	}
	*/
	public String toString() {
		return this.title.get();
	}

	public SimpleStringProperty titleproperty() {
		return this.title;
	}

	public SimpleStringProperty isbnproperty() {
		return this.isbn;
	}

	public SimpleIntegerProperty yearPublishedProperty() {
		return this.yearPublished ;
	}
	public SimpleObjectProperty<LocalDate> dateAddedProperty() {
		return this.dateAdded;
	}

}