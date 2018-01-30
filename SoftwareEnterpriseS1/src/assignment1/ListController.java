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

public class ListController implements Initializable{
	
	@FXML
    private ListView<String> listView;
	ObservableList <String> list = FXCollections.observableArrayList("Ernest Hemingway","Mark Twain","Stephen King","George Orwell");
	private static menuController mController;
	private static Logger logger = LogManager.getLogger(GUI.class);
	
	
	
	public ListController() {
		mController = menuController.getInstanceofmenuContoller();		
	}

	@FXML 
	void onAuthorListClicked(MouseEvent event){
	logger.info("in AuthorListClicked");
    	String author = listView.getSelectionModel().getSelectedItem();
		if (event.getClickCount() == 2){
			if (author != null) {
				System.out.print("Author Clicked!");
			}
		}
    }
	
    @Override
	public void initialize(URL url, ResourceBundle rb) {
    	logger.info("In initialize");
    	listView.setItems(list);
    		
    }
}