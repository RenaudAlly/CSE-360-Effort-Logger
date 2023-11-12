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

	public void setLogin(Login login) {
		username.setText("Name: " + login.getName());
		password.setText("Password: " + login.getPassword());
		passwordLength.setText("Length: " + login.getPasswordLength());
	}
	
	public void goToReportGeneratorView(ActionEvent event) throws IOException {	
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ReportGeneratorPage.fxml"));
		
		stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		scene = new Scene(fxmlLoader.load());
		stage.setTitle("Report Generator");
		
		stage.setScene(scene);
		stage.show();
	}
}
