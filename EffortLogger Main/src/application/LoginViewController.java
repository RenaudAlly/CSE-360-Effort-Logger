package application;

import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.Node;
import javafx.stage.Stage;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import java.io.IOException;



public class LoginViewController {
	private Scene scene;
	private Stage stage;

	@FXML
	private TextField UsernameInput;
	@FXML
	private PasswordField PasswordInput;
	@FXML
	private Label SignUpText;
	@FXML
	private ComboBox<String> ComboBoxInput; 
	
	public void SignUpButtonOnAction(ActionEvent event) throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("SignUpScreen.fxml"));
		stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		scene = new Scene(fxmlLoader.load(), 750, 500);
		stage.setTitle("Sign Up Page");
		
		SignUpViewController control = fxmlLoader.getController();
		stage.setScene(scene);
		stage.show();
		
	
	}
	
	public void LogInButtonOnAction(ActionEvent event) throws IOException {
		
		// first hard-coded credential
		ID userid = new ID();
		userid.setUser("adam");
		userid.setPass("bonnet");
		userid.setLevel(2);
					
		// second hard-coded credential
		ID userid2 = new ID();
		userid2.setUser("matt");
		userid2.setPass("wang");
		userid2.setLevel(2);
					
		// third hard-coded credential
		ID userid3 = new ID();
		userid3.setUser("arjun");
		userid3.setPass("khetan");
		userid3.setLevel(1);
		
		// third hard-coded credential
		ID userid4 = new ID();
		userid4.setUser("sawyer");
		userid4.setPass("kesti");
		userid4.setLevel(2);
		
		// third hard-coded credential
		ID userid5 = new ID();
		userid5.setUser("meshach");
		userid5.setPass("samuel");
		userid5.setLevel(0);
		
		ID useridfinal = new ID();
				
		// the array of credentials
		ID[] arr = new ID[]{userid, userid2, userid3, userid4, userid5};
		// save the username input and password input to string
		String username = UsernameInput.getText().toString();
		String password = PasswordInput.getText().toString();
		
		// if the username or password fall out of the range of acceptable number of characters
		if (username.length() > 32 || password.length() > 32) {
			
			// display an invalid message that instructs them to change it
			SignUpText.setText("Please enter an input that is 32 characters or less.");
			
		}
		
		// if something has been inputted in both the username and password fields
		else if (username.length() > 0 && password.length() > 0) {
			
			// initialize the boolean that indicates whether the username password combo is valid
			boolean found = false;
			
			boolean perm = false;
			
			// for all elements in the array
			for (int i = 0; i < 5; i++) {
				
				// if the username password combo is in this element of the array
				if (username.equals(arr[i].getUser()) && password.equals(arr[i].getPass())) {
					
					if (arr[i].getLevel() != 0) {
						
						// update found to be true
						found = true;
						useridfinal = arr[i];
						break;
						
					}
					else {
						
						SignUpText.setText("Your account does not have the permissions.");
						perm = true;
						break;
						
					}
					
				}
				
			}
			
			
			
			// if found is true, meaning the username and password are valid
			if (found == true) {
				
				String go = ComboBoxInput.getValue().toString();
				
				if (go.equals("Effort Logger")) {
					
					FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("EffortLoggerConsole.fxml"));
					stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
					scene = new Scene(fxmlLoader.load(), 750, 500);
					stage.setTitle("EffortLogger V2");
					
					EffortLoggerConsoleController control = fxmlLoader.getController();
					//control.setUser(useridfinal);
					stage.setScene(scene);
					stage.show();
					
				}
				else if(go.equals("Planning Poker")){
					FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("PlanningPoker.fxml"));
					stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
					scene = new Scene(fxmlLoader.load(), 750, 500);
					stage.setTitle("EffortLogger - Planning Poker Session");
					
					PlanningPokerController control = fxmlLoader.getController();
					control.SetUserPlanningPoker(useridfinal);
					stage.setScene(scene);
					stage.show();
					
					
				}
				
				
			}
			
			// if the username or password is not valid
			else {
				
				if (perm == false) {
					
					// display an invalid message that tells the user to try again
					SignUpText.setText("Invalid Username or Password! Please try again. ");
					
				}
				
			}
		
		
		}
	
	/*
	FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("DisplayPage.fxml"));
	login.setName(usernameField.getText());
	login.setPassword(passwordField.getText());

	stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
	scene = new Scene(fxmlLoader.load(), 900, 600);
	stage.setTitle("Login Info");

	DisplayViewController control = fxmlLoader.getController();
	control.setLogin(login);
	stage.setScene(scene);
	stage.show()
	*/
	}
	
}
