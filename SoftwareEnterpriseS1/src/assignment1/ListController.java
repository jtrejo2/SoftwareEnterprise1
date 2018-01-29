package assignment1;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
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
	
	public void changeToMain(ActionEvent event) throws IOException {
        Parent tableViewParent = FXMLLoader.load(getClass().getResource("main.fxml"));
        Scene tableViewScene = new Scene(tableViewParent);

        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        
        window.setScene(tableViewScene);
        window.show();
    }

	@FXML 
	void onAuthorListClicked(MouseEvent event){
    	String author = listView.getSelectionModel().getSelectedItem();
		if (event.getClickCount() == 2){
			if (author != null) {
				System.out.print("Author Clicked!");
			}
		}
    }
	
    @Override
	public void initialize(URL url, ResourceBundle rb) {
    	listView.setItems(list);
	}   
}