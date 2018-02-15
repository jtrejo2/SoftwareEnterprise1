package views;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import model.*;
import controllers.*;


public class AppMain extends Application{

	private static Logger logger = LogManager.getLogger();
	
	public static BorderPane rootPane;
	
	public static AuthorTableGateway authorGateway;
	
	@Override
	public void init() throws Exception{
		super.init();
		
		//create gateway and exit if problem
		logger.error("AppMain init called");
		authorGateway = new AuthorTableGateway();
	}
	
	@Override
	public void stop() throws Exception{
		super.stop();
		
		//close gateway
		logger.error("AppMain stop called");
		authorGateway.close();
	}
	
	@Override
	public void start(Stage stage) throws Exception{
		//init loader
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/Master_Stage.fxml"));
		
		//init the controller and give it to the loader
		MenuController controller = new MenuController();
		loader.setController(controller);
				
		//create the view from the loader
		Parent view = loader.load();
		//save a reference to the BorderPAne
		rootPane = (BorderPane) view;
			
		//plug the view into a scene
		Scene scene = new Scene(view);
				
		//plug scene into stage
		stage.setScene(scene);
		
		stage.setTitle("Assignment 2");
		stage.show();
		
	}
	
	public static void main(String[] args) {
		launch(args);
		
			
	}

}
