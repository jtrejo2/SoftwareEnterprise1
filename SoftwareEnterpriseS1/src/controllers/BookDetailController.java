package controllers;

import java.util.List;

import model.Author;
import model.Book;
import model.Publisher;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;

public class BookDetailController {
	@FXML ComboBox<Publisher> cbPublishers;
	private List<Publisher> publishers;
	
	public BookDetailController(List<Publisher> publishers) {
		this.publishers = publishers;
	}

	public void initialize() {
		ObservableList<Publisher> items = cbPublishers.getItems();
		for(Publisher a : publishers){
			items.add(a);
		}
		cbPublishers.getSelectionModel().select(0);
	}
}