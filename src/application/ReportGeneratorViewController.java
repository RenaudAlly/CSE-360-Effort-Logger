package application;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

public class ReportGeneratorViewController {
	@FXML
	private TextField employeeID;
	@FXML
	private Label employeeIDConfirmation;
	@FXML
	private Label reportGenerationText;
	@FXML
	private Label reportSubmissionText;
	
	private String validUsername;
	
	public void captureLogin(Login previousLogin) {
		this.validUsername = previousLogin.getName();
	}
	
	public void enteredEmployeeID(ActionEvent event) throws IOException {
		// comparing text field content with initial user name
		if (validUsername.equals(employeeID.getText())) {
			employeeIDConfirmation.setText("You have entered the correct employee ID");
			employeeIDConfirmation.setTextFill(Color.BLACK);
		}
	}
	
	public void createReport(ActionEvent event) throws IOException {
		reportGenerationText.setText("Successfully generated CSV");
	}
	
	public void submitReport(ActionEvent event) throws IOException {
		reportSubmissionText.setText("Succesfully submitted CSV");
	}
	
}
