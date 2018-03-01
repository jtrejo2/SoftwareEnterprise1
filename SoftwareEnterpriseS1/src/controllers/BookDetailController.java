package controllers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import model.Publisher;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import model.*;
import views.*;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

//bookDetailList Controller 
public class BookDetailController {
	
	private static Logger logger = LogManager.getLogger();
	
	@FXML private TextField title, summary, yearPublished, isbn, dateAdded;
	@FXML private Button Save;
	@FXML private ComboBox<Publisher> publisher;
	
	private Book book;
	private ObservableList<Publisher> publishers;
	
	//assign book to this.book
	public BookDetailController(Book book, ObservableList<Publisher> publishers){
		this.book = book;
		this.publishers = publishers;
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
	
		
		if(source == Save){
			try {
				book.Save(book);
				logger.error("Save button was clicked!");
			} catch (Exception e) {
				Alert alert = new Alert (AlertType.WARNING, "Error saving please try again");
				alert.showAndWait();
			}
		}
		

	}
	//initialize
	public void initialize(){
		logger.info("Detail View init called");
		//title.setText(book.getTitle());
		//summary.setText(book.getSummary());
		//yearPublished.setText(book.getYearPublished());
		//isbn.setText(book.getIsbn());
		//dateAdded.setText(book.getDateAdded());
		
		//publisher.setItems(publishers);
	}
}
