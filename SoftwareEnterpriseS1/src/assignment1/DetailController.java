package assignment1;

import java.awt.event.ActionEvent;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class DetailController {
	private static Logger logger = LogManager.getLogger();
	private static menuController menuController;

    @FXML private Button Save;
    
    public DetailController(Author author) {
		menuController = menuController.getInstanceofmenuContoller();
		//this.author = author;
	}
    
    @FXML void saveAuthorDetail(ActionEvent event) {
		logger.info("calling saveAuthorDetail()");
		
		logger.info("author saved");
		
		//menuController.loadAList();
	}
    
    @FXML
    void initialize() {
    	
    }
}
