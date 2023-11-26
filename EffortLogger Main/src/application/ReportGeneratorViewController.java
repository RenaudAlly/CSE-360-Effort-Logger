package application;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ReportGeneratorViewController {
	@FXML
	private TextField employeeID;
	@FXML
	private Label employeeIDConfirmation;
	@FXML
	private Label defectLogsReportLabel;
	@FXML
	private Label effortLogsReportLabel;
	
	private String validUsername;
	// TODO: Hook this up with defect data array list provided
	private ArrayList<Defect> defectData = new ArrayList<Defect>(); // defect logs
	private ArrayList<Effort> effortData = new ArrayList<Effort>(); // effort logs
	
	public void captureLogin(Login previousLogin) {
		this.validUsername = previousLogin.getName();
	}
	
	public void enteredEmployeeID(ActionEvent event) throws IOException {
		// comparing text field content with initial user name
		if (validUsername.equals(employeeID.getText())) {
			employeeIDConfirmation.setText("You have entered the correct employee ID");
			employeeIDConfirmation.setTextFill(Color.BLACK);
		} else {
			employeeIDConfirmation.setText("You did not enter your ID correctly");
			employeeIDConfirmation.setTextFill(Color.RED);
		}
	}
	
	public void createDefectLogsReport(ActionEvent event) throws IOException {
		// TODO: Temporary mock data
		defectData.add(new Defect("Additional comments were added", "Documentation", "Effort Logger", "Information gathering", "Information gathering", "Closed", "Only clarifiaction"));
		defectData.add(new Defect("Delegating responsibilities", "Assignment", "Planning Poker", "Planning", "Outlining", "Open", "Some team members left"));
		
		// Converting defect array list to a string array list
		ArrayList<String[]> defectLogs = new ArrayList<String[]>();
		defectLogs.add(new String[]{"Defect Name", "Defect Category", "Project Name", "Inject Step", "Remove Step", "Status", "Defect Symptoms"});
		for (Defect defectLog : defectData) {
			defectLogs.add(defectLog.getArray());
		}
		
		// Creating the file
		File csvDefectFile = new File("Defect_Logs.csv");
		try (PrintWriter pw = new PrintWriter(csvDefectFile)){
			defectLogs.stream()
				.map(this::convertToCSV)
				.forEach(pw::println);
		}
		
		// Updates label for the defect log report generator
		defectLogsReportLabel.setText("Successfully generated CSV");
	}
	
	public void createEffortLogsReport(ActionEvent event) throws IOException {
		// TODO: Temporary mock data
		
		// Converting effort log array list to a string array list
		ArrayList<String[]> effortLogs = new ArrayList<String[]>();
		effortLogs.add(new String[]{"Effort Log Name", "Time Logged", "Project name", "Effort Category", "Plan"});
		for (Effort effortLog : effortData) {
			effortLogs.add(effortLog.getArray());
		}
		
		// Creating the file
		File csvEffortFile = new File("Effort_Logs.csv");
		try (PrintWriter pw = new PrintWriter(csvEffortFile)){
			effortLogs.stream()
				.map(this::convertToCSV)
				.forEach(pw::println);
		}
		
		// Updates label for the effort log report generator
		effortLogsReportLabel.setText("Successfully generated CSV");
	}
	
	// converts any string array to a CSV readable format
	public String convertToCSV(String[] data) {
		return Stream.of(data)
				.map(this::escapeSpecialCharacters)
				.collect(Collectors.joining(","));
	}
	
	// escapes all special characters in the string for data validation
	public String escapeSpecialCharacters(String data) {
	    String escapedData = data.replaceAll("\\R", " ");
	    if (data.contains(",") || data.contains("\"") || data.contains("'")) {
	        data = data.replace("\"", "\"\"");
	        escapedData = "\"" + data + "\"";
	    }
	    return escapedData;
	}
}
