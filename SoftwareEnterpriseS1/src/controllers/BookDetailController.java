package controllers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import model.*;
import views.*;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

//bookDetailList Controller 
public class BookDetailController {
	private static Logger logger = LogManager.getLogger();
	
	@FXML private TextField title, summary, yearPublished, isbn, publisher, dateAdded;
	@FXML private Button Save;
	
	//private Book book;
	
	//assign book to this.book
	public BookDetailController(Book book){
		//this.book = book;
	}
	
	//when save button is clicked call the save book save method with the entered user information
	@FXML private void handleButtonAction(ActionEvent action) throws Exception {
		Object source = action.getSource();
		
		//book.setTitle(title.getText());
		//book.setSummary(summary.getText());
		//book.setYearPublished(year_published.getText());
		//book.setIsbn(isbn.getText());
		//book.setPublisher(publisher.getText());
		//book.setDateAdded(date_added.getText());
	
		/*
		if(source == Save){
			try {
				book.Save(book);
				logger.error("Save button was clicked!");
			} catch (Exception e) {
				Alert alert = new Alert (AlertType.WARNING, "Error saving please try again");
				alert.showAndWait();
			}
		}
		*/

	}
	//initialize
	public void initialize(){
		logger.info("Detail View init called");
		//title.setText(book.getTitle());
		//summary.setText(book.getSummary());
		//yearPublished.setText(book.getYearPublished());
		//isbn.setText(book.getIsbn());
		//dateAdded.setText(book.getDateAdded());
	}
}
