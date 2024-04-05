package application;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
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
import javafx.stage.Stage;

public class useraccountcontroller implements Initializable{
	
	@FXML Button Homebtn;
	@FXML Label Firstnamelbl;
	@FXML Label Lastnamelbl;
	@FXML Label Usernamelbl;
	@FXML Label activebooklbl;
	 int userId;
	
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
		//Return to home
		Homebtn.setOnAction(new EventHandler<ActionEvent>()
		{

			@Override
			public void handle(ActionEvent arg0) {
				// TODO Auto-generated method stub
				FXMLLoader loader = new FXMLLoader(userhomecontroller.class.getResource("UserHomepage.fxml"));
				try {
									
					Parent root=loader.load();
					Stage stage=(Stage)Homebtn.getScene().getWindow();
					
					userhomecontroller Controller=loader.getController();
					Controller.setUserId(userId);
					
					stage.setScene(new Scene(root,1200.0,800.0));
					stage.setTitle("User Home Page");
					stage.centerOnScreen();
					stage.show();
					
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});			
	}
	
	//Method to initialize the labels
	public void intializeLabels() {
		System.out.println(userId);
		String query = "SELECT * FROM useraccounts WHERE iduserAccount = '"+userId+"';";
		
		DatabaseConnection connectNow = new DatabaseConnection();
		Connection connectDB = connectNow.getConnection();
		
		try {
			Statement statement=connectDB.createStatement();
			ResultSet queryResult = statement.executeQuery(query);					
			
			queryResult.next();
			
			int bookidissued=queryResult.getInt(7);
			System.out.println(bookidissued);
			if(bookidissued !=0)
			{
				String querybook= "SELECT * FROM bookinfo WHERE BookId = "+bookidissued+";";
				Statement statement1=connectDB.createStatement();
				ResultSet queryResultbook= statement1.executeQuery(querybook);
				queryResultbook.next();
				activebooklbl.setText(queryResultbook.getString(2));
				Firstnamelbl.setText(queryResult.getString(2));
		        Lastnamelbl.setText(queryResult.getString(3));
		        Usernamelbl.setText(queryResult.getString(4));	  
		        queryResultbook.close();
		        statement1.close();	       
				}
			
			else	
				{
                activebooklbl.setText("No book issued");
                Firstnamelbl.setText(queryResult.getString(2));
                Lastnamelbl.setText(queryResult.getString(3));
                Usernamelbl.setText(queryResult.getString(4));
				}
			}catch (SQLException e) {
				System.out.println(e);
				// TODO Auto-generated catch block
                e.printStackTrace();
            }
		
		
			
		
		
	}

	//Method to set the user id
	public void setUserId(int userId) {
		// TODO Auto-generated method stub
		this.userId=userId;
		intializeLabels();
		System.out.println(this.userId);
	}
		
	
	
}
