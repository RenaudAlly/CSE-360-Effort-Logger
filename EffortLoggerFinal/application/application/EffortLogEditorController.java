package application;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class EffortLogEditorController {
	@FXML
	private ComboBox<String> projectComboBox, effortLogEntryComboBox, 
							 lifeCycleStepComboBox, effortCategoryComboBox, 
							 planComboBox;
	@FXML
	private Button clearEffortLogButton, updateEntryButton, 
				   deleteEntryButton, toEffortLogConsoleButton, 
				   loadProjectsButton;
	@FXML
	private TextField dateTextField, startTimeTextField, stopTimeTextField;
	@FXML
	private Stage stage;
	@FXML
	private Scene scene;
	
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
	
	private ID currentID = new ID();
	
	ArrayList<Effort> effortList = new ArrayList<Effort>();
	
	ArrayList<Defect> defectList = new ArrayList<Defect>();
	
	public void SetUserEffortLogEditor(ID newID) {
		currentID = newID;
	}
	
	public void setList(ArrayList<Effort> newList) {
		this.effortList = newList;
	}
	public void setDefect(ArrayList<Defect> array) {
		
		this.defectList = array;
		
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
	
	public void ProjectAction(ActionEvent event) throws IOException {
		if (effortList.isEmpty()) return;
		
		List<String> relevant = new ArrayList<String>();
		
		if (projectComboBox.getValue() != null) {
			String projName = projectComboBox.getValue();
			for (int j = 0; j < effortList.size(); j++) {
				if (effortList.get(j).getProjectName().equals(projName)) {
					String entryName = effortList.get(j).getEffortName();
					relevant.add(entryName);
				}
			}
		}
		
		ObservableList<String> olist = FXCollections.observableArrayList(relevant);
		
		effortLogEntryComboBox.setItems(olist);
	}
	
	public void ClearEffortLog(ActionEvent event) throws IOException {
		if (effortList.isEmpty()) return;
		if(projectComboBox.getValue() == null) return;
		
		if (projectComboBox.getValue() != null) {
			
			String projname = projectComboBox.getValue().toString();
			int size = effortList.size();
			int removed = 0;
			
			for (int i = 0; i < size; i++) {
				if (i == size) return;
				if (effortList.get(i-removed).getProjectName() != null) {
					if (effortList.get(i-removed).getProjectName().toString().equals(projname)) {
						effortList.remove(i-removed);
						removed++;	
					}	
				}	
			}
		}
		ToEffortLogConsole(event);
	}
	
	public int indexOfEffort(String projectName) {
		int index = -1;
		
		
		for (int i = 0; i < effortList.size(); i++) {
			if (effortList.get(i).getProjectName().equals(projectName)) {
				return i;
			}
		}
		return index;
	}
	
	public void EffortLogEntryAction(ActionEvent event) throws IOException {
		if (projectComboBox.getValue() != null) {
			String entryName = effortLogEntryComboBox.getValue();
			for (int j = 0; j < effortList.size(); j++) {
					if (entryName.equals(effortList.get(j).getEffortName())) {
						dateTextField.setText(effortList.get(j).getTimeLogged());
						startTimeTextField.setText(effortList.get(j).getStartTime());
						stopTimeTextField.setText(effortList.get(j).getStopTime());
					}
			}
		}
		
	}
	
	public void UpdateThisEntry(ActionEvent event) throws IOException {
		if (projectComboBox.getValue() == null) return;
		if (effortCategoryComboBox.getValue() == null) return;
		if (effortLogEntryComboBox.getValue() == null) return;
		if (lifeCycleStepComboBox.getValue() == null) return;
		if (planComboBox.getValue() == null) return;
		
		for(int i = 0; i < effortList.size(); i++) {
			String entryName = effortLogEntryComboBox.getValue();
			if (entryName.equals(effortList.get(i).getEffortName())) {
				if (effortCategoryComboBox != null) {
					effortList.get(i).setEffortCategory(effortCategoryComboBox.getValue());
				}
				if (lifeCycleStepComboBox != null) {
					effortList.get(i).setLifeCycle(lifeCycleStepComboBox.getValue());
				}
				if (planComboBox.getValue() != null) {
					effortList.get(i).setPlan(planComboBox.getValue());
				}
				effortList.get(i).setStartTime(startTimeTextField.getText());
				effortList.get(i).setStopTime(stopTimeTextField.getText());
				effortList.get(i).setTimeLogged(dateTextField.getText());
			}
		}
	}

	public void DeleteThisEntry(ActionEvent event) throws IOException {
		if (effortList.isEmpty()) return;
		
		if (effortLogEntryComboBox.getValue() == null) return;
		
		int index = indexOfEffortName(effortLogEntryComboBox.getValue());
		effortList.remove(index);
		ToEffortLogConsole(event);
	}

	public int indexOfEffortName(String value) {
		int index = -1;
		for(int i = 0; i < effortList.size(); i++) {
			if (value.equals(effortList.get(i).getEffortName())) {
				return i;
			}
		}
		return index;
	}

	public void ToEffortLogConsole(ActionEvent event) throws IOException {
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

}
