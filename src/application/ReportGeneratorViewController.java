package application;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class ReportGeneratorViewController {
	@FXML
	private TextField employeeID;
	@FXML
	private Label employeeIDConfirmation;
	@FXML
	private Label reportGenerationText;
	@FXML
	private Label reportSubmissionText;
	
	public void enteredEmployeeID(ActionEvent event) throws IOException {
		employeeIDConfirmation.setText("You have entered the correct employee ID");
	}
	
	public void createReport(ActionEvent event) throws IOException {
		reportGenerationText.setText("Successfully generated PDF");
	}
	
	public void submitReport(ActionEvent event) throws IOException {
		reportSubmissionText.setText("Succesfully submitted PDF");
	}
	
}
