package assignment1;

import java.awt.TextField;
import java.awt.event.ActionEvent;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class DetailController {
	private static Logger logger = LogManager.getLogger();
	private static menuController menuController;

    @FXML private Button Save;
    

    @FXML private TextField textFieldAuthorFirstName;
	@FXML private TextField textFieldAuthorLastName;
	@FXML private TextField textFieldAuthorDOB;
	@FXML private TextField textFieldAuthorGender;
	@FXML private TextField textFieldAuthorWebsite;

	private Author author;
	
    public DetailController(Author author) {
		menuController = menuController.getInstanceofmenuContoller();
		this.author = author;
	}
    
    @FXML void saveAuthorDetail(ActionEvent event) {
		logger.info("author saved");		
		menuController.loadAList();
	}
    
    @FXML
    void initialize() {
    	
    }
}
