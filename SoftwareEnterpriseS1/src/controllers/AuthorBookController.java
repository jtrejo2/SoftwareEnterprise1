package controllers;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
//import com.oracle.tools.packager.Log.Logger;

import model.Gateway;
import model.Publisher;
import views.AppMain;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import model.AlertHelper;
import model.Author;
import model.AuthorBook;
import model.Book;

public class AuthorBookController implements Initializable{
	
	@FXML private TextField royalty;
    @FXML private ComboBox<Author> cbAuthors;
    @FXML private Button save;
    
    private List<Author> authors;
    private Logger logger = LogManager.getLogger() ;
	private Book book;
	private Gateway authorGateway;
	List<Publisher> publishers;

	public AuthorBookController(Book book) {
		this.book = book;
	}
	
	@FXML
    void handleSaveButton(ActionEvent event) throws Exception {
		AuthorBook authorBook = new AuthorBook(cbAuthors.getSelectionModel().getSelectedItem(),
				book, BigDecimal.valueOf(Double.valueOf(royalty.getText())));
		Object source = event.getSource();
		if(source == save) {
			//book.saveAuthor(authorBook);


				try {
					book.saveAuthor(authorBook);
					logger.error("Save button was clicked!");
					//AlertHelper.showWarningMessage("Warning", "Warning2", "Error saving book");
					//Alert alert = new Alert (AlertType.WARNING, "Error saving Royalty please try again");

				} catch (Exception e) {
					Alert alert = new Alert (AlertType.WARNING, "Error saving Royalty please try again");
					//alert.showAndWait();
					//AlertHelper.showWarningMessage("Warning", "Warning2", "Error saving book");
			}
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/BookDetailView.fxml"));
			publishers = AppMain.publisherGateway.getPublishers();
			loader.setController(new BookDetailController(book));//set Detail Controller
			Parent view = loader.load();
			AppMain.rootPane.setCenter(view); //Display
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		}
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		authors = AppMain.authorGateway.getAuthor(); 
		ObservableList<Author> items = cbAuthors.getItems();
		
		for(Author a : authors){
			items.add(a);
		}
		
		if (this.book.getId() == 0) {
			cbAuthors.getSelectionModel().select(0);		
		} 
		
	}
}