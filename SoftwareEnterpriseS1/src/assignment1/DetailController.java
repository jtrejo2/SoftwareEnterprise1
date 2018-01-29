package assignment1;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class DetailController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button AuthorList;

    @FXML
    private Button Save;

    @FXML
    void changeToList(ActionEvent event) {

    }

    @FXML
    void initialize() {
        assert AuthorList != null : "fx:id=\"AuthorList\" was not injected: check your FXML file 'AuthorDetailView.fxml'.";
        assert Save != null : "fx:id=\"Save\" was not injected: check your FXML file 'AuthorDetailView.fxml'.";

    }
}
