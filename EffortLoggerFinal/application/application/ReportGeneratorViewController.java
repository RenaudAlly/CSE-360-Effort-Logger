package application;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ReportGeneratorViewController {
	@FXML
	private TextField employeeID;
	@FXML
	private Label employeeIDConfirmation, effortLogsReportLabel, defectLogsReportLabel;
	@FXML
	private Stage stage;
	@FXML
	private Scene scene;
	
	private String validUsername;

	// TODO: Hook this up with defect data array list provided
	private ID currentID = new ID();
	private ArrayList<Effort> effortList = new ArrayList<Effort>();
	private ArrayList<Defect> defectList = new ArrayList<Defect>();
	
	public void captureLogin(Login previousLogin) {
		this.validUsername = previousLogin.getName();
	}
	public void SetUserReportGenerator(ID newID) {
		currentID = newID;
	}
	
	public void SetEffortList(ArrayList<Effort> newList) {
		effortList = newList;
	}
	
	public void SetDefectList(ArrayList<Defect> newList) {
		defectList = newList;
	}	
	
	public void EnteredEmployeeID(ActionEvent event) throws IOException {
		// comparing text field content with initial user name
		if (validUsername.equals(employeeID.getText())) {
			employeeIDConfirmation.setText("You have entered the correct employee ID");
			employeeIDConfirmation.setTextFill(Color.BLACK);
		} else {
			employeeIDConfirmation.setText("You did not enter your ID correctly");
			employeeIDConfirmation.setTextFill(Color.RED);
		}
	}
	
	public void CreateDefectLogsReport(ActionEvent event) throws IOException {
		// Converting defect array list to a string array list
		ArrayList<String[]> defectLogs = new ArrayList<String[]>();
		defectLogs.add(new String[]{"Defect Name", "Defect Category", "Inject Step", "Remove Step", "Status", "Defect Symptoms", "Project Name"});
		for (Defect defectLog : defectList) {
			defectLogs.add(defectLog.getArray());
		}
		
		// Creating the file
		File csvDefectFile = new File("Defect_Logs.csv");
		try (PrintWriter pw = new PrintWriter(csvDefectFile)){
			defectLogs.stream()
				.map(this::ConvertToCSV)
				.forEach(pw::println);
		}
		
		// Updates label for the defect log report generator
		defectLogsReportLabel.setText("Successfully generated CSV");
	}

	public void ExitReportGenerator(ActionEvent event) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("EffortLoggerConsole.fxml"));
        Parent root = loader.load();

        EffortLoggerConsoleController controller = loader.getController();
        controller.setList(effortList);
        controller.setDefectList(defectList);
        controller.SetUserEffortLoggerConsole(currentID);
        
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
	}
	public void CreateEffortLogsReport(ActionEvent event) throws IOException {		
		// Converting effort log array list to a string array list
		ArrayList<String[]> effortLogs = new ArrayList<String[]>();
		effortLogs.add(new String[]{"Effort Name", "Time Logged", "Project Name", "Life Cycle", "Effort Category", "Plan", "Start Time", "Stop Time"});
		for (Effort effortLog : effortList) {
			effortLogs.add(effortLog.getArray());
		}
		
		// Creating the file
		File csvEffortFile = new File("Effort_Logs.csv");
		try (PrintWriter pw = new PrintWriter(csvEffortFile)){
			effortLogs.stream()
				.map(this::ConvertToCSV)
				.forEach(pw::println);
		}
		
		// Updates label for the effort log report generator
		effortLogsReportLabel.setText("Successfully generated CSV");
	}
	
	// converts any string array to a CSV readable format
	public String ConvertToCSV(String[] data) {
		return Stream.of(data)
				.map(this::EscapeSpecialCharacters)
				.collect(Collectors.joining(","));
	}
	
	// escapes all special characters in the string for data validation
	public String EscapeSpecialCharacters(String data) {
	    String escapedData = data.replaceAll("\\R", " ");
	    if (data.contains(",") || data.contains("\"") || data.contains("'")) {
	        data = data.replace("\"", "\"\"");
	        escapedData = "\"" + data + "\"";
	    }
	    return escapedData;
	}
}
