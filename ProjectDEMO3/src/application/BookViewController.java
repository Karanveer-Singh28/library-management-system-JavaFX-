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
	
	public void initData(int bookId) {
		// TODO Auto-generated method stub
		
		
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
							
							stage.setScene(new Scene(root,787.0,573.0));
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

	
}
