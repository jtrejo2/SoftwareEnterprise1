package controllers;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import views.AppMain;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import model.AlertHelper;
import model.Book;
import model.ExcelReport;
import model.GatewayException;
import model.Publisher;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ExcelReportController implements Initializable {

	@FXML ComboBox<Publisher> cbPublishers;
	private List<Publisher> publishers;
    @FXML private TextField Path;
    @FXML private Button Generate;
    @FXML private Button Save;
    
    private static Logger logger = LogManager.getLogger();


    public ExcelReportController() {
    	
    }

    @FXML
    void handleButtonAction(ActionEvent action) throws GatewayException {
    		Object source = action.getSource();
 
    		if(source == Generate){
        		if(cbPublishers.getSelectionModel().getSelectedItem() == null) {
        			logger.error("No publisher selected");
        			AlertHelper.showWarningMessage("Warning", "Warning2","No publisher selected");
        			return;
        		} else if(Path.getText().equals("")) {
        			logger.error("No save path selected");
        			AlertHelper.showWarningMessage("Warning", "Warning2","No save path selected");
        			return;
        		}
        		
        		ObservableList<Book> books = AppMain.publisherGateway.getBooksByPublisherId(cbPublishers.getSelectionModel().getSelectedItem().getId());
        		new ExcelReport(Path.getText(), books, cbPublishers.getSelectionModel().getSelectedItem().getPublisherName());
        		logger.info("Report generated");
    		}
    }

    
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		publishers = AppMain.publisherGateway.getPublishers(); 
		ObservableList<Publisher> items = cbPublishers.getItems();
		
		for(Publisher a : publishers){
			items.add(a);
		}
		
	}
}