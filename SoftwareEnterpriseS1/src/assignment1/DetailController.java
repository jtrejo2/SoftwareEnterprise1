package assignment1;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

public class DetailController implements Initializable {
	private static menuController mController;
	private Author author;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button AuthorList;

    @FXML
    private Button Save;

    public DetailController(Author author){
		mController = menuController.getInstanceofmenuContoller();
		this.author = author;
		
		
	}
    void changeToList(ActionEvent event) {

    }

    @FXML
    void initialize() {
        assert AuthorList != null : "fx:id=\"AuthorList\" was not injected: check your FXML file 'AuthorDetailView.fxml'.";
        assert Save != null : "fx:id=\"Save\" was not injected: check your FXML file 'AuthorDetailView.fxml'.";

    }
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}
}
