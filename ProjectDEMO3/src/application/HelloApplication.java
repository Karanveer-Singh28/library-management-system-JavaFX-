package application;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;


public class HelloApplication extends Application {
	@Override
	
	
	//Display the first login page 
	public void start(Stage primaryStage) {
		try {
			AnchorPane root= FXMLLoader.load(getClass().getResource("login.fxml"));
			Scene scene = new Scene(root,787.0,573.0);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.centerOnScreen();
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
