package controllers;

import java.io.File;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import model.BookGateway;
import model.PublisherGateway;
import views.AppMain;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import model.Book;
import model.Publisher;

public class ExcelReportController implements Initializable {

	@FXML ComboBox<Publisher> cbPublishers;
	private List<Publisher> publishers;
    @FXML private Label Path;
    @FXML private Button Generate;


    public ExcelReportController() {
    	
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