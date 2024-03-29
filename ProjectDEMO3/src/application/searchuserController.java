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
	ObservableList<User> observablelist= FXCollections.observableArrayList();
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		tableinit();
		
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
	
	
	public void tableinit() {
		userid.setCellValueFactory(new PropertyValueFactory<User, Integer>("userid"));
		firstname.setCellValueFactory(new PropertyValueFactory<User, String>("firstname"));
		lastname.setCellValueFactory(new PropertyValueFactory<User, String>("lastname"));
		username.setCellValueFactory(new PropertyValueFactory<User, String>("username"));
		
		
		String query = "SELECT * FROM useraccounts;";
		String querybook= "SELECT * FROM bookinfo WHERE BookId = ? ;"; 
		
		DatabaseConnection connectNow = new DatabaseConnection();
		Connection connectDB = connectNow.getConnection();
		
		int userID, bookID;
		String bookname, UserName;
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
				bookID=queryResult.getInt(7);
				
				observablelist.add(new User(userID, FirstName, LastName, UserName));
			}
			
			
			
		}catch(SQLException e)
		{System.out.println(e);
			e.printStackTrace();}
		
		tableview.setItems(observablelist);
	}

}
