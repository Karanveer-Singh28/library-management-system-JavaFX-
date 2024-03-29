package application;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
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
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class searchbookcontroller implements Initializable {
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
	@FXML TextField searchfield;
	@FXML Button homebtn;
	@FXML Label resultlbl;
	
	
	private ImageView[] bookCovers;
	private Hyperlink[] booktitles;
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	
		bookinit();
		
		homebtn.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				// TODO Auto-generated method stub
				Stage stage = (Stage) homebtn.getScene().getWindow();
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
		
		searchfield.textProperty().addListener((observable, oldValue, newValue) -> onSearch());
		
	}
	
	
	public void bookinit()
	{
		bookCovers = new ImageView[]{book1cover, book2cover, book3cover, book4cover, book5cover, book6cover, book7cover, book8cover};
		booktitles = new Hyperlink[] {book1title, book2title, book3title, book4title, book5title, book6title, book7title, book8title};
		
		DatabaseConnection connectNow = new DatabaseConnection();
		Connection connectDB = connectNow.getConnection();
		
		String booksquery = "SELECT * FROM bookinfo ORDER BY RAND();";
		
		resultlbl.setText("Recommended Books:");
		
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
			 
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
	
	}
	
	@FXML
	public void onSearch() {
	    String searchText = searchfield.getText().trim();
	    if (!searchText.isEmpty()) {
	        String searchQuery = "SELECT * FROM bookinfo WHERE BookTitle LIKE ? LIMIT 8;";
	        
	        try (Connection connectDB = new DatabaseConnection().getConnection();
	             PreparedStatement preparedStatement = connectDB.prepareStatement(searchQuery)) {
	            
	            preparedStatement.setString(1, "%" + searchText + "%");
	            ResultSet queryResult = preparedStatement.executeQuery();
	            
	            int index = 0;
	            while (queryResult.next() && index < bookCovers.length) {
	                String bookcover = queryResult.getString("BookCoverImgUrl");
	                String booktitle = queryResult.getString("BookTitle");
	                
	                bookCovers[index].setImage(new Image(bookcover));
	                booktitles[index].setText(booktitle);
	                index++;
	            }
	            
	                     
	            // If less than 8 results, clear the remaining images and titles
	            for (int i = index; i < bookCovers.length; i++) {
	                bookCovers[i].setImage(null); // or set to a default image
	                booktitles[i].setText("");
	            }
	            
				if (index == 0) {
					resultlbl.setText("Sorry, No results found  :(");
				} else {
					resultlbl.setText("Search Results:");
				}
				
	            
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	    
	    else
	    {
	    	bookinit();
	    	
	    }
	    
	    
	}
	
}