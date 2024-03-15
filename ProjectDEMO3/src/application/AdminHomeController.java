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
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;

public class AdminHomeController implements Initializable{
	@FXML Button Issuebookbtn;
	@FXML Button Returnbookbtn;
	@FXML MenuItem Menuitemaddnewuser;
	@FXML Menu menuuser;
	@FXML MenuBar mymenubar;
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
			//Go to Issue book page 
			Issuebookbtn.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				FXMLLoader loader = new FXMLLoader(adminIssueBookController.class.getResource("AdminIssuebook.fxml"));
				try {
					Parent root=loader.load();
					Stage stage=(Stage)Issuebookbtn.getScene().getWindow();
					
					stage.setScene(new Scene(root,1200,800));
					stage.setTitle("Issue Book");
					stage.show();
					
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		
		});
		
		
			//Go to return book page 
			Returnbookbtn.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				FXMLLoader loader = new FXMLLoader(BookReturnController.class.getResource("BookReturn.fxml"));
				try {
					Parent root=loader.load();
					Stage stage=(Stage)Issuebookbtn.getScene().getWindow();
					
					stage.setScene(new Scene(root,1200,800));
					stage.setTitle("Issue Book");
					stage.show();
					
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		
		});
		
			
			//#################            Menu Bar controls         #######################
			
			//Add new User menu
			Menuitemaddnewuser.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				FXMLLoader loader = new FXMLLoader(Addnewusercontroller.class.getResource("addnewuser.fxml"));
				try {
					Parent root=loader.load();
					Stage stage=(Stage)mymenubar.getScene().getWindow();
					
					stage.setScene(new Scene(root,1200,800));
					stage.setTitle("Create new User");
					stage.show();
					
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		
		});
	
	}
}
