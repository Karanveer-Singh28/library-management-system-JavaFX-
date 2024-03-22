package application;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
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
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class addnewbookcontroller implements Initializable {
	@FXML Button Homebtn;
	@FXML Label bookidlabel;
	@FXML Button addbookbtn;
	@FXML TextField booktitletxt;
	@FXML TextField authortxt;
	@FXML TextField publishertxt;
	@FXML ComboBox<String> conditioncombo;	
	@FXML Label errorlabel;
	@FXML Button uploadpicture;
	@FXML ImageView imagedisplay;
	@FXML AnchorPane myanchorpane;
	@FXML Label successlabel;
	
	
	public static String path;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
			// TODO Auto-generated method stub
			conditioncombo.setItems(FXCollections.observableArrayList("New","Used"));
		
		
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
			
			uploadpicture.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent arg0) {
					// TODO Auto-generated method stub
					FileChooser fileChooser=new FileChooser();
					fileChooser.setTitle("Upload Vehicle Image");
					fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Image Files","*.jpg","*.png","*.jpeg","*.svg"));
					Stage stage=(Stage)myanchorpane.getScene().getWindow();
					
					File file=fileChooser.showOpenDialog(stage);
					if(file==null) {
						errorlabel.setText("Upload the book cover image!");
					}else {
						errorlabel.setText("");
					path="file:"+file.getAbsolutePath();
					
					Image image=new Image(path);
					image.getHeight();
					image.getWidth();
					
					imagedisplay.setImage(image);	
					}
				}
				
			});
			
			
			addbookbtn.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				// TODO Auto-generated method stub
								
				if((booktitletxt.getText().isEmpty() || booktitletxt.getText()==null ) || (authortxt.getText().isEmpty() || authortxt.getText()==null) || (conditioncombo.getValue()== null) || (path.isEmpty() || path==null) || (publishertxt.getText().isEmpty() || publishertxt.getText()==null))
				{ errorlabel.setText("Enter full details !");
				}
				
				else
				{
					String Title=booktitletxt.getText();
					String Author=authortxt.getText();
					String Publisher=publishertxt.getText();
					String Bookcondition=conditioncombo.getValue();
					
					String uploadquery="INSERT INTO `librarymanager`.`bookinfo` (`BookTitle`, `BookCoverImgUrl`, `Publisher`, `Author`, `Available`, `BookCondition`) VALUES ('"+Title+"', '"+path+"', '"+Publisher+"', '"+Author+"', '1', '"+Bookcondition+"');";
					
					DatabaseConnection connectNow = new DatabaseConnection();
					Connection connectDB = connectNow.getConnection();
					
					try {
						Statement statement =connectDB.createStatement();
						statement.executeUpdate(uploadquery);
						successlabel.setText("Book added Succesfully!");
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}
			}			
		});
	}
}

