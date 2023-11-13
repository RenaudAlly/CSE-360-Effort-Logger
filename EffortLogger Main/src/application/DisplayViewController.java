package application;

import javafx.fxml.*;
import javafx.scene.control.*;

public class DisplayViewController {

	@FXML
	private Label username;
	@FXML
	private Label password;
	@FXML
	private Label passwordLength;

	public void setLogin(Login login) {

		username.setText("Name: " + login.getName());
		password.setText("Password: " + login.getPassword());
		passwordLength.setText("Length: " + login.getPasswordLength());

	}

}
