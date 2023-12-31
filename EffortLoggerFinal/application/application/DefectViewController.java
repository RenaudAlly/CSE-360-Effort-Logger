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
	private boolean listExists = false;
	private ArrayList<Defect> defectList;
	private ArrayList<Effort> effortList;
	private Defect defect;
	private int index;
	
	@FXML
	private Label defectLabel;
	@FXML
	private Label statusLabel;
	@FXML
	private TextField nameTextField;
	@FXML
	private TextField descriptionTextField;
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
	
	public void setDefectList(ArrayList<Defect> newDefect) {
		
		this.defectList = newDefect;
		listExists = true;
		
	}
	public void setEffortList(ArrayList<Effort> newEffort) {
		
		this.effortList = newEffort;
		
	}
	
	public void ProjectOnAction(ActionEvent event) throws IOException {
		if (listExists == false) {
			
			defectList = new ArrayList<Defect>();
			listExists = true;
			
		}

		ArrayList<String> relevantDefectsList = new ArrayList<String>();
		if (projectComboBox.getValue() != null) {
			
			String projectName = projectComboBox.getValue().toString();
			int size = defectList.size();
			
			for (int i = 0; i < size; i++) {
				
				if (defectList.get(i).getProject() != null) {
					
					if (defectList.get(i).getProject().toString().equals(projectName)) {
						
						relevantDefectsList.add(defectList.get(i).getName());
						
					}
					
				}
				
			}
			
		}
		
		ObservableList<String> list = FXCollections.observableArrayList(relevantDefectsList);
		defectComboBox.setItems(list);
	
		defectLabel.setText(Integer.toString(relevantDefectsList.size()) + " defects for this project.");
		
	}
	public void ClearOnAction(ActionEvent event) throws IOException {
		
		if (projectComboBox.getValue() != null) {
			
			String projname = projectComboBox.getValue().toString();
			int size = defectList.size();
			int removed = 0;
			
			for (int i = 0; i < size; i++) {
				
				if (i == size) {
					
					return;
					
				}
				
				if (defectList.get(i-removed).getProject() != null) {
					
					if (defectList.get(i-removed).getProject().toString().equals(projname)) {
						
						defectList.remove(i-removed);
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
		control.setEffortList(effortList);
		control.setDefectList(defectList);
		control.SetUserDefectLog(currentID);
		stage.setScene(scene);
		stage.show();
		
		
		
	}
	
	public void CreateOnAction(ActionEvent event) throws IOException {
		
		statusLabel.setText("Status: Open");
		edit = false;
		closed = false;
		nameTextField.clear();
		descriptionTextField.clear();
		injectComboBox.setValue(null);
		removeComboBox.setValue(null);
		categoryComboBox.setValue(null);
		fixComboBox.setValue(null);
		defectComboBox.setValue(null);
		
	}
	public void CloseOnAction(ActionEvent event) throws IOException {
		
		closed = true;
		statusLabel.setText("Status: Closed");
		
	}
	public void ReopenOnAction(ActionEvent event) throws IOException {
		
		closed = false;
		statusLabel.setText("Status: Open");
		
	} 
	public void UpdateOnAction(ActionEvent event) throws IOException {
		
		if (edit == false) {
			
			defect = new Defect();
			if (listExists == false) {
				
				defectList = new ArrayList<Defect>();
				listExists = true;
				
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

			defect.setName(nameTextField.getText().toString());
			defect.setSymptoms(descriptionTextField.getText());
			
			if (closed == true) {
				
				defect.setStatus("Closed");
				
			}
			else {
				
				defect.setStatus("Open");
				
			}
			
			if (defect.getName().length() > 0) {
				
				boolean found = false;
				
				for (int i = 0; i < defectList.size(); i++) {
					
					if (defect.getName().equals(defectList.get(i).getName())) {
						
						found = true;
						
					}
					
				}
				
				if (found == false) {
					
					defectList.add(defect);
					
					FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("DefectLog.fxml"));
					stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
					scene = new Scene(fxmlLoader.load(), 750, 500);
					stage.setTitle("EffortLogger V2");
					
					
					DefectLogViewController control = fxmlLoader.getController();
					control.setEffortList(effortList);
					control.setDefectList(defectList);
					control.SetUserDefectLog(currentID);
					stage.setScene(scene);
					stage.show();
					
				}
				
				
			}
			
		}
		
		else if (edit == true) {
			
			if (nameTextField.getText().toString().length() > 0) {
				
				boolean found = false;
				
				for (int i = 0; i < defectList.size(); i++) {
					
					if (nameTextField.getText().toString().equals(defectList.get(i).getName())) {
						
						found = true;
						
					}
					
				}
				
				if (found == false) {
					
					if (categoryComboBox.getValue() != null) {
						
						defectList.get(index).setCategory(categoryComboBox.getValue().toString());
						
					}
					else {
						
						defectList.get(index).setCategory("");
						
					}
					if (injectComboBox.getValue() != null) {
						
						defectList.get(index).setInject(injectComboBox.getValue().toString());
					
					}
					else {
						
						defectList.get(index).setInject("");
						
					}
					if (removeComboBox.getValue() != null) {
						
						defectList.get(index).setRemove(removeComboBox.getValue().toString());
					
					}
					else {
						
						defectList.get(index).setRemove("");
						
					}
					if (projectComboBox.getValue() != null) {
						
						defectList.get(index).setProject(projectComboBox.getValue().toString());
					
					}
					else {
						
						defectList.get(index).setProject("None");
						
					}

					defectList.get(index).setName(nameTextField.getText());
					defectList.get(index).setSymptoms(descriptionTextField.getText());
					
					if (closed == true) {
						
						defectList.get(index).setStatus("Closed");
						
					}
					else {
						
						defectList.get(index).setStatus("Open");
						
					}
					
					FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("DefectLog.fxml"));
					stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
					scene = new Scene(fxmlLoader.load(), 750, 500);
					stage.setTitle("EffortLogger V2");
					
					
					DefectLogViewController control = fxmlLoader.getController();
					control.setDefectList(defectList);
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
		
		for (int i = 0; i < defectList.size(); i++) {
			
			if (defectComboBox.getValue().toString().equals(defectList.get(i).getName())) {
				
				defectList.remove(i);
				break;
				
			}
			
		}
		
		control.setEffortList(effortList);
		control.setDefectList(defectList);
		control.SetUserDefectLog(currentID);
		
		statusLabel.setText("Status: Closed");
		nameTextField.clear();
		descriptionTextField.clear();
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
		control.setList(effortList);
		control.setDefectList(defectList);
		control.SetUserEffortLoggerConsole(currentID);
		
		stage.setScene(scene);
		stage.show();
		
	}
	// load in the information from that defect
	public void DefectOnAction(ActionEvent event) throws IOException {
		
		if (defectComboBox.getValue() != null) {
			
			edit = true;
			String toedit = defectComboBox.getValue();
			
			for (int i = 0; i < defectList.size(); i++) {
				
				if(toedit.equals(defectList.get(i).getName())) {
					
					index = i;
					break;
					
				}
				
			}
			
		}
		
	}
}
