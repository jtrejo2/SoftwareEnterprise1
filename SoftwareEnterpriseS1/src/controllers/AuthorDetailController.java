package controllers;

import java.time.LocalDateTime;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import model.*;
import views.AppMain;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

//AuthorDetailList Controller 
public class AuthorDetailController {
	private static Logger logger = LogManager.getLogger();
	
	@FXML private TextField first_name, last_name, dob, gender, web_site, last_modified;
	@FXML private Button Save;
	@FXML private Button Audit;
	
	private Author author;

	
	//assign author to this.author
	public AuthorDetailController(Author author){
		this.author = author;
		
	}
	//when save button is clicked call the save Author save method with the entered user information
	@FXML private void handleButtonAction(ActionEvent action) throws Exception {
		Object source = action.getSource();
		
		author.setFirst_name(first_name.getText());
		author.setLast_name(last_name.getText());
		author.setDob(dob.getText());
		author.setGender(gender.getText());
		author.setWeb_site(web_site.getText());
		
		if(source == Save){
			if (author.getId() == 0) {
				try {
					logger.error("Save button was clicked!");
			    		author.Save(author);

				} catch (Exception e) {
					//Alert alert = new Alert (AlertType.WARNING, "Error saving please try again");
					//alert.showAndWait();
					AlertHelper.showWarningMessage("Error", "Error saving", "Please try again");
				}
			}
			else {
				try {
					logger.error("Save button was clicked!");
		    			LocalDateTime original = AppMain.authorGateway.getTimeStamp(author);
		    			System.out.println("Original Timestamp = " + original);
		    			System.out.println("Last Modified = " + author.getLastModified());
		    			
		    			if(!original.equals(author.getLastModified())) {
		    				logger.error("Cannot Save! Please reload and try again.");
		    				//Alert alert = new Alert (AlertType.WARNING, "Cannot Save! Please reload and try again.");
						//alert.showAndWait();
		    				AlertHelper.showWarningMessage("Error", "Error saving", "Please try again");
		    				return;
		    			}
		    			author.Save(author);
		    			
				} catch (Exception e) {
					//Alert alert = new Alert (AlertType.WARNING, "Error saving please try again");
					//alert.showAndWait();
					AlertHelper.showWarningMessage("Error", "Error saving", "Please try again");
				}
			}

			
		}
		if(source == Audit) {
			System.out.println("here we are");
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/AuthorAuditTrailView.fxml"));
			List<AuthorAuditTrail> Authors = author.GetAuthorAuditTrail();
			loader.setController(new AuthorAuditTrailController(Authors,author));//set controller
			
			Parent view = loader.load();
			AppMain.rootPane.setCenter(view);//display
			return;
			
		}
	}
	
	//initialize
	public void initialize(){
		logger.info("Detail View init called");
		first_name.setText(author.getFirst_name());
		last_name.setText(author.getLast_name());
		dob.setText(author.getDob());
		gender.setText(author.getGender());
		web_site.setText(author.getWeb_site());
	}
}
