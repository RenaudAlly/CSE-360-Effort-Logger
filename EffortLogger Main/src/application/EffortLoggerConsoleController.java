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
import javafx.scene.effect.Glow;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import java.time.*;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.control.TextField;



public class EffortLoggerConsoleController {
	@FXML
	private TextField effortTextField;
	@FXML
	private Button startActivityButton, stopActivityButton, defectLogConsoleButton, effortLogEditorButton, defectLogsButton, effortLogsButton;
	@FXML
	private Label clockLabel;
	@FXML
	private ComboBox<String> lifeCycleStepsComboBox, effortCategoryComboBox, planComboBox, projectComboBox;
	@FXML
	private Stage stage;
	@FXML
	private Scene scene; 
	@FXML
	private LocalTime startTime, stopTime;
	@FXML
	private LocalDate startDate;
	
	private String currentDate, currentYear, currentMonth, currentDay;
	@FXML
	private int starttime, stoptime, loggedtime, hoursLogged, minutesLogged, secondsLogged;
	
	private Effort e;
	
	ArrayList<Effort> effortList = new ArrayList<Effort>();
	ArrayList<Defect> defectList = new ArrayList<Defect>();
	
	//List<Project> list = new ArrayList<Project>();
	
	public void setList(ArrayList<Effort> newList) {
		this.effortList = newList;
	}
	
	public void setDefectList(ArrayList<Defect> newList) {
		
		this.defectList = newList;
		
	}
	
	
	public void StartActivity(ActionEvent event) throws IOException {
		clockLabel.setText("Clock has Started");
		clockLabel.setStyle("-fx-text-fill: green;");
		startTime = LocalTime.now();
		
	    starttime = (startTime.getHour() * 3600) + (startTime.getMinute() * 60) + startTime.getSecond();   
	}
	
	public void StopActivity(ActionEvent event) throws IOException {
		clockLabel.setText("Clock is Stopped");
		clockLabel.setStyle("-fx-text-fill: red;");
		
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
        stage.setScene(scene);
        stage.show();
	}
	
	public void ToEffortLogEditor(ActionEvent event) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("EffortLogEditor.fxml"));
        Parent root = loader.load();

        EffortLogEditorController controller = loader.getController();
        controller.setList(effortList);
        controller.setDefect(defectList);
        
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
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
	}
	
	public void ToEffortLog(ActionEvent event) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("ProjectLog.fxml"));
        Parent root = loader.load();
        
        ProjectLogController controller = loader.getController();
        controller.setList(effortList);
        controller.setDefect(defectList);

        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
	}
}

