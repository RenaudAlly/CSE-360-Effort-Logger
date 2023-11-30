package application;

import java.io.IOException;
import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;


public class EffortLogController {
	@FXML
    private TableView<Effort> projectLog;
    @FXML
    private TableColumn<Effort, String> effortCategory;
    @FXML
    private TableColumn<Effort, String> lifeCycle;
    @FXML
    private TableColumn<Effort, String> plan;
    @FXML
    private TableColumn<Effort, String> projectName;
    @FXML
    private TableColumn<Effort, String> time;
    @FXML
    private Button effortLoggerConsoleButton, projectAssembler, projectRemove;
    @FXML
    private Stage stage;
    @FXML
    private Scene scene;
    
    private ID currentID = new ID();
    
    ArrayList<Effort> effortList = new ArrayList<Effort>();
    ArrayList<Defect> defectList = new ArrayList<Defect>();
    
    public void setUserEffortLog(ID newID) {
    	currentID = newID;
    }
    
    
    public void setList(ArrayList<Effort> newList) {
		effortList = newList;
	
		time.setCellValueFactory(new PropertyValueFactory<Effort, String>("timeLogged"));
		projectName.setCellValueFactory(new PropertyValueFactory<Effort, String>("projectName"));
    	lifeCycle.setCellValueFactory(new PropertyValueFactory<Effort, String>("lifeCycle"));
    	plan.setCellValueFactory(new PropertyValueFactory<Effort, String>("plan"));
    	effortCategory.setCellValueFactory(new PropertyValueFactory<Effort, String>("effortCategory"));
    	
    	ObservableList<Effort> olist = FXCollections.observableArrayList(effortList);
    	projectLog.setItems(olist);
	}
    
    public void setDefect(ArrayList<Defect> array) {
    	
    	this.defectList = array;
    	
    }
    
    @FXML
    public void ToEffortLoggerConsole(ActionEvent event) throws IOException {
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
