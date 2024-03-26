package application;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class AdminHomeController implements Initializable{
	@FXML Button Issuebookbtn;
	@FXML Button Returnbookbtn;
	@FXML MenuItem Menuitemaddnewbook;
	@FXML MenuItem Menuitemaddnewuser;
	@FXML MenuItem Logout;
	@FXML Menu menuuser;
	@FXML Menu bookmenu;
	@FXML Menu myaccountmenu;
	@FXML MenuBar mymenubar;
	@FXML TableView<Issuedbook> tableview;
	@FXML public TableColumn<Issuedbook, String> bookname;
	@FXML public TableColumn<Issuedbook, LocalDate> issuedate;
	@FXML public TableColumn<Issuedbook, Integer> userid;
		
	ObservableList<Issuedbook> observablelist= FXCollections.observableArrayList(
			new Issuedbook("Book1",LocalDate.now(),3));
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
			
		
		
		initializetable();		
		
		
		//#################            Button controls         #######################
		
		
	
	
			//Go to Issue book page 
			Issuebookbtn.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				FXMLLoader loader = new FXMLLoader(adminIssueBookController.class.getResource("AdminIssuebook.fxml"));
				try {
					Parent root=loader.load();
					Stage stage=(Stage)Issuebookbtn.getScene().getWindow();
					
					stage.setScene(new Scene(root,1200,800));
					stage.setTitle("Issue Book");
					stage.show();
					
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		
		});
		
		
			//Go to return book page 
			Returnbookbtn.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				FXMLLoader loader = new FXMLLoader(BookReturnController.class.getResource("BookReturn.fxml"));
				try {
					Parent root=loader.load();
					Stage stage=(Stage)Issuebookbtn.getScene().getWindow();
					
					stage.setScene(new Scene(root,1200,800));
					stage.setTitle("Issue Book");
					stage.show();
					
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		
		});
		
			
			//#################            Menu Bar controls         #######################
			
			//Add new User menu
			Menuitemaddnewuser.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				FXMLLoader loader = new FXMLLoader(Addnewusercontroller.class.getResource("addnewuser.fxml"));
				try {
					Parent root=loader.load();
					Stage stage=(Stage)mymenubar.getScene().getWindow();
					
					stage.setScene(new Scene(root,1200,800));
					stage.setTitle("Create new User");
					stage.show();
					
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		
		});
			
			Menuitemaddnewbook.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent arg0) {
					FXMLLoader loader = new FXMLLoader(Addnewusercontroller.class.getResource("addnewbook.fxml"));
					try {
						Parent root=loader.load();
						Stage stage=(Stage)mymenubar.getScene().getWindow();
						
						stage.setScene(new Scene(root,1200,800));
						stage.setTitle("Create new User");
						stage.show();
						
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			
			});
			
			
			Logout.setOnAction(new EventHandler<ActionEvent>()
			{

				@Override
				public void handle(ActionEvent arg0) {
					// TODO Auto-generated method stub
					FXMLLoader loader = new FXMLLoader(HelloController.class.getResource("login.fxml"));
					try {
						Parent root=loader.load();
						Stage stage=(Stage)mymenubar.getScene().getWindow();
						
						
						
						stage.setScene(new Scene(root,1200,800));
						stage.setTitle("Login Page");
						stage.show();
					} catch(Exception e) {
						e.printStackTrace();
					}
				}
			});
			
		}
	
	public void initializetable()
	{
		bookname.setCellValueFactory(new PropertyValueFactory<>("bookName"));
		issuedate.setCellValueFactory(new PropertyValueFactory<>("issueDate"));
		userid.setCellValueFactory(new PropertyValueFactory<>("userId"));
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		
		String curdate= formatter.format(LocalDate.now());
		
		String issuedate= formatter.format(LocalDate.now().plusDays(31));
		
		String query="SELECT * FROM bookinfo WHERE Available = '0';";
		
		
		
		
		
		
		
		tableview.setItems(observablelist);
		
		
	}
	
}

			
					
					
				
		
			
			
	
			

