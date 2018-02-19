package assignment1;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.net.URL;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;



public class GUI extends Application {
     //small change to launch menu.fxml
	//second sample comment
	private static Logger logger = LogManager.getLogger(GUI.class);
    @Override
    public void start(Stage stage) throws Exception {
    		System.out.println("start called");
    	    AppController controller = AppController.getInstanceofmenuContoller();
        /*Parent root = FXMLLoader.load(getClass().getResource("AuthorDetailView.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();
        */
    		
    		//TODO: move this to a singleton switchView method OR a view builder
    		URL fxmlFile = this.getClass().getResource("menu.fxml");
    		FXMLLoader loader = new FXMLLoader(fxmlFile);
    		
    		loader.setController(controller);
    		
    		Parent root = loader.load();
    		System.out.println("here\n");
    		//controller.setRootPane((BorderPane) root);
    		
    		//Scene scene = new Scene(root, 600, 400);
    	    
    		//stage.setTitle("CS 4743 Dog Demo");
    		//stage.setScene(scene);
    		//stage.show();		

    }
	@Override
	public void stop() throws Exception {
		super.stop();
		
		//TODO: find out how to attach to shutdown hook
		logger.info("Closing connection...");
		
		//conn.close();
	}

    public static void main(String[] args) {
    	//won't show because of the default logger config
  		logger.info("starting program");
  		//CS 4743 Assignment 1 by <Jonathon Trejo>
  		
  		//but this will
  		//logger.error("This is an error log message24");
        launch(args);
    }
    
}