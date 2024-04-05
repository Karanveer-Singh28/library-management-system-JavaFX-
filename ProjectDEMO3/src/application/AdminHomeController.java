package application;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
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
	@FXML Button searchbookbtn;
	@FXML Button searchuserbtn;
	@FXML TableView<Issuedbook> tableview;
	@FXML public TableColumn<Issuedbook, String> bookname;
	@FXML public TableColumn<Issuedbook, LocalDate> issuedate;
	@FXML public TableColumn<Issuedbook, Integer> userid;
	@FXML public TableColumn<Issuedbook, Integer> bookid;
		
	ObservableList<Issuedbook> observablelist= FXCollections.observableArrayList();
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
			
		
		//#################            Table initialization         #######################
		initializetable();		
		
		
		//#################            Button controls         #######################
			
		
		
		    //Go to search user page
		    searchuserbtn.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent arg0) {
					// TODO Auto-generated method stub
					FXMLLoader loader = new FXMLLoader(searchuserController.class.getResource("searchuser.fxml"));
					try {
                        Parent root = loader.load();
                        Stage stage = (Stage) searchuserbtn.getScene().getWindow();

                        stage.setScene(new Scene(root, 1200, 800));
                        stage.setTitle("Search User");
                        stage.show();

                    } catch (IOException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
				}
		    	
		    });
		
			//Go to search book page
		    searchbookbtn.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent arg0) {
					// TODO Auto-generated method stub
					FXMLLoader loader = new FXMLLoader(searchbookcontroller.class.getResource("searchbook.fxml"));
					try {
						Parent root = loader.load();
						Stage stage = (Stage) searchbookbtn.getScene().getWindow();

						stage.setScene(new Scene(root, 1200, 800));
						stage.setTitle("Search Book");
						stage.show();

					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
		    	
		    });
	
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
			
			//go to add new book page
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
			
			
			//go to login page
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
	
	
	//#################            Table initialization         #######################
	public void initializetable()
	{
		bookname.setCellValueFactory(new PropertyValueFactory<>("bookName"));
		issuedate.setCellValueFactory(new PropertyValueFactory<>("issueDate"));
		userid.setCellValueFactory(new PropertyValueFactory<>("userId"));
		bookid.setCellValueFactory(new PropertyValueFactory<>("bookId"));
		
		
		int bookid;
		String query;
		
		String query1="SELECT iduserAccount, Bookidissued, Issuedate FROM useraccounts WHERE Bookidissued != 0 OR Bookidissued != null;";
		
		DatabaseConnection connectNow = new DatabaseConnection();
		Connection connectDB = connectNow.getConnection();
		
		Statement statement;
		try {
			statement = connectDB.createStatement();
			ResultSet queryResult = statement.executeQuery(query1);

			while (queryResult.next()) {
				bookid = queryResult.getInt("Bookidissued");
				LocalDate issuedate = LocalDate.parse(queryResult.getString("Issuedate"));
				int userid = queryResult.getInt("iduserAccount");
				
				query="SELECT BookTitle FROM bookinfo WHERE BookId = '"+bookid+"';";
				
				Statement statement1 = connectDB.createStatement();
				ResultSet queryResult1 = statement1.executeQuery(query);
				queryResult1.next();
				
				String bookname = queryResult1.getString(1);
				queryResult1.close();
				

				observablelist.add(new Issuedbook(bookname, issuedate, userid, bookid));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
		//Set the items in the table
		tableview.setItems(observablelist);
		
		
	}
	
}

			
					
					
				
		
			
			
	
			

