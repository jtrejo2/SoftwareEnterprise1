package controllers;
import java.io.IOException;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import model.AuditTrail;
import model.AuthorAuditTrail;
import model.Book;
import views.AppMain;
import model.Author;

public class AuthorAuditTrailController {

/**
 * Sample Skeleton for 'AuthorAuditTrailView.fxml' Controller Class
 */
    @FXML // fx:id="ListAuthor"
    private ListView<AuthorAuditTrail> ListAuthor; // Value injected by FXMLLoader

    @FXML // fx:id="BackButton"
    private Button BackButton; // Value injected by FXMLLoader

    @FXML // fx:id="AuditLabel"
    private Label AuditLabel; // Value injected by FXMLLoader
    private Author Author;
    private List<AuthorAuditTrail> AuditTrails;
    private  static Logger logger = LogManager.getLogger();
  //assign auditTrails to this.AuditTrails
  		public AuthorAuditTrailController(List<AuthorAuditTrail> AuditTrails, Author Author){
  			this.AuditTrails = AuditTrails;
  			this.Author = Author;
  		}


    @FXML
    void handleButtonAction(ActionEvent event) {
    	Object source = event.getSource();
    	//System.out.println("this is the author" + this.Author);
    	if(source == BackButton) {
			try{
				logger.info("Add book called");
				FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/AuthorDetailView.fxml"));
				//publishers = AppMain.publisherGateway.getPublishers();
				loader.setController(new AuthorDetailController(this.Author));//set Detail Controller
				Parent view = loader.load();
				AppMain.rootPane.setCenter(view); //Display
				
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			
		}
    }
	public void initialize(){
		ObservableList<AuthorAuditTrail> items = ListAuthor.getItems();
		for(AuthorAuditTrail a : AuditTrails){
			items.add(a);
			
		}
		AuditLabel.setText("Audit Trail for " + this.Author.getFirst_name());
	}
}


