package controllers;

import java.math.BigDecimal;
import java.net.URL;
import java.util.ResourceBundle;
import model.Gateway;
import views.AppMain;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import model.Author;
import model.AuthorBook;
import model.Book;

public class AuthorBookController implements Initializable{
	
	@FXML private TextField royalty;
    @FXML private ComboBox<Author> cbAuthors;

	private Book book;
	private Gateway authorGateway;

	public AuthorBookController(Book book, Gateway authorGateway) {
		this.book = book;
		this.authorGateway = authorGateway;
	}
	
	@FXML
    void handleSaveButton(ActionEvent event) {
		AuthorBook authorBook = new AuthorBook(cbAuthors.getSelectionModel().getSelectedItem(),
				book, BigDecimal.valueOf(Double.valueOf(royalty.getText())));
		

    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
	}
}