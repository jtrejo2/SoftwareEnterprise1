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

	private String storedSearch;
	private int page, numPages, total;
	private List<Book> books;
	List<Publisher> publishers;
	
	//assign book to this.book
	public BookListController(List<Book> books, int page, int total, String storedSearch) throws GatewayException{
		this.books = books;
		this.page = page;
		this.total = total;
		this.storedSearch = storedSearch;
		if (storedSearch == null)
			this.numPages = AppMain.bookGateway.getNumBooks();
		else
			this.numPages = AppMain.bookGateway.getNumSearchBooks(storedSearch);
	}
	
	
	void updateLabel() throws GatewayException {
		int numBooks;
		if (total == 0) {
			if (storedSearch != null) 
				total = AppMain.bookGateway.getTotalCount(storedSearch);
			else
				total = AppMain.bookGateway.getNumRows();
		}
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
							books = AppMain.bookGateway.getBook(0);
							FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/BookListView.fxml"));
							loader.setController(new BookListController(books, 0, 0, null));//set controller
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
			storedSearch = searchText.getText();
			if (searchText.getText().isEmpty())
				books = AppMain.bookGateway.getBook(0);
			else
				books = AppMain.bookGateway.getSearchBook(0, storedSearch);
			//books = AppMain.bookGateway.getBook(page);
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/Views/BookListView.fxml"));
			int totalFromSearch = AppMain.bookGateway.getTotalCount(searchText.getText());
			loader.setController(new BookListController(books, 0, totalFromSearch, storedSearch));
			Parent view = loader.load();
			AppMain.rootPane.setCenter(view);
			return;
		}
		
		
		if(source == Next) {
			if(page == numPages)
				return;
			page++;
			if (storedSearch == null) {
				books = AppMain.bookGateway.getBook(page);
				System.out.println("HERE");
			}
			else
				books = AppMain.bookGateway.getSearchBook(page, storedSearch);
			System.out.println(storedSearch);
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/Views/BookListView.fxml"));
			loader.setController(new BookListController(books, page, 0, storedSearch));
			Parent view = loader.load();
			AppMain.rootPane.setCenter(view);
			
		} else if(source == Previous) {
			if(page == 0)
				return;
			page--;
			if (storedSearch == null)
				books = AppMain.bookGateway.getBook(page);
			else
				books = AppMain.bookGateway.getSearchBook(page, storedSearch);
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/Views/BookListView.fxml"));
			loader.setController(new BookListController(books, page, 0, storedSearch));
			Parent view = loader.load();
			AppMain.rootPane.setCenter(view);
		} else if(source == First) {
			if(page == 0)
				return;
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/Views/BookListView.fxml"));
			if (storedSearch == null)
				books = AppMain.bookGateway.getBook(0);
			else
				books = AppMain.bookGateway.getSearchBook(0, storedSearch);
			loader.setController(new BookListController(books, 0, 0, storedSearch));
			Parent view = loader.load();
			AppMain.rootPane.setCenter(view);
		} else if(source == Last) {
			if(page == numPages)
				return;
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/Views/BookListView.fxml"));
			if (storedSearch == null)
				books = AppMain.bookGateway.getBook(numPages);
			else
				books = AppMain.bookGateway.getSearchBook(numPages, storedSearch);
			loader.setController(new BookListController(books, numPages, 0, storedSearch));
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
