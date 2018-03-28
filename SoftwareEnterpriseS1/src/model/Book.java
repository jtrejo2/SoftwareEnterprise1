package model;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.List;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import views.AppMain;

public class Book{
	
	int id;
	private String title;
	private String summary;
	private int yearPublished;	
	private String isbn;
	private Publisher publisher;
	private LocalDate dateAdded;
	private ObservableList<AuthorBook> authorBooks;

	
	public Book() {
		this.id = 0;
		this.title = null;
		this.summary = null;
		this.yearPublished = 0;
		this.isbn = null;
		this.dateAdded = null;
		this.publisher = null;
		this.authorBooks = FXCollections.observableArrayList();
	}	
	
	public Book(int id, String title, String summary, int yearPublished, String isbn, LocalDate dateAdded, Publisher publisher) {
		
		this.id = id;
		this.idValidate(id);
		
		this.title = title;
		this.titleValidate(title);
		
		this.summary = summary;
		
		this.yearPublished = yearPublished; 
		this.yearValidate();
		
		this.isbn = isbn;
		this.isbnValidate();
		
		this.publisher = publisher;
		
		this.authorBooks = FXCollections.observableArrayList();
		
	
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
		if(this.summary.length() > 65536) {
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
	
	public int getPublisherId() {
		return publisher.getId();
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
			throw new Exception("TITLE Validation failed");
		if(this.summaryValidate() == false)
			throw new Exception("SUMMARY Validation failed");
		if(this.yearValidate() == false)
			throw new Exception("YEAR Validation failed");
		if(this.isbnValidate() == false)
			throw new Exception("ISBN Validation failed");
		
		if(id != 0){
			AppMain.bookGateway.updateBook(this);
		}
		else
			AppMain.bookGateway.insertBook(this);
	    
	}
	
	public void saveAuthor(AuthorBook authorBook) throws Exception {
		/*
		if(authorBook.newRecord == false){
			AppMain.bookGateway.updateAuthorBook(authorBook);
		}
		else
			AppMain.bookGateway.insertAuthorBook(authorBook);
		}
		*/
		AppMain.bookGateway.insertAuthorBook(authorBook);
	}
		
	@Override
	public String toString() {
		return this.title;
	}

	public List<AuditTrail> GetBookAuditTrail() {
		// TODO Auto-generated method stub
		 List<AuditTrail> AuditTrails = AppMain.bookGateway.GetBookAuditTrail(this);
		 for(AuditTrail a: AuditTrails) {
			 System.out.println("this is a" + a);
			 
		 }
	
	return AuditTrails;
	}

}