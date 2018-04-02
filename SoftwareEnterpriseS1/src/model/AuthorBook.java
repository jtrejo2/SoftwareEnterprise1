package model;

import java.math.BigDecimal;
import java.math.RoundingMode;

import views.AppMain;

public class AuthorBook {

	private Author author;
	private Book book;
	private BigDecimal royalty;
	private String royaltyPercent;
	private boolean newRecord = true;
	
	public AuthorBook(Author author, Book book, BigDecimal royalty) {
		this.author = author;
		this.book = book;
		setRoyalty(royalty);
		
		this.newRecord = true;
	}
	
	public AuthorBook() {
	}
	public Author getAuthor() {
		return author;
	}
	
	public Book getBook() {
		return book;
	}
	
	public boolean getNewRecord() {
		return newRecord;
	}
	
	public void setNewRecord(String update) {
		if(update.equals("true")) {
			this.newRecord = true;
		}
		else {
			this.newRecord = false;
			
		}
		
		
	}
	
	public void setRoyalty(BigDecimal royalty) {

		this.royalty = royalty;
		this.royaltyPercent = this.royalty.multiply(new BigDecimal("100"))+"%";
	}
	
	
	public BigDecimal getRoyalty() {
		return royalty;
	}
	
	public String getRoyaltyPercent() {
		return this.royaltyPercent;
	}
	
	@Override
	public String toString() {
		return " " + author.getFirst_name() + "                         " + royaltyPercent ;
	}
	
}