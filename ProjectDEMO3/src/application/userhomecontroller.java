package application;




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
import javafx.scene.Scene;
import javafx.scene.control.Hyperlink;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class userhomecontroller implements Initializable {
		
		@FXML ImageView book1cover;
		@FXML ImageView book2cover;
		@FXML ImageView book3cover;
		@FXML ImageView book4cover;
		@FXML ImageView book5cover;
		@FXML ImageView book6cover;
		@FXML ImageView book7cover;
		@FXML ImageView book8cover;
		@FXML Hyperlink book1title;
		@FXML Hyperlink book2title;
		@FXML Hyperlink book3title;
		@FXML Hyperlink book4title;
		@FXML Hyperlink book5title;
		@FXML Hyperlink book6title;
		@FXML Hyperlink book7title;
		@FXML Hyperlink book8title;
		@FXML Pane mypane;
		
		
		private ImageView[] bookCovers;
		private Hyperlink[] booktitles;
		

		@Override
		public void initialize(URL arg0, ResourceBundle arg1) {
			// TODO Auto-generated method stub
			bookCovers = new ImageView[]{book1cover, book2cover, book3cover, book4cover, book5cover, book6cover, book7cover, book8cover};
			booktitles = new Hyperlink[] {book1title, book2title, book3title, book4title, book5title, book6title, book7title, book8title};
			
			DatabaseConnection connectNow = new DatabaseConnection();
			Connection connectDB = connectNow.getConnection();  

			String booksquery = "SELECT * FROM bookinfo ;";	
			
			try {
				Statement statement= connectDB.createStatement();
				ResultSet queryResult = statement.executeQuery(booksquery);
				
				String bookcover;
				String booktitle;
				
				 for (int i = 0; i < 8; i++) {
					 	queryResult.next();
					 	bookcover=queryResult.getString(3);
					 	booktitle=queryResult.getString(2);
					 	
					 	
			            bookCovers[i].setImage(new Image(bookcover));
			            booktitles[i].setText(booktitle);
			       }
				 
				 String bookidquery;
				 
				 for (int i = 0; i < 8; i++) {
			            Hyperlink hyperlink = booktitles[i];
			            String name=booktitles[i].getText();
			            
			            bookidquery="SELECT BookId FROM bookinfo WHERE BookTitle='"+ name +"';";
			            queryResult = statement.executeQuery(bookidquery);
			            queryResult.next();
			            
						
			            int  bookId = queryResult.getInt(1); 
			            hyperlink.setOnAction(new EventHandler<ActionEvent>() {
			                @Override
			                public void handle(ActionEvent event) {
			                    try {
			                        // Load the book view FXML file
			                        
			                    	FXMLLoader loader = new FXMLLoader(getClass().getResource("bookview.fxml"));
			                        Stage stage = (Stage)mypane.getScene().getWindow();
			                        stage.setScene(
			                          new Scene(loader.load())
			                        );
			                        stage.centerOnScreen();

			                        BookViewController Controller = loader.getController();
			                        Controller.initData(bookId);
									stage.show();
									
			                    } catch (Exception e) {
			                        e.printStackTrace();
			                    }
			                }
			            });
				 }
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}			
		}
		
	}
		

