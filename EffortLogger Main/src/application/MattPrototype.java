package application;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.layout.*;
import javafx.scene.control.*;

//this code was written by Matthew Ian Wang, student #1222610662

public class MattPrototype{
	
	// open prototype 
	public void openPrototype(Stage primaryStage) {
		try {
			
			// this code was written by Matthew Ian Wang, student #1222610662
			
			// initialize all the labels, text fields, and buttons on the first stage
			Label title = new Label("EffortLogger V2 Login Page");
			Label login = new Label("Login Credentials");
			Label usernamel = new Label("User ID: ");
			TextField usernamei = new TextField();
			Label passwordl = new Label("Password: ");
			PasswordField passwordi = new PasswordField();
			Button submit = new Button("Submit");
			Label invalid = new Label("If you haven't created an account yet, sign up below!");
			Button signup = new Button("Sign Up");
			usernamei.setPromptText("type here");
			passwordi.setPromptText("type here");
			
			// set the fonts to verdana and change the font sizes
			title.setFont(Font.font("verdana", FontWeight.NORMAL, FontPosture.REGULAR, 25));
			login.setFont(Font.font("verdana", FontWeight.NORMAL, FontPosture.REGULAR, 18));
			usernamel.setFont(Font.font("verdana", FontWeight.NORMAL, FontPosture.REGULAR, 15));
			usernamei.setFont(Font.font("verdana", FontWeight.NORMAL, FontPosture.REGULAR, 15));
			passwordl.setFont(Font.font("verdana", FontWeight.NORMAL, FontPosture.REGULAR, 15));
			passwordi.setFont(Font.font("verdana", FontWeight.NORMAL, FontPosture.REGULAR, 15));
			invalid.setFont(Font.font("verdana", FontWeight.NORMAL, FontPosture.REGULAR, 13));
			submit.setFont(Font.font("verdana", FontWeight.NORMAL, FontPosture.REGULAR, 15));
			signup.setFont(Font.font("verdana", FontWeight.NORMAL, FontPosture.REGULAR, 15));

			// use the setTranslateX and setTranslateY to adjust the positioning of all the buttons, labels, and text fields
			//login.setTranslateX(-125);
			title.setTranslateX(225);
			title.setTranslateY(25);
			usernamel.setTranslateX(100);
			passwordl.setTranslateX(100);
			usernamei.setTranslateX(-100);
			passwordi.setTranslateX(-100);
			submit.setTranslateX(230);
			signup.setTranslateX(230);
			invalid.setTranslateX(100);
			signup.setTranslateY(-10);
			
			// initialize the border pane and grid pane
			BorderPane root = new BorderPane();
			GridPane middle = new GridPane();
			
			// set the background color of the border pane to light grey
			root.setBackground(new Background(new BackgroundFill(Color.LIGHTGREY, CornerRadii.EMPTY, Insets.EMPTY)));
			
			// adjust the margins of the grid pane
			middle.setVgap(48);
			
			// add in the rows of the grid pane
			middle.addRow(1, login);
			middle.addRow(2, usernamel);
			middle.addRow(2, usernamei);
			middle.addRow(3, passwordl);
			middle.addRow(3, passwordi);
			middle.addRow(4, submit);
			middle.addRow(5, invalid);
			middle.addRow(6, signup);
			
			// adjust the text color where necessary
			usernamel.setTextFill(Color.RED);
			passwordl.setTextFill(Color.RED);
			submit.setTextFill(Color.GREEN);
			
			// adjust the alignment of every part
			submit.setAlignment(Pos.CENTER_RIGHT);
			middle.setAlignment(Pos.TOP_CENTER);
			signup.setAlignment(Pos.CENTER_RIGHT);
			root.setTop(title);
			root.setCenter(middle);
			
			// create the primary stage title
			primaryStage.setTitle("Login Page");
			
			// create a new scene
			Scene scene = new Scene(root,800,500);
			
			// set the scene with primary stage
			primaryStage.setScene(scene);
			
			// show the primary stage
			primaryStage.show();
			
			// first hard-coded credential
			ID userid = new ID();
			userid.setUser("adam");
			userid.setPass("bonnet");
			
			// second hard-coded credential
			ID userid2 = new ID();
			userid2.setUser("matt");
			userid2.setPass("wang");
			
			// third hard-coded credential
			ID userid3 = new ID();
			userid3.setUser("arjun");
			userid3.setPass("khetan");
			
			// fourth hard-coded credential
			ID userid4 = new ID();
			userid4.setUser("sawyer");
			userid4.setPass("kesti");
			
			// the array of credentials
			ID[] arr = new ID[]{userid, userid2, userid3, userid4};
			
			// when the button is clicked
			submit.setOnAction(event -> {
				
				// clear the invalid label 
				invalid.setText("");
				
				// save the username input and password input to string
				String username = usernamei.getText().toString();
				String password = passwordi.getText().toString();
				
				// if the username or password fall out of the range of acceptable number of characters
				if (username.length() > 32 || password.length() > 32) {
					
					// display an invalid message that instructs them to change it
					invalid.setText("Please enter an input that is 32 characters or less.");
					
				}
				
				// if something has been inputted in both the username and password fields
				else if (username.length() > 0 && password.length() > 0) {
					
					// initialize the boolean that indicates whether the username password combo is valid
					boolean found = false;
					
					// for all elements in the array
					for (int i = 0; i < 3; i++) {
						
						// if the username password combo is in this element of the array
						if (username.equals(arr[i].getUser()) && password.equals(arr[i].getPass())) {
							
							// update found to be true
							found = true;
							
						}
						
					}
					
					// if found is true, meaning the username and password are valid
					if (found == true) {
						
						// clear those fields
						usernamei.clear();
						passwordi.clear();
						
						// close the primary stage
						primaryStage.close();
						
						// open the second stage
						displayStage(primaryStage, username, password);
						
					}
					
					// if the username or password is not valid
					else {
						
						// display an invalid message that tells the user to try again
						invalid.setText("Invalid Username or Password! Please try again. ");
						
					}
					
				}
				
				
			});
			
			// when the button is clicked
			signup.setOnAction(event -> {
				
				// close the primaryStage
				primaryStage.close();
				
				// go to signUpStage
				signUpStage(primaryStage);
				
			});
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	// display stage function
	public void displayStage(Stage primaryStage, String username, String password) {
		
		// this code was written by Matthew Ian Wang, student #1222610662
		
		// initialize the second stage
		Stage secondStage = new Stage();
		
		// set the title of the second stage
		secondStage.setTitle("EffortLogger V2");
		
		// create the success label
		Label success = new Label("You have successfully logged in!");
		Button back = new Button("Log Out");
		
		// initialize the border pane and VBox
		BorderPane root = new BorderPane();
		GridPane grid = new GridPane();
		
		// set the background color of the border pane to light grey
		root.setBackground(new Background(new BackgroundFill(Color.LIGHTGREY, CornerRadii.EMPTY, Insets.EMPTY)));
		
		// set the font and font size of the label
		success.setFont(Font.font("verdana", FontWeight.NORMAL, FontPosture.REGULAR, 25));
		back.setFont(Font.font("verdana", FontWeight.NORMAL, FontPosture.REGULAR, 15));
		
		// add rows to the grid pane
		grid.addRow(0, success);
		grid.addRow(2, back);
		
		// set the alignment of the grid 
		root.setCenter(grid);
		grid.setAlignment(Pos.TOP_CENTER);
		
		// set the gap for the grid pane
		grid.setVgap(50);
		
		// move the grid and the log out button
		grid.setTranslateY(200);
		back.setTranslateX(150);	
		
		// create a new scene
		Scene scene = new Scene(root,800,500);
					
		// set the scene with primary stage
		secondStage.setScene(scene);
					
		// show the primary stage
		secondStage.show();
		
		// when the button is clicked
		back.setOnAction(event -> {
			
			// close the second stage
			secondStage.close();
			
			// go back to the primary stage
			openPrototype(primaryStage);
			
		});
		
	}
	
	// sign up stage function
	public void signUpStage(Stage primaryStage) {
		
		// this code was written by Matthew Ian Wang, student #1222610662
		
		// initialize the second stage
		Stage thirdStage = new Stage();
				
		// set the title of the second stage
		thirdStage.setTitle("Sign Up Page");
		
		// create the success label
		Label success = new Label("Here you make your account!");
		Button back = new Button("Return");
				
		// initialize the border pane and VBox
		BorderPane root = new BorderPane();
		GridPane grid = new GridPane();
				
		// set the background color of the border pane to light grey
		root.setBackground(new Background(new BackgroundFill(Color.LIGHTGREY, CornerRadii.EMPTY, Insets.EMPTY)));
				
		// set the font and font size of the label
		success.setFont(Font.font("verdana", FontWeight.NORMAL, FontPosture.REGULAR, 25));
		back.setFont(Font.font("verdana", FontWeight.NORMAL, FontPosture.REGULAR, 15));
				
		// add rows to the grid pane
		grid.addRow(0, success);
		grid.addRow(2, back);
				
		// set the alignment of the grid 
		root.setCenter(grid);
		grid.setAlignment(Pos.TOP_CENTER);
				
		// set the gap for the grid pane
		grid.setVgap(50);
				
		// move the grid and the log out button
		grid.setTranslateY(200);
		back.setTranslateX(150);	
				
		// create a new scene
		Scene scene = new Scene(root,800,500);
							
		// set the scene with primary stage
		thirdStage.setScene(scene);
							
		// show the primary stage
		thirdStage.show();
				
		// when the button is clicked
		back.setOnAction(event -> {
					
			// close the second stage
			thirdStage.close();
					
			// go back to the primary stage
			openPrototype(primaryStage);
					
		});
		
	}
	
}

