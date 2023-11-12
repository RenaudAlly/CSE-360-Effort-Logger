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
	private Login login = new Login();

	@FXML
	private TextField usernameField;
	@FXML
	private PasswordField passwordField;

	public void submitLogin(ActionEvent event) throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("DisplayPage.fxml"));
		login.setName(usernameField.getText());
		login.setPassword(passwordField.getText());

		stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		scene = new Scene(fxmlLoader.load());
		stage.setTitle("Login Info");

		DisplayViewController control = fxmlLoader.getController();
		control.setLogin(login);
		stage.setScene(scene);
		stage.show();
	}
}
