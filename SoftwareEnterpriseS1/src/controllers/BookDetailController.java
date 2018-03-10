package controllers;

import java.util.List;
import model.Author;
import model.Book;
import model.Publisher;
import views.AppMain;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class BookDetailController {
	private static Logger logger = LogManager.getLogger();
	
	@FXML private TextField title, summary, yearPublished, isbn;
	@FXML private Button Save;
	@FXML ComboBox<Publisher> cbPublishers;
	private List<Publisher> publishers;
	
	private Book book;
	
	public BookDetailController(Book book) {
		this.book = book;
	}
	
	@FXML private void handleButtonAction(ActionEvent action) throws Exception {
		Object source = action.getSource();
		
		book.setTitle(title.getText());
		book.setSummary(summary.getText());
		book.setYearPublished(Integer.parseInt(yearPublished.getText()));
		book.setIsbn(isbn.getText());
		book.setPublisher(cbPublishers.getValue());
		
		if(source == Save){
			try {
				book.Save(book);
				logger.error("Save button was clicked!");
			} catch (Exception e) {
				Alert alert = new Alert (AlertType.WARNING, "Error saving book please try again");
				alert.showAndWait();
			}
		}
	}

	public void initialize() {
		publishers = AppMain.publisherGateway.getPublishers(); 
		ObservableList<Publisher> items = cbPublishers.getItems();
		
		for(Publisher a : publishers){
			items.add(a);
		}
		
		if (this.book.getId() == 0) {
			cbPublishers.getSelectionModel().select(0);
				
		} else {
			Publisher publisher = AppMain.publisherGateway.getPublisherById(this.book.getPublisherId());
			if (publisher == null)
				cbPublishers.getSelectionModel().select(0);
			else
				cbPublishers.getSelectionModel().select(publisher);
		
		
		title.setText(book.getTitle());
		summary.setText(book.getSummary());
		yearPublished.setText(String.valueOf(book.getYearPublished()));
		isbn.setText(book.getIsbn());
		
		}	
	}
}




