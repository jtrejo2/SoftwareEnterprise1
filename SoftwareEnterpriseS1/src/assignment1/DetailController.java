package assignment1;

import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.net.URL;
import java.util.ResourceBundle;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;


public class DetailController implements Initializable {
	private static menuController mController;
	private Author author;
	private static Logger logger = LogManager.getLogger();

    private Button AuthorList;

    private Button Save;

    public DetailController(Author author){
		mController = menuController.getInstanceofmenuContoller();
		this.author = author;
		
		
	}
    void changeToList(ActionEvent event) {

    }

    
    @FXML void saveAuthorDetail(ActionEvent event) {
		logger.info("author saved");		
		mController.loadAList();
	}
    
    void initialize() {
    	
    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}
}
