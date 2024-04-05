// This class is the controller class for the Login Page. It validates the user login and directs to the appropriate User or Admin Home Page.

package application;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class HelloController implements Initializable {
	
	
	@FXML Button Submitbtn;
	@FXML Button Cancelbtn;
	@FXML Label LoginMessageLabel;
	@FXML TextField usernameTextField;
	@FXML PasswordField passwordPasswordField;
	@FXML Button LogoutBtn;
	@FXML GridPane loginpane;
	
	
	//function to validate login and set appropriate User or Administrator Home page
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		Submitbtn.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				
				DatabaseConnection connectNow = new DatabaseConnection();
				Connection connectDB = connectNow.getConnection();
				
				//Query to verify login credentials
				String verifyLogin = "SELECT count(1), AccountType, idUserAccount FROM useraccounts WHERE Username='" + usernameTextField.getText() + "' AND Password='" + passwordPasswordField.getText() + "'";

				
				 try {
						Statement statement = connectDB.createStatement();
						ResultSet queryResult = statement.executeQuery(verifyLogin);
						
						queryResult.next();
						
						//If the query returns a row, the user is redirected to the appropriate Home Page
							if(queryResult.getInt(1) == 1)
							{
								//If the user is an Administrator, the Admin Home Page is loaded
								if(queryResult.getInt(2) == 1)
								{
									FXMLLoader loader = new FXMLLoader(AdminHomeController.class.getResource("AdminHome.fxml"));
									try {
										Parent root=loader.load();
										Stage stage=(Stage)Submitbtn.getScene().getWindow();
										
										stage.setScene(new Scene(root,1200,800));
										stage.setTitle("Admin Home Page");
										stage.centerOnScreen();
										stage.setMaxHeight(800);
										stage.setMaxWidth(1200);
										stage.show();
										
										
									} catch (IOException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}
								}
								//If the user is a regular user, the User Home Page is loaded
								else if(queryResult.getInt(2) == 0)
								{
									FXMLLoader loader = new FXMLLoader(userhomecontroller.class.getResource("UserHomepage.fxml"));
									try {
										
										
										Parent root=loader.load();
										Stage stage=(Stage)Submitbtn.getScene().getWindow();
										
										userhomecontroller Controller=loader.getController();
										Controller.setUserId(queryResult.getInt(3));
										
										stage.setScene(new Scene(root,1200.0,800.0));
										stage.setTitle("User Home Page");
										stage.centerOnScreen();										
										stage.show();
										
									} catch (IOException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}
								}
								
							}
							
							//If the query returns no rows, the user is prompted to enter the correct Username and Password
							else
							{
								LoginMessageLabel.setText("Incorrect Username or Password!");
							}
						
						 queryResult.close();
				         statement.close();
				 }catch(Exception e1)
					{
						e1.printStackTrace();
					}
				
				
				
			}
		
		
		});
		
		//button to close the application
		Cancelbtn.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				// TODO Auto-generated method stub
				Stage stage=(Stage)Cancelbtn.getScene().getWindow();
				stage.close();	
			}
			
			
		});
		
	}
}

