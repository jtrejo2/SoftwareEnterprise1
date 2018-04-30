package controllers;

import java.io.File;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import views.AppMain;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import model.AlertHelper;
import model.Book;
import model.GatewayException;
import model.Publisher;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ExcelReportController implements Initializable {

	@FXML ComboBox<Publisher> cbPublishers;
	private List<Publisher> publishers;
    @FXML private Label Path;
    @FXML private Button Generate;
    @FXML private Button Save;
    
    private static Logger logger = LogManager.getLogger();


    public ExcelReportController() {
    	
    }

    @FXML
    void handleButtonAction(ActionEvent action) throws GatewayException {
    		Object source = action.getSource();
    		if(source == Save){
    				JFileChooser fileChooser = new JFileChooser(".xls"); 
    				fileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
	
    				int ret = fileChooser.showSaveDialog(null);
    				if (ret == JFileChooser.CANCEL_OPTION)
    					return;
			
    				File file = fileChooser.getSelectedFile();
    				if(!fileChooser.getSelectedFile().getAbsolutePath().endsWith(".xls")){
    					file = new File(fileChooser.getSelectedFile() + ".xls");
    				}
    				Path.setText(file.getAbsolutePath());
    		}
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
        	
        		ObservableList<Book> books = AppMain.bookGateway.getBooksByPublisherId(cbPublishers.getSelectionModel().getSelectedItem().getId());
        	
        		//new ExcelReport(pathLabel.getText(), books, bGateway, cbPublishers.getSelectionModel().getSelectedItem().getName());
        	
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