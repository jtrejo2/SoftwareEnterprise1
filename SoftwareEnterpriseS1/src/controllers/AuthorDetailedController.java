package controllers;
import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import model.*;
import views.*;
public class AuthorDetailedController {
	private static Logger logger = LogManager.getLogger();
	
	@FXML private TextField tfid, tffirst_name, tflast_name, tfdob, tfgender, tfweb_site;
	@FXML private Button Save;
	
	private Author author;
	
	public AuthorDetailedController(Author author){
		this.author = author;
		
	}
	
	@FXML private void handleButtonAction(ActionEvent action) throws Exception {
		Object source = action.getSource();
		
		
		author.setFirst_name(tffirst_name.getText());
		author.setLast_name(tflast_name.getText());
		author.setDob(tfdob.getText());
		author.setGender(tfgender.getText());
		author.setWeb_site(tfweb_site.getText());
		
		if(source == Save){
			author.Save(author);
		logger.error("Save button was clicked!");
		}
	}
	
	public void initialize(){
		logger.error("Detailed View init has been called");
		tfid.setText(author.getId() + "");
		tffirst_name.setText(author.getFirst_name());
		tflast_name.setText(author.getLast_name());
		tfdob.setText(author.getDob());
		tfgender.setText(author.getGender());
		tfweb_site.setText(author.getWeb_site());
	}
}
