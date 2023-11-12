package application;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.*;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class DisplayViewController {
	@FXML
	private Label username;
	@FXML
	private Label password;
	@FXML
	private Label passwordLength;
	
	private Scene scene;
	private Stage stage;
	private Login previousLogin;

	public void setLogin(Login previousLogin) {
		// storing login session details locally
		this.previousLogin = previousLogin;
		// displaying login details
		username.setText("Name: " + previousLogin.getName());
		password.setText("Password: " + previousLogin.getPassword());
		passwordLength.setText("Length: " + previousLogin.getPasswordLength());
	}
	
	public void goToReportGeneratorView(ActionEvent event) throws IOException {	
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ReportGeneratorPage.fxml"));
		
		// Preparing stage
		stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		scene = new Scene(fxmlLoader.load());
		stage.setTitle("Report Generator");		
		
		// Setting up logic for the stage
		ReportGeneratorViewController control = fxmlLoader.getController();
		// passing login details to report generator page
		control.captureLogin(previousLogin);
		
		// Setting and showing stage
		stage.setScene(scene);
		stage.show();
	}
}
