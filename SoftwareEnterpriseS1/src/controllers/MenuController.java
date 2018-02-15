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

public class MenuController {
	private static Logger logger = LogManager.getLogger();
	@FXML private MenuItem menuExit;
	@FXML private MenuItem menuAuthorList;
	@FXML private MenuItem addAuthor;
	
	public MenuController(){
		
	}
	
	@FXML private void handleMenuItem(ActionEvent action) throws IOException {
		Object source = action.getSource();
		if(source == menuExit) {
			Platform.exit();
		}
		if(source == menuAuthorList) {
			
			List<Author> authors = AppMain.authorGateway.getAuthor();
			
			
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/AuthorListView.fxml"));
			
			//add the controller
			loader.setController(new AuthorListController(authors));
			
			Parent view = loader.load();
			//plug into APPMain's border pane
			AppMain.rootPane.setCenter(view);
			return;
		}
		
		if(source == addAuthor){
			logger.error("Add author was called");
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/AuthorDetailedView.fxml"));
			loader.setController(new AuthorDetailedController(new Author()));
			Parent view = loader.load();
			AppMain.rootPane.setCenter(view);
			
		}
	}
	
	public void initialize(){
		logger.error("Controller init has been called");
	}
	
}
