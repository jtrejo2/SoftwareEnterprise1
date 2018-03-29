package controllers;

import java.io.IOException;
import java.util.List;

import model.AuditTrail;
import model.Author;
import model.AuthorBook;
import model.Book;
import model.GatewayException;
import model.Publisher;
import views.AppMain;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class BookDetailController {
	private static Logger logger = LogManager.getLogger();
	
	@FXML private TextField title, summary, yearPublished, isbn;
	@FXML private Button Save;
	@FXML private Button Audit;
	@FXML private Button AddAuthor;
	@FXML private Button Delete;
	
	@FXML ComboBox<Publisher> cbPublishers;
	private List<Publisher> publishers;
	
	@FXML private ListView<AuthorBook> listAuthorBooks;
	private List<AuthorBook> authorBooks;
	
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
		if(source == Audit) {
			System.out.println("here we are");
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/AuditTrailView.fxml"));
			List<AuditTrail> books = book.GetBookAuditTrail();
			loader.setController(new AuditTrailController(books,book));//set controller
			
			Parent view = loader.load();
			AppMain.rootPane.setCenter(view);//display
			return;
			
		}
		if(source == AddAuthor) {
			System.out.println("here we are");
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/AuthorBookView.fxml"));
			//List<AuditTrail> books = book.GetBookAuditTrail();
			loader.setController(new AuthorBookController(book));//set Detail Controller
			Parent view = loader.load();
			AppMain.rootPane.setCenter(view);//display
			return;
			
		}
		
	}
	
	@FXML private void handleButtonAction1(ActionEvent action) throws Exception{
		Object source = action.getSource();
			
		listAuthorBooks.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent click){
				if (click.getClickCount() == 1){				
					try {
						if(source == Delete){
							logger.info("deleting");
							AuthorBook selected = listAuthorBooks.getSelectionModel().getSelectedItem();
							AppMain.bookGateway.deleteAuthorBook(selected);
							List<AuthorBook> authorBooks = AppMain.bookGateway.getAuthorsForBook(book);
							FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/BookDetailView.fxml"));
							loader.setController(new BookDetailController(book));//set controller
							Parent view = loader.load();
							AppMain.rootPane.setCenter(view);//display
							return;
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		});	
	}
	
	public void initialize() throws GatewayException {
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
		
		authorBooks = AppMain.bookGateway.getAuthorsForBook(book);
		ObservableList<AuthorBook> items1 = listAuthorBooks.getItems();
		for(AuthorBook a : authorBooks){
			items1.add(a);
		}
		
		
		
		listAuthorBooks.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent click){
				if(click.getClickCount() == 2){
					AuthorBook selected = listAuthorBooks.getSelectionModel().getSelectedItem();
					
					logger.info("double-clicked " + selected);
					
					try{
						FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/AuthorBookView.fxml"));
						loader.setController(new AuthorBookController(selected.getBook()));//set controller
						Parent view = loader.load();
						AppMain.rootPane.setCenter(view);//display
						
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		});
		
		title.setText(book.getTitle());
		summary.setText(book.getSummary());
		yearPublished.setText(String.valueOf(book.getYearPublished()));
		isbn.setText(book.getIsbn());
		
		}	
	}
}




