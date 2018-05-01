package controllers;

import java.io.IOException;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import model.*;
import views.*;

//BookListController
public class BookListController {
	
	private static Logger logger = LogManager.getLogger();
	@FXML private Button Delete, Search, First, Previous, Next, Last;
	@FXML private TextField searchText;
	@FXML private ListView<Book> ListBook;
	@FXML private Label Fetched;

	
	private int page, numPages, total;
	private List<Book> books;
	List<Publisher> publishers;
	
	//assign book to this.book
	public BookListController(List<Book> books, int page, int total) throws GatewayException{
		this.books = books;
		this.page = page;
		this.total = total;
		this.numPages = AppMain.bookGateway.getNumBooks();
	}
	
	
	void updateLabel() throws GatewayException {
		int numBooks;
		if (total == 0)
			total = AppMain.bookGateway.getNumRows();
			System.out.println("Empty");
		if (page == numPages)
			numBooks = total;
		else
			numBooks = (50 * page) + books.size();
		Fetched.setText("Fetched records " + (page * 50) + " to " + numBooks + " out of " + total);
	}
	//Handle when a button is clicked
	@FXML private void handleButtonAction(ActionEvent action) throws Exception{
		Object source = action.getSource();
			
		ListBook.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent click){
				if (click.getClickCount() == 1){				
					//if delete was selected delete the book and load the listView again
					try {
						if(source == Delete){
							logger.info("deleting");
							
							Book selected = ListBook.getSelectionModel().getSelectedItem();
							AppMain.bookGateway.bookDelete(selected);
							List<Book> books = AppMain.bookGateway.getBook(page);
							FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/BookListView.fxml"));
							loader.setController(new BookListController(books, 0, 0));//set controller
							Parent view = loader.load();
							AppMain.rootPane.setCenter(view); //display
							return;
						}

						

					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		});	
		
		
		if(source == Search) {
			logger.info("searching");
			List<Book> books = AppMain.bookGateway.getSearchBook(0, searchText.getText());
			//books = AppMain.bookGateway.getBook(page);
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/Views/BookListView.fxml"));
			int totalFromSearch = AppMain.bookGateway.getTotalCount(searchText.getText());
			loader.setController(new BookListController(books, 0, totalFromSearch));
			Parent view = loader.load();
			AppMain.rootPane.setCenter(view);
			return;
		}
		
		
		if(source == Next) {
			if(page == numPages)
				return;
			page++;
			books = AppMain.bookGateway.getBook(page);
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/Views/BookListView.fxml"));
			loader.setController(new BookListController(books, page, 0));
			Parent view = loader.load();
			AppMain.rootPane.setCenter(view);
			
		} else if(source == Previous) {
			if(page == 0)
				return;
			page--;
			books = AppMain.bookGateway.getBook(page);
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/Views/BookListView.fxml"));
			loader.setController(new BookListController(books, page, 0));
			Parent view = loader.load();
			AppMain.rootPane.setCenter(view);
		} else if(source == First) {
			if(page == 0)
				return;
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/Views/BookListView.fxml"));
			books = AppMain.bookGateway.getBook(0);
			loader.setController(new BookListController(books, 0, 0));
			Parent view = loader.load();
			AppMain.rootPane.setCenter(view);
		} else if(source == Last) {
			if(page == numPages)
				return;
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/Views/BookListView.fxml"));
			books = AppMain.bookGateway.getBook(numPages);
			loader.setController(new BookListController(books, numPages, 0));
			Parent view = loader.load();
			AppMain.rootPane.setCenter(view);
		}
	}
	
	//initialize
	public void initialize() throws GatewayException{
		updateLabel();
		ObservableList<Book> items = ListBook.getItems();
		for(Book a : books){
			items.add(a);
		}
		
		ListBook.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent click){
				if(click.getClickCount() == 2){
					Book selected = ListBook.getSelectionModel().getSelectedItem();
					
					logger.info("double-clicked " + selected);
					
					try{
						logger.info("Add book called");
						FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/BookDetailView.fxml"));
						publishers = AppMain.publisherGateway.getPublishers();
						loader.setController(new BookDetailController(selected));//set Detail Controller
						Parent view = loader.load();
						AppMain.rootPane.setCenter(view); //Display
						
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		});
	}
	
}
