package application;

import javafx.scene.Scene;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.*;
import javafx.scene.text.Font;
import javafx.scene.Node;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;

import java.io.IOException;
import java.util.ArrayList;

public class DefectViewController {
	
	private Stage stage;
	private Scene scene;
	
	// user who logged in
	private ID currentID = new ID();
	private boolean closed = false;
	private boolean edit = false;
	private boolean arrayexists = false;
	private ArrayList<Defect> array;
	private ArrayList<Effort> effort;
	private Defect defect;
	private int index;
	
	@FXML
	private Label defectLabel;
	@FXML
	private Label statusText;
	@FXML
	private TextField nameInput;
	@FXML
	private TextField descriptionInput;
	@FXML	
	private ComboBox<String> injectComboBox, categoryComboBox, removeComboBox, fixComboBox, defectComboBox, projectComboBox;

	public void DisplayDefectErrorStage() {
		Stage entryErrorStage = new Stage();
		entryErrorStage.setTitle("Error");
		
		Label entryErrorLabel = new Label();
		entryErrorLabel.setText("Choose a unique defect name");
		
		
		Scene unauthorizedScene = new Scene(entryErrorLabel, 180, 50);
		
		entryErrorLabel.setPadding(new Insets(10,0,0,10));
		entryErrorLabel.setFont(new Font("Arial", 12));
		
		entryErrorStage.setScene(unauthorizedScene);
		entryErrorStage.show();
	}
	
	public void SetUserDefectConsole(ID newID) {
		currentID = newID;
	}
	
	public void setArray(ArrayList<Defect> array) {
		
		this.array = array;
		arrayexists = true;
		
	}
	public void setEffort(ArrayList<Effort> array) {
		
		this.effort = array;
		
	}
	
	public void ProjectOnAction(ActionEvent event) throws IOException {
		if (arrayexists == false) {
			
			array = new ArrayList<Defect>();
			arrayexists = true;
			
		}

		ArrayList<String> relevant = new ArrayList<String>();
		if (projectComboBox.getValue() != null) {
			
			String projname = projectComboBox.getValue().toString();
			int size = array.size();
			
			for (int i = 0; i < size; i++) {
				
				if (array.get(i).getProject() != null) {
					
					if (array.get(i).getProject().toString().equals(projname)) {
						
						relevant.add(array.get(i).getName());
						
					}
					
				}
				
			}
			
		}
		
		ObservableList<String> list = FXCollections.observableArrayList(relevant);
		defectComboBox.setItems(list);
	
		defectLabel.setText(Integer.toString(relevant.size()) + " defects for this project.");
		
	}
	public void ClearOnAction(ActionEvent event) throws IOException {
		
		if (projectComboBox.getValue() != null) {
			
			String projname = projectComboBox.getValue().toString();
			int size = array.size();
			int removed = 0;
			
			for (int i = 0; i < size; i++) {
				
				if (i == size) {
					
					return;
					
				}
				
				if (array.get(i-removed).getProject() != null) {
					
					if (array.get(i-removed).getProject().toString().equals(projname)) {
						
						array.remove(i-removed);
						removed++;
						
					}
					
				}
				
			}
			
		}
		
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("DefectLog.fxml"));
		stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		scene = new Scene(fxmlLoader.load(), 750, 500);
		stage.setTitle("EffortLogger V2");
		
		
		DefectLogViewController control = fxmlLoader.getController();
		control.setEffort(effort);
		control.setArray(array);
		control.SetUserDefectLog(currentID);
		stage.setScene(scene);
		stage.show();
		
		
		
	}
	
	public void CreateOnAction(ActionEvent event) throws IOException {
		
		statusText.setText("Status: Open");
		edit = false;
		closed = false;
		nameInput.clear();
		descriptionInput.clear();
		injectComboBox.setValue(null);
		removeComboBox.setValue(null);
		categoryComboBox.setValue(null);
		fixComboBox.setValue(null);
		defectComboBox.setValue(null);
		
	}
	public void CloseOnAction(ActionEvent event) throws IOException {
		
		closed = true;
		statusText.setText("Status: Closed");
		
	}
	public void ReopenOnAction(ActionEvent event) throws IOException {
		
		closed = false;
		statusText.setText("Status: Open");
		
	} 
	public void UpdateOnAction(ActionEvent event) throws IOException {
		
		if (edit == false) {
			
			defect = new Defect();
			if (arrayexists == false) {
				
				array = new ArrayList<Defect>();
				arrayexists = true;
				
			}
				
			if (categoryComboBox.getValue() != null) {
				
				defect.setCategory(categoryComboBox.getValue().toString());
				
			}
			else {
				
				defect.setCategory("");
				
			}
			if (injectComboBox.getValue() != null) {
				
				defect.setInject(injectComboBox.getValue().toString());
			
			}
			else {
				
				defect.setInject("");
				
			}
			if (removeComboBox.getValue() != null) {
				
				defect.setRemove(removeComboBox.getValue().toString());
			
			}
			else {
				
				defect.setRemove("");
				
			}
			if (projectComboBox.getValue() != null) {
				
				defect.setProject(projectComboBox.getValue().toString());
			
			}
			else {
				
				defect.setProject("None");
				
			}

			defect.setName(nameInput.getText().toString());
			defect.setSymptoms(descriptionInput.getText().toString());
			
			if (closed == true) {
				
				defect.setStatus("Closed");
				
			}
			else {
				
				defect.setStatus("Open");
				
			}
			
			if (defect.getName().length() > 0) {
				
				boolean found = false;
				
				for (int i = 0; i < array.size(); i++) {
					
					if (defect.getName().equals(array.get(i).getName())) {
						
						found = true;
						
					}
					
				}
				
				if (found == false) {
					
					array.add(defect);
					
					FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("DefectLog.fxml"));
					stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
					scene = new Scene(fxmlLoader.load(), 750, 500);
					stage.setTitle("EffortLogger V2");
					
					
					DefectLogViewController control = fxmlLoader.getController();
					control.setEffort(effort);
					control.setArray(array);
					control.SetUserDefectLog(currentID);
					stage.setScene(scene);
					stage.show();
					
				}
				
				
			}
			
		}
		
		else if (edit == true) {
			
			if (nameInput.getText().toString().length() > 0) {
				
				boolean found = false;
				
				for (int i = 0; i < array.size(); i++) {
					
					if (nameInput.getText().toString().equals(array.get(i).getName())) {
						
						found = true;
						
					}
					
				}
				
				if (found == false) {
					
					if (categoryComboBox.getValue() != null) {
						
						array.get(index).setCategory(categoryComboBox.getValue().toString());
						
					}
					else {
						
						array.get(index).setCategory("");
						
					}
					if (injectComboBox.getValue() != null) {
						
						array.get(index).setInject(injectComboBox.getValue().toString());
					
					}
					else {
						
						array.get(index).setInject("");
						
					}
					if (removeComboBox.getValue() != null) {
						
						array.get(index).setRemove(removeComboBox.getValue().toString());
					
					}
					else {
						
						array.get(index).setRemove("");
						
					}
					if (projectComboBox.getValue() != null) {
						
						array.get(index).setProject(projectComboBox.getValue().toString());
					
					}
					else {
						
						array.get(index).setProject("None");
						
					}

					array.get(index).setName(nameInput.getText().toString());
					array.get(index).setSymptoms(descriptionInput.getText().toString());
					
					if (closed == true) {
						
						array.get(index).setStatus("Closed");
						
					}
					else {
						
						array.get(index).setStatus("Open");
						
					}
					
					FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("DefectLog.fxml"));
					stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
					scene = new Scene(fxmlLoader.load(), 750, 500);
					stage.setTitle("EffortLogger V2");
					
					
					DefectLogViewController control = fxmlLoader.getController();
					control.setArray(array);
					control.SetUserDefectLog(currentID);
					control.SetUserDefectLog(currentID);
					
					stage.setScene(scene);
					stage.show();
					
				}
				
				
			}
			
		}
		
		
	}

	public void DeleteOnAction(ActionEvent event) throws IOException {
		
		if (projectComboBox.getValue() == null) {
			
			return;
			
		}
		
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("DefectLog.fxml"));
		stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		scene = new Scene(fxmlLoader.load(), 750, 500);
		stage.setTitle("EffortLogger V2");
		
		
		DefectLogViewController control = fxmlLoader.getController();
		
		for (int i = 0; i < array.size(); i++) {
			
			if (defectComboBox.getValue().toString().equals(array.get(i).getName())) {
				
				array.remove(i);
				break;
				
			}
			
		}
		
		control.setEffort(effort);
		control.setArray(array);
		control.SetUserDefectLog(currentID);
		
		statusText.setText("Status: Closed");
		nameInput.clear();
		descriptionInput.clear();
		injectComboBox.setValue(null);
		removeComboBox.setValue(null);
		categoryComboBox.setValue(null);
		fixComboBox.setValue(null);
		
		stage.setScene(scene);
		stage.show();
		
	}
	public void ProceedOnAction(ActionEvent event) throws IOException {
		
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("EffortLoggerConsole.fxml"));
		stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		scene = new Scene(fxmlLoader.load(), 750, 500);
		stage.setTitle("EffortLogger V2");
		
		EffortLoggerConsoleController control = fxmlLoader.getController();
		control.setList(effort);
		control.setDefectList(array);
		control.SetUserEffortLoggerConsole(currentID);
		
		stage.setScene(scene);
		stage.show();
		
	}
	// load in the information from that defect
	public void DefectOnAction(ActionEvent event) throws IOException {
		
		if (defectComboBox.getValue() != null) {
			
			edit = true;
			String toedit = defectComboBox.getValue();
			
			for (int i = 0; i < array.size(); i++) {
				
				if(toedit.equals(array.get(i).getName())) {
					
					index = i;
					break;
					
				}
				
			}
			
		}
		
	}
}
