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
import javafx.stage.Stage;

public class controller {
	
	private static controller controller;

	public static controller getInstance() {
		if (controller == null) {
			controller = new controller();
		}
		return controller;
	}
	
	public void changeToMain(ActionEvent event) throws IOException {
        Parent tableViewParent = FXMLLoader.load(getClass().getResource("main.fxml"));
        Scene tableViewScene = new Scene(tableViewParent);

        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        
        window.setScene(tableViewScene);
        window.show();
    }
	
    public void changeToList(ActionEvent event) throws IOException {
        Parent tableViewParent = FXMLLoader.load(getClass().getResource("AuthorListView.fxml"));

        Scene tableViewScene = new Scene(tableViewParent);

        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();        
        window.setScene(tableViewScene);
        window.show();

    }
    
    public void exit(ActionEvent event) throws IOException {
        System.exit(0);
    }
    
}

/*
<FXCollections fx:factory="observableArrayList">
<String fx:value="Ernest Hemingway"/>
<String fx:value="Mark Twain"/>
<String fx:value="Stephen King"/>
<String fx:value="George Orwell"/>
<String fx:value="Ray Bradbury"/>
</FXCollections>
*/