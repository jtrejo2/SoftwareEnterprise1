package controllers;
import java.io.IOException;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import model.AuditTrail;
import model.Author;
import model.Book;
import views.AppMain;

public class AuditTrailController {

	private static Logger logger = LogManager.getLogger();

	    @FXML
	    private ListView<AuditTrail> ListBook;

	    @FXML
	    private Button BackButton;

	    @FXML
	    private Label AuditLabel;
		private AuditTrail AuditTrail;
		private AuditTrail AuditTrail2;
		private Book book;
		private List<AuditTrail> AuditTrails;
		//assign auditTrails to this.AuditTrails
		public AuditTrailController(List<AuditTrail> AuditTrails, Book book){
			this.AuditTrails = AuditTrails;
			this.book = book;
		}

	    @FXML
	    void handleButtonAction(ActionEvent event) {
	    		Object source = event.getSource();
	    		
	    		if(source == BackButton) {
	    			try{
						logger.info("Add book called");
						FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/BookDetailView.fxml"));
						//publishers = AppMain.publisherGateway.getPublishers();
						loader.setController(new BookDetailController(this.book));//set Detail Controller
						Parent view = loader.load();
						AppMain.rootPane.setCenter(view); //Display
						
					} catch (IOException e) {
						e.printStackTrace();
					}
	    			
	    			
	    		}
	    }

	    	public void initialize(){
	    		ObservableList<AuditTrail> items = ListBook.getItems();
	    		for(AuditTrail a : AuditTrails){
	    			items.add(a);
	    			
	    		}
	    		AuditLabel.setText("Audit Trail for " + this.book.getTitle());
	    	}
}
	    		
	    					
	    					
	    	
	    	
	
