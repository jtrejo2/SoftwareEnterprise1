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
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import model.*;
import views.*;

//BookListController
public class BookListController {
	
	private static Logger logger = LogManager.getLogger();
	@FXML private Button Delete;
	@FXML private ListView<Book> ListBook;
	
	private Book book;
	private List<Book> books;
	List<Publisher> publishers;
	//assign book to this.book
	public BookListController(List<Book> books){
		this.books = books;	
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
							Book selected = ListBook.getSelectionModel().getSelectedItem();
							AppMain.bookGateway.bookDelete(selected);
							List<Book> books = AppMain.bookGateway.getBook();
							FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/BookListView.fxml"));
							loader.setController(new BookListController(books));//set controller
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
	}
	//initialize
	public void initialize(){
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
						loader.setController(new BookDetailController(selected, publishers));//set Detail Controller
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
