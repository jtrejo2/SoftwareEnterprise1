package assignment1;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class DetailController {
	private static AppController mController = null;
	private Author author;
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button AuthorList;

    @FXML
    private Button Save;

    @FXML
    private TextField textAuthorFirst;

    @FXML
    private TextField textAuthorLast;

    @FXML
    private TextField textDOB;

    @FXML
    private TextField textGender;

    @FXML
    private TextField textWebsite;

    @FXML
    void changeToList(ActionEvent event) {

    }
    public DetailController(Author author) {
		mController = AppController.getInstanceofmenuContoller();
		this.author = author;
	}


    @FXML
    void initialize() {
        assert AuthorList != null : "fx:id=\"AuthorList\" was not injected: check your FXML file 'AuthorDetailView.fxml'.";
        assert Save != null : "fx:id=\"Save\" was not injected: check your FXML file 'AuthorDetailView.fxml'.";
        assert textAuthorFirst != null : "fx:id=\"textAuthorFirst\" was not injected: check your FXML file 'AuthorDetailView.fxml'.";
        assert textAuthorLast != null : "fx:id=\"textAuthorLast\" was not injected: check your FXML file 'AuthorDetailView.fxml'.";
        assert textDOB != null : "fx:id=\"textDOB\" was not injected: check your FXML file 'AuthorDetailView.fxml'.";
        assert textGender != null : "fx:id=\"textGender\" was not injected: check your FXML file 'AuthorDetailView.fxml'.";
        assert textWebsite != null : "fx:id=\"textWebsite\" was not injected: check your FXML file 'AuthorDetailView.fxml'.";
        dogName.textProperty().bindBidirectional(dog.dogNameProperty());
    }
}
