package assignment1;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class menuController implements Initializable {
	private static menuController menuSingleton;
	
	@FXML
	private ResourceBundle resources;
	@FXML
	private URL location;
	
	@FXML
	private MenuItem AuthorList;
	@FXML
	private MenuItem Quit;
	@FXML
	private BorderPane mainLocation;
	private static Logger logger = LogManager.getLogger(GUI.class);
	
	public static menuController getInstanceofmenuContoller() {
		logger.info("in menuController");
		
		if (menuSingleton == null) {
			menuSingleton = new menuController();
		}
		return menuSingleton;
	}
	    @FXML void MenuAction(ActionEvent event) throws IOException {
	    		logger.info("in MenuAction");
	    		if(event.getSource() == AuthorList) {
	    			loadAList();
	    		}
	    		else
	    			Platform.exit();
	    }

	  
		void loadAList() {
			// TODO Auto-generated method stub
			Parent tableViewParent = null;
			logger.info("in loadAList");
			try {
				tableViewParent = FXMLLoader.load(getClass().getResource("AuthorListView.fxml"));
				System.out.println("we are here2");
				mainLocation.setCenter(tableViewParent);
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				logger.info("there was an error in the try/catch in loadAlist");
				
			}
	        //Scene tableViewScene = new Scene(tableViewParent);

	        //Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
	        
	        //window.setScene(tableViewScene);
	        //window.show();
			
			
		}
		@Override
		public void initialize(URL location, ResourceBundle resources) {
			// TODO Auto-generated method stub
			
		}
}
