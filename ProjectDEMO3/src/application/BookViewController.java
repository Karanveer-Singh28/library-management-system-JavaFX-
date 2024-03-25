package application;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class BookViewController implements Initializable{
	
	@FXML Label booktitlelbl;
	@FXML Label bookauthorlbl;
	@FXML Label bookpublisherlbl;
	@FXML Label bookavailablelbl;
	@FXML Button homebtn;
	@FXML ImageView bookimg;
	@FXML Button issuebtn;
	@FXML Label successlbl;
	LocalDate date;
	
	int userId;
	int bookId;
	
	public void initData(int bookId,int UserId) {
		// TODO Auto-generated method stub
		
		this.userId=UserId;
		this.bookId=bookId;
		
		DatabaseConnection connectNow = new DatabaseConnection();
		Connection connectDB = connectNow.getConnection();  

		String booksquery = "SELECT * FROM bookinfo WHERE BookId='"+bookId+"' ;";	
		
		Statement statement;
		try {
			statement = connectDB.createStatement();
			ResultSet queryResult = statement.executeQuery(booksquery);
			
			queryResult.next();
			
			String img=queryResult.getString(3);
			String title=queryResult.getString(2);
			String author=queryResult.getString(5);
			String publ=queryResult.getString(4);
			
			booktitlelbl.setText(title);
			bookauthorlbl.setText(author);
			bookpublisherlbl.setText(publ);
			bookimg.setImage(new Image(img));
			
			boolean available;
			
			if(queryResult.getInt(6)==1)
				available=true;
			else
				available=false;
			
			bookavailablelbl.setText(Boolean.toString(available));
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
		homebtn.setOnAction(new EventHandler<ActionEvent>()
				{

					@Override
					public void handle(ActionEvent arg0) {
						// TODO Auto-generated method stub
						FXMLLoader loader = new FXMLLoader(userhomecontroller.class.getResource("UserHomepage.fxml"));
						try {
											
							Parent root=loader.load();
							Stage stage=(Stage)homebtn.getScene().getWindow();
							
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
		
		issuebtn.setOnAction(new EventHandler<ActionEvent>()
		{

			@Override
			public void handle(ActionEvent arg0) {
				// TODO Auto-generated method stub
							
				DatabaseConnection connectNow = new DatabaseConnection();
				Connection connectDB = connectNow.getConnection();  
		
				String user= "SELECT * FROM useraccounts WHERE idUserAccount = '"+ userId +"';";
				
				Statement statement;
				try {
					statement = connectDB.createStatement();
					ResultSet queryResult = statement.executeQuery(user);
					queryResult.next();
					String name= queryResult.getString(2)+" "+queryResult.getString(3);
					
					date= LocalDate.now();
					System.out.println(date);
					
					String updatequery="UPDATE `librarymanager`.`useraccounts` SET `Bookidissued` = '"+bookId+"', `Issuedate` = '"+date+"' WHERE (`idUserAccount` = '"+userId+"');";
					statement.executeUpdate(updatequery);
					
					successlbl.setText(name+" Issed the book");
					
				}catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		});
	}
}
