// Add new user to the database

package application;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Addnewusercontroller implements Initializable{
	@FXML Button Homebtn;
	@FXML Button Confirmbtn;
	@FXML AnchorPane titlepane;
	@FXML TextField Firstnametxt;
	@FXML TextField Lastnametxt;
	@FXML TextField Usernametxt;
	@FXML PasswordField Passwordtxt;
	@FXML PasswordField Passwordconfirmtxt;
	@FXML ComboBox<String> accountcombo;
	@FXML Label errorlabel;
	@FXML Label successlabel;
	
	
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
			
		//Set the account type combo box
		accountcombo.setItems(FXCollections.observableArrayList("Staff","User"));
		//Return to Admin home page 
			Homebtn.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				FXMLLoader loader = new FXMLLoader(AdminHomeController.class.getResource("AdminHome.fxml"));
				try {
					Parent root=loader.load();
					Stage stage=(Stage)Homebtn.getScene().getWindow();
					
					stage.setScene(new Scene(root,1200,800));
					stage.setTitle("Admin Home");
					stage.show();
					
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		
		});
			
			//Confirm button to add new user to the database
			Confirmbtn.setOnAction(new EventHandler<ActionEvent>() {
				
				
				@Override
				public void handle(ActionEvent arg0) {
					// TODO Auto-generated method stub
					errorlabel.setText("");
					
					//Check if all fields are filled
					if((Firstnametxt.getText()==null || Firstnametxt.getText().isEmpty()) || (Lastnametxt.getText()==null || Lastnametxt.getText().isEmpty()) || (Usernametxt.getText()==null || Usernametxt.getText().isEmpty()) || (Passwordtxt.getText()==null || Passwordtxt.getText().isEmpty()) || (Passwordconfirmtxt.getText()==null || Passwordconfirmtxt.getText().isEmpty()) || (accountcombo.getValue()==null))
					{
						errorlabel.setText("Enter Full Information");
					}
					
					
					else
					{
						DatabaseConnection connectNow = new DatabaseConnection();
						Connection connectDB = connectNow.getConnection();
				
						errorlabel.setText("");
						
						//Get the values from the fields
						String fname=Firstnametxt.getText();
						String lname=Lastnametxt.getText();
						String username=Usernametxt.getText();
						String password=Passwordtxt.getText();
						String passwordconfirm=Passwordconfirmtxt.getText();
						int accounttype;
						
						//Check the account type
						if(accountcombo.getValue()== "Staff")
							accounttype=1;
						else
							accounttype=0;
						
						String checkusername="SELECT * FROM useraccounts WHERE username='"+username+"';";
						
						Statement statement;
						try {
							statement = connectDB.createStatement();
							ResultSet queryResult = statement.executeQuery(checkusername);	
							
							//Check if the username already exists
							if(queryResult.next())
								errorlabel.setText("Username already exists");
							
							//Check if the passwords match
							else if (!password.equals(passwordconfirm))
							{
								errorlabel.setText("Passwords do not match !");
							}
							
							//Add user to database
							else {
								String updatequery="INSERT INTO `librarymanager`.`useraccounts` (`Firstname`, `LastName`, `Username`, `Password`, `AccountType`) VALUES ('"+fname+"', '"+lname+"', '"+username+"', '"+password+"', '"+accounttype+"');";
								statement.executeUpdate(updatequery);
								successlabel.setText("Account Created Succesfully!");
							}
							
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					
				}
							
			});		
	}
}
