package assignment1;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ListController implements Initializable{
	
	@FXML
    private ListView<Author> listView;
	private ObservableList<Author> list;
	private static menuController mController;
	private static Logger logger = LogManager.getLogger(GUI.class);
	
	
	

	public ListController() {
		list = FXCollections.observableArrayList(
				new Author("Billy", "2/22/1984", "www.billybob.com","Bob","Male"),
			    new Author("Vince", "10/2/1964", "www.vincebrown.com","Brown","Male"),
			    new Author("Anthony", "11/04/1989", "www.anthonyreynolds.com","Reynolds","Male"));
		mController = menuController.getInstanceofmenuContoller();
		listView = new ListView<Author>();
			    
	}


	@FXML 
	void onAuthorListClicked(MouseEvent event){
	//logger.info("in AuthorListClicked");
    	//Author author = listView.getSelectionModel().getSelectedItem();
		//if (event.getClickCount() == 2){
		//	if (author != null) {
				//System.out.print("Author Clicked!");
		//	}
		//}
    }

	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		listView.setItems(list);
		listView.setOnMouseClicked(new EventHandler<MouseEvent>() {
		@Override
		public void handle(MouseEvent click) {
			if (click.getClickCount() == 2) {
				logger.info("double click occured in AuthorListView");
		        
		        }
		    }
		});
	}
}