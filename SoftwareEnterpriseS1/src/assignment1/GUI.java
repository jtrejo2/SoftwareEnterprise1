package assignment1;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class GUI extends Application {
     //small change to launch menu.fxml
	//second sample comment
	private static Logger logger = LogManager.getLogger(GUI.class);
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("menu.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
    	//won't show because of the default logger config
  		logger.debug("This is a debug log message23");
  		
  		//but this will
  		logger.error("This is an error log message24");
        launch(args);
    }
    
}