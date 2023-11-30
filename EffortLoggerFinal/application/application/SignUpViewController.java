package application;

import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.Node;
import javafx.stage.Stage;

import javafx.event.ActionEvent;
import javafx.fxml.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.time.DayOfWeek;


public class SignUpViewController {
	private Scene scene;
	private Stage stage;

	@FXML
	private TextField usernameInput;
	@FXML
	private PasswordField passwordInput;
	@FXML
	private ComboBox<String> comboBoxInput;
	@FXML
	private Button createAccountButton;

	public void CreateAccountButtonOnAction(ActionEvent event) throws IOException {
		
		ID newuser = new ID();
		
		String username = usernameInput.getText().toString();
		String password = passwordInput.getText().toString();
		
		if (username.length() > 0 && password.length() > 0 && username.length() < 33 && password.length() < 33) {
			newuser.setUser(username);
			newuser.setPass(password);
		
			String level = comboBoxInput.getValue().toString();
		
			if (level.equals("Guest")) {
				
				newuser.setLevel(0);
				
			}
			else if (level.equals("Developer")) {
				
				newuser.setLevel(1);
				
			}
			else if (level.equals("First Level Supervisor")) {
				newuser.setLevel(2);
				
			}
			else {

				newuser.setLevel(0);
				
			}
			
			// Adds created user to a CSV file which contains all the valid users
			addUser(newuser);
			
		}
		
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("LoginScreen.fxml"));
		stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		scene = new Scene(fxmlLoader.load(), 750, 500);
		stage.setTitle("Login Page");
		
//		LoginViewController control = fxmlLoader.getController();
		stage.setScene(scene);
		stage.show();
		
	}

	public void addUser(ID newUser) {
		// Using FileWriter for the append functionality offered when calling write() method
		try {
			File csvUsersListFile = new File("Users_List.csv");
			FileWriter fw = new FileWriter(csvUsersListFile, true);
			fw.write(newUser.toString() + "\n");
			fw.close();
		}
		catch(IOException e) {
			System.out.println("Unexpeced error occurred while writing to users list.");
		}
	}
}
