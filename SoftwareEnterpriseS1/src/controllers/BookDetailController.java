package controllers;

import java.util.List;
import model.Author;
import model.Book;
import model.Publisher;
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
	
	public BookDetailController(Book book, List<Publisher> publishers) {
		this.book = book;
		this.publishers = publishers;
	}
	
	@FXML private void handleButtonAction(ActionEvent action) throws Exception {
		Object source = action.getSource();
		
		book.setTitle (title.getText());
		book.setSummary(summary.getText());
		book.setYearPublished(Integer.parseInt(yearPublished.getText()));
		book.setIsbn(isbn.getText());

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

	public void initialize() {
		ObservableList<Publisher> items = cbPublishers.getItems();
		for(Publisher a : publishers){
			items.add(a);
		}
		cbPublishers.getSelectionModel().select(0);
	}
}