package controllers;

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
public class AuthorDetailController {
	private static Logger logger = LogManager.getLogger();
	
	@FXML private TextField first_name, last_name, dob, gender, web_site;
	@FXML private Button Save;
	
	private Author author;
	
	public AuthorDetailController(Author author){
		this.author = author;
		
	}
	
	@FXML private void handleButtonAction(ActionEvent action) throws Exception {
		Object source = action.getSource();
		
		author.setFirst_name(first_name.getText());
		author.setLast_name(last_name.getText());
		author.setDob(dob.getText());
		author.setGender(gender.getText());
		author.setWeb_site(web_site.getText());
		
		if(source == Save){
			author.Save(author);
			logger.error("Save button was clicked!");
			}
	}
	
	public void initialize(){
		logger.info("Detail View init called");
		first_name.setText(author.getFirst_name());
		last_name.setText(author.getLast_name());
		dob.setText(author.getDob());
		gender.setText(author.getGender());
		web_site.setText(author.getWeb_site());
	}
}
