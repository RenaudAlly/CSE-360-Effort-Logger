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
	
	private ArrayList<Defect> defectList;
	private ArrayList<Effort> effortList;
	private ID currentID = new ID();
	
	@FXML
	private TableColumn<Defect, String> defectNameColumn, defectCategoryColumn, projectColumn, injectStepColumn, removeStepColumn, statusColumn, defectSymptomsColumn;
	@FXML
	private TableView<Defect> defectLogTable;
	
	public void SetUserDefectLog(ID newID) {
		currentID = newID;
	}
	
	public void setEffortList(ArrayList<Effort> effort) {
		
		this.effortList = effort;
		
	}

	public void setDefectList(ArrayList<Defect> array) {
		
		this.defectList = array;
		
		if (this.defectList.isEmpty()) {
			
			return;
			
		}
		else {
		
			defectNameColumn.setCellValueFactory(new PropertyValueFactory<Defect, String>("name"));
			defectCategoryColumn.setCellValueFactory(new PropertyValueFactory<Defect, String>("category"));
			injectStepColumn.setCellValueFactory(new PropertyValueFactory<Defect, String>("inject"));
			removeStepColumn.setCellValueFactory(new PropertyValueFactory<Defect, String>("remove"));
			statusColumn.setCellValueFactory(new PropertyValueFactory<Defect, String>("status"));
			defectSymptomsColumn.setCellValueFactory(new PropertyValueFactory<Defect, String>("symptoms"));
			projectColumn.setCellValueFactory(new PropertyValueFactory<Defect, String>("project"));

			ObservableList<Defect> list = FXCollections.observableArrayList(this.defectList);
			defectLogTable.setItems(list);
			
		}
		
	}
	
	
	public void ReturnOnAction(ActionEvent event) throws IOException {
		
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("DefectConsole.fxml"));
		stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		scene = new Scene(fxmlLoader.load(), 750, 500);
		stage.setTitle("EffortLogger V2");
		
		DefectViewController control = fxmlLoader.getController();
		control.SetUserDefectConsole(currentID);
		control.setEffortList(effortList);
		control.setDefectList(defectList);
		stage.setScene(scene);
		stage.show();
		
	}
}
