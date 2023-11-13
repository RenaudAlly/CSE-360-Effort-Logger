package application;

import javafx.scene.Scene;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.*;
import javafx.scene.Node;
import javafx.stage.Stage;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import java.io.IOException;
import java.util.ArrayList;

public class DefectViewController {
	
	private Stage stage;
	private Scene scene;
	
	// user who logged in
	private ID user;
	private boolean closed = false;
	private boolean edit = false;
	private boolean arrayexists = false;
	private ArrayList<Defect> array;
	private ArrayList<Effort> effort;
	private Defect defect;
	private int index;
	
	@FXML
	private Label DefectLabel;
	@FXML
	private Label StatusText;
	@FXML
	private TextField NameInput;
	@FXML
	private TextField DescriptionInput;
	@FXML	
	private ComboBox<String> InjectComboBox, CategoryComboBox, RemoveComboBox, FixComboBox, DefectComboBox, ProjectComboBox;
	
	public void setUser(ID user1) {
		
		this.user = user1;
		
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
		if (ProjectComboBox.getValue() != null) {
			
			String projname = ProjectComboBox.getValue().toString();
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
		DefectComboBox.setItems(list);
		
		DefectLabel.setText(Integer.toString(relevant.size()) + " defects for this project.");
		
	}
	public void ClearOnAction(ActionEvent event) throws IOException {
		
		if (ProjectComboBox.getValue() != null) {
			
			String projname = ProjectComboBox.getValue().toString();
			int size = array.size();
			int removed = 0;
			
			for (int i = 0; i < size-removed; i++) {
				
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
		stage.setScene(scene);
		stage.show();
		
		
		
	}
	public void CreateOnAction(ActionEvent event) throws IOException {
		
		StatusText.setText("Status: Open");
		edit = false;
		closed = false;
		NameInput.clear();
		DescriptionInput.clear();
		InjectComboBox.setValue(null);
		RemoveComboBox.setValue(null);
		CategoryComboBox.setValue(null);
		FixComboBox.setValue(null);
		DefectComboBox.setValue(null);
		
	}
	public void CloseOnAction(ActionEvent event) throws IOException {
		
		closed = true;
		StatusText.setText("Status: Closed");
		
	}
	public void ReopenOnAction(ActionEvent event) throws IOException {
		
		closed = false;
		StatusText.setText("Status: Open");
		
	} 
	public void UpdateOnAction(ActionEvent event) throws IOException {
		
		if (edit == false) {
			
			defect = new Defect();
			if (arrayexists == false) {
				
				array = new ArrayList<Defect>();
				arrayexists = true;
				
			}
				
			if (CategoryComboBox.getValue() != null) {
				
				defect.setCategory(CategoryComboBox.getValue().toString());
				
			}
			else {
				
				defect.setCategory("");
				
			}
			if (InjectComboBox.getValue() != null) {
				
				defect.setInject(InjectComboBox.getValue().toString());
			
			}
			else {
				
				defect.setInject("");
				
			}
			if (RemoveComboBox.getValue() != null) {
				
				defect.setRemove(RemoveComboBox.getValue().toString());
			
			}
			else {
				
				defect.setRemove("");
				
			}
			if (ProjectComboBox.getValue() != null) {
				
				defect.setProject(ProjectComboBox.getValue().toString());
			
			}
			else {
				
				defect.setProject("None");
				
			}

			defect.setName(NameInput.getText().toString());
			defect.setSymptoms(DescriptionInput.getText().toString());
			
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
					stage.setScene(scene);
					stage.show();
					
				}
				
				
			}
			
		}
		
		else if (edit == true) {
			
			if (NameInput.getText().toString().length() > 0) {
				
				boolean found = false;
				
				for (int i = 0; i < array.size(); i++) {
					
					if (NameInput.getText().toString().equals(array.get(i).getName())) {
						
						found = true;
						
					}
					
				}
				
				if (found == false) {
					
					if (CategoryComboBox.getValue() != null) {
						
						array.get(index).setCategory(CategoryComboBox.getValue().toString());
						
					}
					else {
						
						array.get(index).setCategory("");
						
					}
					if (InjectComboBox.getValue() != null) {
						
						array.get(index).setInject(InjectComboBox.getValue().toString());
					
					}
					else {
						
						array.get(index).setInject("");
						
					}
					if (RemoveComboBox.getValue() != null) {
						
						array.get(index).setRemove(RemoveComboBox.getValue().toString());
					
					}
					else {
						
						array.get(index).setRemove("");
						
					}
					if (ProjectComboBox.getValue() != null) {
						
						array.get(index).setProject(ProjectComboBox.getValue().toString());
					
					}
					else {
						
						array.get(index).setProject("None");
						
					}

					array.get(index).setName(NameInput.getText().toString());
					array.get(index).setSymptoms(DescriptionInput.getText().toString());
					
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
					stage.setScene(scene);
					stage.show();
					
				}
				
				
			}
			
		}
		
		
	}

	public void DeleteOnAction(ActionEvent event) throws IOException {
		
		// add functionality to delete the selected defect
		StatusText.setText("Status: Closed");
		NameInput.clear();
		DescriptionInput.clear();
		InjectComboBox.setValue(null);
		RemoveComboBox.setValue(null);
		CategoryComboBox.setValue(null);
		FixComboBox.setValue(null);
		
	}
	public void ProceedOnAction(ActionEvent event) throws IOException {
		
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("EffortLoggerConsole.fxml"));
		stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		scene = new Scene(fxmlLoader.load(), 750, 500);
		stage.setTitle("EffortLogger V2");
		
		EffortLoggerConsoleController control = fxmlLoader.getController();
		control.setList(effort);
		control.setDefectList(array);
		stage.setScene(scene);
		stage.show();
		
	}
	// load in the information from that defect
	public void DefectOnAction(ActionEvent event) throws IOException {
		
		if (DefectComboBox.getValue() != null) {
			
			edit = true;
			String toedit = DefectComboBox.getValue();
			
			for (int i = 0; i < array.size(); i++) {
				
				if(toedit.equals(array.get(i).getName())) {
					
					index = i;
					break;
					
				}
				
			}
			
		}
		
	}
}
