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


public class AuthorListController {
	private static Logger logger = LogManager.getLogger();
	@FXML private Button Delete;
	//@FXML private Button Refresh;
	@FXML private ListView<Author> ListAuthor;
	
	private Author author;
	private List<Author> authors;
	
	public AuthorListController(List<Author> authors){
		this.authors = authors;	
	}
	
	@FXML private void handleButtonAction(ActionEvent action) throws Exception{
		Object source = action.getSource();
			
		ListAuthor.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent click){
				if (click.getClickCount() == 1){				
					try {
						if(source == Delete){
							Author selected = ListAuthor.getSelectionModel().getSelectedItem();
							AppMain.authorGateway.authorDelete(selected);
							List<Author> authors = AppMain.authorGateway.getAuthor();
							FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/AuthorListView.fxml"));
							loader.setController(new AuthorListController(authors));
							Parent view = loader.load();
							AppMain.rootPane.setCenter(view);
							return;
						}
						/*if(source == Refresh){
							List<Author> authors = AppMain.authorGateway.getAuthor();
							FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/AuthorListView.fxml"));
							loader.setController(new AuthorListController(authors));
							Parent view = loader.load();
							AppMain.rootPane.setCenter(view);
							return;
						}*/
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		});	
	}
	
	public void initialize(){
		ObservableList<Author> items = ListAuthor.getItems();
		for(Author a : authors){
			items.add(a);
		}
		
		ListAuthor.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent click){
				if(click.getClickCount() == 2){
					Author selected = ListAuthor.getSelectionModel().getSelectedItem();
					
					logger.info("double-clicked " + selected);
					
					try{
						FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/AuthorDetailView.fxml"));
						loader.setController(new AuthorDetailController(selected));
						Parent view = loader.load();
						AppMain.rootPane.setCenter(view);
						
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		});
	}
	
}
