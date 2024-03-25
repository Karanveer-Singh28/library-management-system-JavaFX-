package application;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class adminIssueBookController implements Initializable{
	
	@FXML Button Homebtn;
	@FXML TextField BookIDtxt;
	@FXML TextField UserIDtxt;
	@FXML DatePicker Issuedate;
	@FXML Button Searchbook;
	@FXML Label Messagelbl;
	@FXML Label Titlelbl;
	@FXML Label Authorlbl;
	@FXML Label Publisherlbl;
	@FXML Label Availabilitylbl;
	@FXML ImageView Bookcover;
	@FXML Button Issuebookbtn;
	
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
			
		
		//Return to Admin Home page 
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
			
			
			//Searches if the book is available in DB and displays book info
			Searchbook.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent arg0) {
				
					DatabaseConnection connectNow = new DatabaseConnection();
					Connection connectDB = connectNow.getConnection();
			
					String book = "SELECT * FROM bookinfo WHERE BookID= '"+ BookIDtxt.getText() +"';";
				
					Statement statement;
					try {
						statement = connectDB.createStatement();
						ResultSet queryResult = statement.executeQuery(book);					
						
						
						if(queryResult.next() != true)
						{
							Messagelbl.setText("Invalid Book ID !");
						}
						
						else
						{
							Messagelbl.setText("");
							Titlelbl.setText(queryResult.getString(2));
							Authorlbl.setText(queryResult.getString(5));
							Publisherlbl.setText(queryResult.getString(4));
							Availabilitylbl.setText(Integer.toString(queryResult.getInt(6)));
							Bookcover.setImage(new Image(queryResult.getString(3)));							
						}
						queryResult.close();
				        statement.close();
					
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					 
				}
			});
			
			
			Issuebookbtn.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent arg0) {
					// TODO Auto-generated method stub
					int userid=Integer.parseInt(UserIDtxt.getText());    //TODO NEED TO CHECK IF ANY FIELD IS BLANK 
					int bookid=Integer.parseInt(BookIDtxt.getText());
					
					DatabaseConnection connectNow = new DatabaseConnection();
					Connection connectDB = connectNow.getConnection();  
			
					String book = "SELECT * FROM bookinfo WHERE BookID= '"+ bookid +"';";
					String user= "SELECT * FROM useraccounts WHERE idUserAccount = '"+ userid +"';";
					
					Statement statement;
					try {
						statement = connectDB.createStatement();
						ResultSet queryResult = statement.executeQuery(book);
						
						if(queryResult.next() != true)
						{
							Messagelbl.setText("Invalid Book ID !");
						}
						
						else
						{
							Messagelbl.setText("");
							Titlelbl.setText(queryResult.getString(2));
							Authorlbl.setText(queryResult.getString(5));
							Publisherlbl.setText(queryResult.getString(4));
							Availabilitylbl.setText(Integer.toString(queryResult.getInt(6)));
							Bookcover.setImage(new Image(queryResult.getString(3)));	
							int booksavailable=queryResult.getInt(6);							
							
							queryResult = statement.executeQuery(user);
					        
							if(queryResult.next() != true)
							{
								Messagelbl.setText("Invalid User ID !");
							}
							
							else if(queryResult.getString(7) !=  null && queryResult.getInt(7)!=0)
							{
								Messagelbl.setText("User already has an Unreturned book !");
							}
							
							else if(BookIDtxt.getText()== null || UserIDtxt.getText()==null || Issuedate.getValue()==null)
							{
								Messagelbl.setText("Please enter all the information!");
							}
							
							else 
							{
								DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
								String updateuser= "UPDATE `librarymanager`.`useraccounts` SET `Bookidissued` = '"+ bookid +"', `Issuedate` = '" +formatter.format(Issuedate.getValue()) +"' WHERE (`idUserAccount` = '" + userid +"');";
								
								statement.executeUpdate(updateuser);
								
								String updatebook = "UPDATE `librarymanager`.`bookinfo` SET `Available` = '"+ booksavailable +"' WHERE (`BookID` = '3');";
								statement.executeUpdate(updatebook);
								
								
								Messagelbl.setText("Book Issed");
								
								statement.close();
								queryResult.close();
							}				
						}
					}catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}						
			});		
		}
}

			
			
			
			
		
		
		
		
		

