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

//“CS 4743 Assignment 2 by <Noah Zook and Jonathan Trejo>

public class AppMain extends Application{

	private static Logger logger = LogManager.getLogger();
	public static BorderPane rootPane;
	public static Gateway authorGateway;
	public static BookGateway bookGateway;
	public static PublisherGateway publisherGateway;
	
	
	
	@Override
	public void init() throws Exception{
		super.init();
		
		logger.info("AppMain init called");
		
		authorGateway = new Gateway();
		bookGateway = new BookGateway();
		publisherGateway = new PublisherGateway();
	}
	
	@Override
	public void stop() throws Exception{
		super.stop();
		logger.error("stop called");
		authorGateway.close();
		bookGateway.close();
		publisherGateway.close();

	}
	
	@Override
	public void start(Stage stage) throws Exception{
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/menu.fxml"));
		MenuController controller = new MenuController();
		loader.setController(controller);
				
		Parent view = loader.load();
		rootPane = (BorderPane) view;
	
		Scene scene = new Scene(view);
		stage.setScene(scene);
		stage.setTitle("Assignment 3");
		stage.show();
		
	}
	
	public static void main(String[] args) {
		launch(args);
				
	}

}
