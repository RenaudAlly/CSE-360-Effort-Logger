package application;

import java.io.IOException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import java.time.*;
import java.util.ArrayList;
import javafx.scene.control.TextField;



public class EffortLoggerConsoleController {
	@FXML
	private TextField effortTextField;
	@FXML
	private Button startActivityButton, stopActivityButton, 
					defectLogConsoleButton, effortLogEditorButton, 
					defectLogsButton, effortLogsButton, 
					toLoginScreenButton, generateReportButton;
	@FXML
	private Label clockLabel;
	@FXML
	private ComboBox<String> lifeCycleStepsComboBox, effortCategoryComboBox, planComboBox, projectComboBox; 
	@FXML
	private LocalTime startTime, stopTime;
	@FXML
	private LocalDate startDate;
	@FXML
	private int starttime, stoptime, loggedtime, hoursLogged, minutesLogged, secondsLogged;
	
	private Scene scene;
	private Stage stage;
	private String currentDate, currentYear, currentMonth, currentDay;
	private Effort e;
	private ID currentID = new ID();
	private ArrayList<Effort> effortList = new ArrayList<Effort>();
	private ArrayList<Defect> defectList = new ArrayList<Defect>();
	private boolean timerStartedBool = false;
	
	@SuppressWarnings("serial")
	private ArrayList<String> plansList = new ArrayList<String>() {
		{
		add("Project Plan");
		add("Risk Mitigation Plan");
		add("Conceptual Design Plan");
		add("Detailed Design Plan");
		add("Implementation Plan");
		}
	};
	
	@SuppressWarnings("serial")
	private ArrayList<String> deliverablesList = new ArrayList<String>() {
		{
		add("Conceptual Design");
		add("Detailed Design");
		add("Test Cases");
		add("Solution");
		add("Reflection");
		add("Outline");
		add("Draft");
		add("Report");
		add("User Defined");
		}
	};
	
	private ObservableList<String> plansOList = FXCollections.observableArrayList(plansList);
	private ObservableList<String> deliverablesOList = FXCollections.observableArrayList(deliverablesList);
	
	public void SetUserEffortLoggerConsole(ID newID) {
		currentID = newID;
	}
	
	public void setList(ArrayList<Effort> newList) {
		effortList = newList;
	}
	
	public void setDefectList(ArrayList<Defect> newList) {
		
		defectList = newList;
		
	}
	
	
	
	public void SetEffortCategory(ActionEvent event) throws IOException {
		String currentEffortCategory = effortCategoryComboBox.getValue();
		if (currentEffortCategory.equals("Plans")) {
			planComboBox.setItems(plansOList);
		}
		if (currentEffortCategory.equals("Deliverables")) {
			planComboBox.setItems(deliverablesOList);
		}
	}
	
	public void DisplayUnauthorizedStage() {
		Stage unauthorizedStage = new Stage();
		unauthorizedStage.setTitle("Error");
		
		Label unauthorizedLabel = new Label();
		unauthorizedLabel.setText("You are not authorized to use this feature");
		
		
		Scene unauthorizedScene = new Scene(unauthorizedLabel, 245, 50);
		
		unauthorizedLabel.setPadding(new Insets(10,0,0,10));
		unauthorizedLabel.setFont(new Font("Arial", 12));
		
		unauthorizedStage.setScene(unauthorizedScene);
		unauthorizedStage.show();
	}
	
	public void DisplayEntryErrorStage() {
		Stage entryErrorStage = new Stage();
		entryErrorStage.setTitle("Error");
		
		Label entryErrorLabel = new Label();
		entryErrorLabel.setText("Choose a unique entry name");
		
		
		Scene unauthorizedScene = new Scene(entryErrorLabel, 180, 50);
		
		entryErrorLabel.setPadding(new Insets(10,0,0,10));
		entryErrorLabel.setFont(new Font("Arial", 12));
		
		entryErrorStage.setScene(unauthorizedScene);
		entryErrorStage.show();
	}
	
	public void StartActivity(ActionEvent event) throws IOException {
		
		timerStartedBool = true;
		clockLabel.setText("Clock has Started");
		clockLabel.setStyle("-fx-text-fill: green;");
		startTime = LocalTime.now();
		
	    starttime = (startTime.getHour() * 3600) + (startTime.getMinute() * 60) + startTime.getSecond();   
	}
	
	public void StopActivity(ActionEvent event) throws IOException {
		if(!timerStartedBool) {
			
			return;
		}
		clockLabel.setText("Clock is Stopped");
		clockLabel.setStyle("-fx-text-fill: red;");
		int size = effortList.size();
		
		if (size >= 1) {
			for (int i = 0; i < size; i++) {
				if (effortList.get(i).getEffortName().equals(effortTextField.getText().toString())) {
					DisplayEntryErrorStage();
					return;
				}
			}
		}
		
		e = new Effort();
		
		stopTime = LocalTime.now();
		stoptime = (stopTime.getHour() * 3600) + (stopTime.getMinute() * 60) + stopTime.getSecond();
	
		loggedtime = stoptime - starttime;
		
		hoursLogged = loggedtime / 3600;
		loggedtime = loggedtime % 3600;
		minutesLogged = loggedtime / 60;
		loggedtime = loggedtime % 60;
		secondsLogged = loggedtime;
		
		
		
		startDate = LocalDate.now();
		
		currentDay = Integer.toString(startDate.getDayOfMonth());
		currentMonth = Integer.toString(startDate.getMonth().getValue());
		currentYear = Integer.toString(startDate.getYear());
		
		currentDate = currentYear + "-" + currentMonth + "-" + currentDay;
		
		e.setStopTime(stopTime.getHour() + ":" + stopTime.getMinute() + ":" + stopTime.getSecond());
		e.setStartTime(startTime.getHour() + ":" + startTime.getMinute() + ":" + startTime.getSecond());
		e.setTimeLogged(currentDate);
		e.setEffortCategory(effortCategoryComboBox.getValue());
		e.setLifeCycle(lifeCycleStepsComboBox.getValue());
		e.setPlan(planComboBox.getValue());
		e.setProjectName(projectComboBox.getValue());
		e.setEffortName(effortTextField.getText());
		
		effortList.add(e);
	}
	
	public void ToDefectLogConsole(ActionEvent event) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("DefectConsole.fxml"));
        Parent root = loader.load();

        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        
        DefectViewController controller = loader.getController();
        controller.setEffort(effortList);
        controller.setArray(defectList);
        controller.SetUserDefectConsole(currentID);
        
        stage.setScene(scene);
        stage.show();
	}
	
	public void ToEffortLogEditor(ActionEvent event) throws IOException {
		if (currentID.getLevel() != 2) {
			DisplayUnauthorizedStage();
			return;
		}
		
		FXMLLoader loader = new FXMLLoader(getClass().getResource("EffortLogEditor.fxml"));
        Parent root = loader.load();

        EffortLogEditorController controller = loader.getController();
        controller.setList(effortList);
        controller.setDefect(defectList);
        controller.SetUserEffortLogEditor(currentID);
        
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
		}
	
	public void GenerateReports(ActionEvent event) throws IOException{
		if (currentID.getLevel() != 2) {
			DisplayUnauthorizedStage();
			return;
		}
		
		FXMLLoader loader = new FXMLLoader(getClass().getResource("ReportGeneratorPage.fxml"));
        Parent root = loader.load();
        
        ReportGeneratorViewController controller = loader.getController();
        controller.SetEffortList(effortList);
        controller.SetDefectList(defectList);
        controller.SetUserReportGenerator(currentID);
        
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
	}

	public void ToDefectLogs(ActionEvent event) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("DefectLog.fxml"));
        Parent root = loader.load();
        DefectLogViewController controller = loader.getController();
        controller.setEffort(effortList);
        controller.setArray(defectList);
        controller.SetUserDefectLog(currentID);
        
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
	}

	public void ToLoginScreen(ActionEvent event) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("LoginScreen.fxml"));
		Parent root = loader.load();
		
		LoginViewController controller = loader.getController();
		controller.SetUserLoginScreen(currentID);
		controller.SetEffortList(effortList);
		controller.SetDefectList(defectList);
		
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
	} 
	
	public void ToEffortLog(ActionEvent event) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("EffortLog.fxml"));
        Parent root = loader.load();
        
        EffortLogController controller = loader.getController();
        controller.setList(effortList);
        controller.setDefect(defectList);
        controller.setUserEffortLog(currentID);

        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
	}
	
}

