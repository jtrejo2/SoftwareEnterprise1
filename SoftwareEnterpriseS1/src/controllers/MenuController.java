package controllers;

import javafx.event.ActionEvent;
import java.io.IOException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.MenuItem;
import model.*;
import views.*;


import java.util.List;
//Menu Controller
public class MenuController {
	private static Logger logger = LogManager.getLogger();
	@FXML private MenuItem menuExit;
	@FXML private MenuItem menuAuthorList;
	@FXML private MenuItem addAuthor;
	@FXML private MenuItem menuBookList;
	@FXML private MenuItem addBook;
	
	List<Publisher> publishers;

	public MenuController(){
		
	}
	
	//Handle when menu item is clicked
	@FXML private void handleMenuItem(ActionEvent action) throws IOException, GatewayException {
		Object source = action.getSource();
		if(source == menuExit) {//exit if exit is selected
			Platform.exit();
		}
		if(source == menuAuthorList) {//load the listView if menuAuthorList was selected
			
			List<Author> authors = AppMain.authorGateway.getAuthor();
			
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/AuthorListView.fxml"));

			loader.setController(new AuthorListController(authors));//set controller
			
			Parent view = loader.load();
			AppMain.rootPane.setCenter(view);//display
			return;
		}
		
		if(source == addAuthor){//load DetailView if addAuthor is selected
			logger.info("Add author called");
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/AuthorDetailView.fxml"));
			loader.setController(new AuthorDetailController(new Author()));//set Detail Controller
			Parent view = loader.load();
			AppMain.rootPane.setCenter(view); //Display
			
		}
		
		if(source == menuBookList) {//load the listView if menuAuthorList was selected
			
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/BookListView.fxml"));
			List<Book> books = AppMain.bookGateway.getBook();
			loader.setController(new BookListController(books));//set controller
			
			Parent view = loader.load();
			AppMain.rootPane.setCenter(view);//display
			return;
		}
		
		if(source == addBook){//load DetailView if addAuthor is selected
			logger.info("Add book called");
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/BookDetailView.fxml"));
			publishers = AppMain.publisherGateway.getPublishers();
			loader.setController(new BookDetailController(new Book()));//set Detail Controller
			Parent view = loader.load();
			AppMain.rootPane.setCenter(view); //Display
			
		}
		
	}
	
	public void initialize(){
		logger.info("Controller init has been called");
	}
	
}
