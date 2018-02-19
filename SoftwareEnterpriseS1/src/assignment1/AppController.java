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



public class AppController implements Initializable {
	private static AppController myInstance = null;
	public static final int DOG_LIST = 1;
	public static final int DOG_DETAIL = 2;
	private BorderPane rootPane = null;

    @FXML
    private MenuItem Quit;

    @FXML
    private MenuItem AuthorList;

    @FXML
    void clickMenuAuthorList(ActionEvent event) {
    	System.out.println("woo");

    }

    @FXML
    void clickMenuQuit(ActionEvent event) {
    	System.out.println("hoo");
    }

	private static Logger logger = LogManager.getLogger(GUI.class);
	
	private AppController() {
		//TODO: instantiate models
	}

	
	public static AppController getInstanceofmenuContoller() {
		logger.info("in menuController");
		System.out.print("YOOOOOO\n");
		
		if (myInstance == null) {
			myInstance = new AppController();
		}
		return myInstance;
	
	}
	
	
	public BorderPane getRootPane() {
		return rootPane;
	}

	public void setRootPane(BorderPane rootPane) {
		this.rootPane = rootPane;
	}/*
public void changeView(int viewType, Object arg) throws AppException {
			try {
				ListController controller = null;
				URL fxmlFile = null;
				switch(viewType) {
					case DOG_LIST:
						System.out.println("We are here 2\n");
						fxmlFile = this.getClass().getResource("AuthorListView.fxml");
						//controller = new ListController();
						break;
					case DOG_DETAIL:
						fxmlFile = this.getClass().getResource("AuthorDetailView.fxml");
						//controller = new DogDetailController((Dog) arg);
						break;
				}
			
				FXMLLoader loader = new FXMLLoader(fxmlFile);
				//loader.setController(controller);
			
				Parent viewNode = loader.load();
				rootPane.setCenter(viewNode);
			} catch (IOException e) {
				throw new AppException(e);
			}
		} */
	   /* @FXML void MenuAction(ActionEvent event) throws IOException {
	    		logger.info("in MenuAction");
	    		if(event.getSource() == AuthorList) {
	    			System.out.println( event.getSource());
	    			System.out.print("we are here!\n");
	    			changeView(DOG_LIST,null);
	    			//loadAList();
	    		}
	    		else
	    			Platform.exit();
	    } /*
	//	@FXML
	//    void clickMenuDogList(ActionEvent event) {
	//		logger.info("Dog list menu item clicked");
			//changeView(DOG_LIST, null);
	    //}
		
		//@FXML
	   // void clickMenuQuit(ActionEvent event) {
	//		logger.info("Quit menu item clicked");
			//Platform.exit();
	  //  }

	  /*
		void loadAList() {
			// TODO Auto-generated method stub
			//Parent tableViewParent = null;
			logger.info("in loadAList");
			
			//ListController listController = new ListController();
				try {
					Parent tableViewParent = FXMLLoader.load(getClass().getResource("AuthorDetailView.fxml"));
					//mainLocation.setCenter(tableViewParent);
					mainLocation.setCenter(tableViewParent);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					logger.info("Therre was an issue in the try/catch of loadAlist");
					e.printStackTrace();
				}
				//mainLocation.setCenter(tableViewParent);
				
	        //Scene tableViewScene = new Scene(tableViewParent);

	        //Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
	        
	        //window.setScene(tableViewScene);
	        //window.show();
			
			
		} 
		*/
		
		@Override
		public void initialize(URL location, ResourceBundle resources) {
			// TODO Auto-generated method stub
			logger.info("in menu controller intialize");
			
		}
}
