package application;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;

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
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class BookReturnController implements Initializable{
	@FXML Button Homebtn;
	@FXML Button paycard;
	@FXML Button paycash;
	@FXML Button calculate;
	@FXML TextField bookidtxt;
	@FXML DatePicker returndate;
	@FXML Label Pricedisplaylabel;
	@FXML Label errorlabel;
	@FXML Label confirmationlabel;
	@FXML ComboBox<String> bookcondition;
	


	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		bookcondition.setItems(FXCollections.observableArrayList("New","Used","Damaged Level 1","Damaged Level 2"));
		
			
		//Return to home 
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
			
			
			
			calculate.setOnAction(new EventHandler<ActionEvent>()
					{
				public void handle(ActionEvent arg0) {
					
					if(bookidtxt.getText() == null || returndate.getValue() == null || bookcondition.getValue()==null)
					{ errorlabel.setText("Enter full details !");}
					
					else {
					
					DatabaseConnection connectNow = new DatabaseConnection();
					Connection connectDB = connectNow.getConnection();
					
					String condition;
					double cost;
					int  bookid=Integer.parseInt(bookidtxt.getText());
			
					String date = "SELECT Issuedate FROM useraccounts WHERE Bookidissued='" + bookid + "';";
					
					Statement statement;
					try {
						statement = connectDB.createStatement();
						ResultSet queryResult = statement.executeQuery(date);
						
						if(queryResult.next() != true)
						{
							errorlabel.setText("Invalid Book ID or Book is already returned!");
						}
						else {
						Date start = queryResult.getDate(1) ;
						Date stop = Date.valueOf(returndate.getValue());
						
						long diffInMillies = Math.abs(start.getTime() - stop.getTime());
						long diff =TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
												
						if(diff<=31)
						{
							cost= diff * 0.5;
						}
						else
						{
							cost=15.5+ ((diff-31)*0.8);
						}
						
						String conditionquery="SELECT BookCondition, Available FROM bookinfo WHERE BookID='" + bookid + "';";
						queryResult = statement.executeQuery(conditionquery);
						queryResult.next();
						condition=queryResult.getString(1);
												
						if(condition == (bookcondition.getValue())) 
						{	cost+=0;			}
						else if(bookcondition.getValue() == "Used")
						{	cost+=10;	}
						else if(bookcondition.getValue() == "Damaged Level 1")
						{	cost+=20;	}
						else if(bookcondition.getValue() == "Damaged Level 2")
						{	cost+=35;	}
						
						Pricedisplaylabel.setText("$ "+ cost);
						
						}
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						
					}
					
				}
				}
			});
					
				
							
			// Go to payment screen	
			paycard.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent arg0) {
					if(Pricedisplaylabel.getText()== "") {
						errorlabel.setText("Calculate total first !");
					}
					else {
						errorlabel.setText("");
						
						
					FXMLLoader loader = new FXMLLoader(Paymentcontroller.class.getResource("Payment.fxml"));
					try {
						Parent root=loader.load();
						Stage stage=(Stage)Homebtn.getScene().getWindow();
						
						stage.setScene(new Scene(root,1200,800));
						stage.setTitle("Card Payment");
						stage.show();
						
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}	
				}
			});	
			
			
			
			paycash.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent arg0) {
					if(Pricedisplaylabel.getText()== "") {
						errorlabel.setText("Calculate total first !");
					}
					else {
						errorlabel.setText("");
						DatabaseConnection connectNow = new DatabaseConnection();
						Connection connectDB = connectNow.getConnection();
						
						
						
						Statement statement;
						try {
							statement = connectDB.createStatement();
							int bookid=Integer.parseInt(bookidtxt.getText());	
							String condition=bookcondition.getValue();
							String querybook="UPDATE `librarymanager`.`bookinfo` SET `Available` ='1' , `BookCondition` = '"+condition+"' WHERE (`BookID` = '"+bookid+"');";
							statement.executeUpdate(querybook);
							String queryuser="UPDATE `librarymanager`.`useraccounts` SET `Bookidissued` = '0' WHERE (`Bookidissued` = '"+bookid+"');";
							statement.executeUpdate(queryuser);
							confirmationlabel.setText("Book Returned");
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}				
					}	
				}
			});	
			
	}	
		
}
