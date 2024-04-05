// list the users in the tableview and display the details of the selected user in the labels

package application;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class searchuserController implements Initializable{
	
	
	@FXML Button Homebtn;
	@FXML TableView<User> tableview;
	@FXML public TableColumn<User, String> firstname;
	@FXML public TableColumn<User, String> lastname;
	@FXML public TableColumn<User, Integer> userid;
	@FXML public TableColumn<User, String> username;
	@FXML Label Firstnamelbl;
	@FXML Label Lastnamelbl;
	@FXML Label Usernamelbl;
	@FXML Label Useridlbl;
	@FXML Label Activebooklbl;
	@FXML Label Bookidlbl;
	@FXML Label Issuedatelbl;
	@FXML Label bookidlabel;
	@FXML Label issuedatelabel;
	@FXML Label label1;
	@FXML Label label2;
	
	
	ObservableList<User> observablelist= FXCollections.observableArrayList();
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
		// Initialize the tableview
		tableinit();
		
		// Display the data in the labels when a user is selected
		tableview.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
			if (newSelection != null) {
				
				displayDataInLabels();
			}
		});
		
		
		//Return to home
		Homebtn.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				// TODO Auto-generated method stub
				Stage stage = (Stage) Homebtn.getScene().getWindow();
				Parent root;
				try {
					root = FXMLLoader.load(getClass().getResource("AdminHome.fxml"));
					Scene scene = new Scene(root);
					stage.setScene(scene);
					stage.show();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		});		
	}
	
	// Initialize the tableview
	public void tableinit() {
		userid.setCellValueFactory(new PropertyValueFactory<User, Integer>("userid"));
		firstname.setCellValueFactory(new PropertyValueFactory<User, String>("firstname"));
		lastname.setCellValueFactory(new PropertyValueFactory<User, String>("lastname"));
		username.setCellValueFactory(new PropertyValueFactory<User, String>("username"));
		
		
		String query = "SELECT * FROM useraccounts;";
		
		DatabaseConnection connectNow = new DatabaseConnection();
		Connection connectDB = connectNow.getConnection();
		
		int userID ;
		String  UserName;
		String FirstName, LastName;
		
		
		
		Statement statement;
		try {
			statement=connectDB.createStatement();
			ResultSet queryResult = statement.executeQuery(query);
			
			while(queryResult.next())
			{
				userID= queryResult.getInt(1);
				FirstName=queryResult.getString(2);
				LastName=queryResult.getString(3);
				UserName=queryResult.getString(4);
				
				
				observablelist.add(new User(userID, FirstName, LastName, UserName));
			}
			
			
			
		}catch(SQLException e)
		{System.out.println(e);
			e.printStackTrace();}
		
		tableview.setItems(observablelist);
	}
	
	// Display the data in the labels
	 private void displayDataInLabels() {
	        // Get the selected user from the tableview
	        User selectedUser = tableview.getSelectionModel().getSelectedItem();
	        
	        
	        if (selectedUser != null) {
	        	int userid = selectedUser.getUserid();
	        	
	        	
	        	String query = "SELECT * FROM useraccounts WHERE iduserAccount = '"+userid+"';";
	        	
	        	DatabaseConnection connectNow = new DatabaseConnection();
	        	Connection connectDB = connectNow.getConnection();
	        	
	        	try {
					Statement statement=connectDB.createStatement();
					ResultSet queryResult = statement.executeQuery(query);					
					
					queryResult.next();
					
					int bookidissued=queryResult.getInt(7);
					// Check if the user has an issued book
					if(bookidissued !=0)
					{
						String querybook= "SELECT * FROM bookinfo WHERE BookId = "+bookidissued+";";
						Statement statement1=connectDB.createStatement();
						ResultSet queryResultbook= statement1.executeQuery(querybook);
						queryResultbook.next();
						Activebooklbl.setText(queryResultbook.getString(2));
						bookidlabel.setVisible(true);
						Bookidlbl.setText(Integer.toString(bookidissued));
						issuedatelabel.setVisible(true);
						String issuedate = (queryResult.getDate(8)).toString();
						Issuedatelbl.setText(issuedate);
						Firstnamelbl.setText(selectedUser.getFirstname());
				        Lastnamelbl.setText(selectedUser.getLastname());
				        Usernamelbl.setText(selectedUser.getUsername());	  
				        Useridlbl.setText(Integer.toString(selectedUser.getUserid()));
				        label1.setVisible(true);
				        label2.setVisible(true);
				        queryResultbook.close();
				        statement1.close();	       
						
					}
					// If the user has not issued a book
					else {
						Activebooklbl.setText("None");
						bookidlabel.setVisible(false);
						issuedatelabel.setVisible(false);
						Bookidlbl.setText("");
						Issuedatelbl.setText("");
						label1.setVisible(false);
						label2.setVisible(false);
						
						Useridlbl.setText(Integer.toString(selectedUser.getUserid()));
						Firstnamelbl.setText(selectedUser.getFirstname());
				        Lastnamelbl.setText(selectedUser.getLastname());
				        Usernamelbl.setText(selectedUser.getUsername());	
					}
					
					
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	        	
	        }
	        
	 }
}
