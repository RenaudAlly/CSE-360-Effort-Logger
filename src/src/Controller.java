package src;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.scene.Node;

import javafx.scene.control.TextField;

import javafx.event.ActionEvent;

public class Controller{
	
	
	//------------ Variables ------------------------------------------------------------------------------------------------------------------------------
	private Stage stage;
	private Scene scene;
	private Parent root;
	
	@FXML
	private Label hoursWorked, defectsInLogs, logsProduced, userIDLabel;
	
	@FXML
	private Button goBackToSelect, showDataButton;
	
	@FXML
	private TextField selectedUser;
	
	public void displayHours(String hours) {
		hoursWorked.setText("The total hours worked is: " + hours);
	}
	public void displayDefects(String defects) {
		defectsInLogs.setText("The number of defects is: " + defects);
	}
	public void displayLogsProduced(String logsCount) {
		logsProduced.setText("The total number of logs produced: " + logsCount);
	}
	public void displayUserID(String userID) {
		userIDLabel.setText("The userID is: " + userID);
	}
	
	//-------------------------------------------Switch Between Scenes---------------------------------------------------------------------------------
	public void ShowDataClicked(ActionEvent event) throws Exception{
		String input = selectedUser.getText();

		FXMLLoader loader = new FXMLLoader(getClass().getResource("Scene2.fxml"));
		root = loader.load();
		
		//root = FXMLLoader.load(getClass().getResource("DataPage.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		
		displayDefects(input);
		displayUserID(input);
		displayLogsProduced(input);
		displayHours(input);
		
		stage.show();		
		
	}
	
	public void ShowHomePage(ActionEvent event) throws Exception{
		root = FXMLLoader.load(getClass().getResource("SelectUserPage.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
		
	}
}
