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
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;

public class menuController implements Initializable {
	private static menuController menuSingleton;
	
	@FXML
	private ResourceBundle resources;
	@FXML
	private URL location;
	@FXML
	private MenuItem MenuHome;
	@FXML
	private MenuItem AuthorList;
	@FXML
	private MenuItem Quit;
	@FXML
	private BorderPane mainLocation;
	
	public static menuController getInstanceofmenuContoller() {
		
		if (menuSingleton == null) {
			menuSingleton = new menuController();
		}
		return menuSingleton;
	}
	    @FXML void MenuAction(ActionEvent event) throws IOException {
	    		if(event.getSource() == MenuHome) {
	    			loadmain();
	    		}
	    		else if(event.getSource() == AuthorList) {
	    			loadAList();
	    		}
	    		else
	    			Platform.exit();
	    }

	    private void loadmain() {
			// TODO Auto-generated method stub
			
		}
		private void loadAList() {
			// TODO Auto-generated method stub
			Parent tableViewParent = null;
			System.out.println("we are here");
			try {
				tableViewParent = FXMLLoader.load(getClass().getResource("AuthorListView.fxml"));
				System.out.println("we are here2");
				mainLocation.setCenter(tableViewParent);
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
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
