// Purpose: Controller for Payment.fxml

package application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;



public class Paymentcontroller implements Initializable {
	
	@FXML Button backbtn;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
	
		//Return to book return page
		backbtn.setOnAction(new EventHandler<ActionEvent>() {

		@Override
		public void handle(ActionEvent arg0) {
			FXMLLoader loader = new FXMLLoader(BookReturnController.class.getResource("BookReturn.fxml"));
			try {
				Parent root=loader.load();
				Stage stage=(Stage)backbtn.getScene().getWindow();
				
				stage.setScene(new Scene(root,1200,800));
				stage.setTitle("Issue Book");
				stage.show();
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	
	});
	}
}
