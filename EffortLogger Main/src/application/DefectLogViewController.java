package application;

import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.Node;
import javafx.stage.Stage;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import java.io.IOException;
import java.util.ArrayList;

public class DefectLogViewController {
	private Stage stage;
	private Scene scene;
	
	@FXML
	private Button ReturnButton;
	
	private ArrayList<Defect> array;
	private ArrayList<Effort> effort;
	private ID currentID = new ID();
	
	@FXML
	private TableColumn<Defect, String> DefectName, DefectCategory, InjectStep, RemoveStep, Status, DefectSymptoms, Project;
	@FXML
	private TableView<Defect> DefectLog;
	
	public void SetUserDefectLog(ID newID) {
		currentID = newID;
	}
	
	public void setArray(ArrayList<Defect> array) {
		
		this.array = array;
		
		if (this.array.isEmpty()) {
			
			return;
			
		}
		else {
		
			DefectName.setCellValueFactory(new PropertyValueFactory<Defect, String>("name"));
			DefectCategory.setCellValueFactory(new PropertyValueFactory<Defect, String>("category"));
			InjectStep.setCellValueFactory(new PropertyValueFactory<Defect, String>("inject"));
			RemoveStep.setCellValueFactory(new PropertyValueFactory<Defect, String>("remove"));
			Status.setCellValueFactory(new PropertyValueFactory<Defect, String>("status"));
			DefectSymptoms.setCellValueFactory(new PropertyValueFactory<Defect, String>("symptoms"));
			Project.setCellValueFactory(new PropertyValueFactory<Defect, String>("project"));
			ObservableList<Defect> list = FXCollections.observableArrayList(this.array);
			DefectLog.setItems(list);
			
		}
		
	}
	
	public void setEffort(ArrayList<Effort> effort) {
		
		this.effort = effort;
		
	}
	
	public void ReturnOnAction(ActionEvent event) throws IOException {
		
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("DefectConsole.fxml"));
		stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		scene = new Scene(fxmlLoader.load(), 750, 500);
		stage.setTitle("EffortLogger V2");
		
		DefectViewController control = fxmlLoader.getController();
		control.SetUserDefectConsole(currentID);
		control.setEffort(effort);
		control.setArray(array);
		stage.setScene(scene);
		stage.show();
		
	}
}
