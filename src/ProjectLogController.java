import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;
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


public class ProjectLogController {
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
    
    private Project p;
    
    private Effort e;
    
    List<Effort> effortList = new ArrayList<Effort>();
    
    
    public void setList(List<Effort> newList) {
		effortList = newList;
	
		time.setCellValueFactory(new PropertyValueFactory<Effort, String>("timeLogged"));
		projectName.setCellValueFactory(new PropertyValueFactory<Effort, String>("projectName"));
    	lifeCycle.setCellValueFactory(new PropertyValueFactory<Effort, String>("lifeCycle"));
    	plan.setCellValueFactory(new PropertyValueFactory<Effort, String>("plan"));
    	effortCategory.setCellValueFactory(new PropertyValueFactory<Effort, String>("effortCategory"));
    	
    	ObservableList<Effort> olist = FXCollections.observableArrayList(effortList);
    	projectLog.setItems(olist);
	}
    
    /*public void RemoveTheProject(ActionEvent event) throws IOException {
    	if (list.isEmpty()) return;
    	
    	String textFieldName = removeProjectTextField.getText();
    	int index = RemoveProject(textFieldName);

    	list.remove(index);
  
    	setList(list);
    }*/
    
    /*public void RemoveProject(String projectName) {
    	int index = -1;
    	for (int i = 0; i < list.size(); i++) {
    		if ((list.get(i).getProjectName()).equals(projectName)) {
    			index = i;
    		} 
    	}
    	return index;
    }*/
    
    
    /*public void AssembleProject(ActionEvent event) throws IOException {
    	String a = projectTextField.getText();
    	String b = lifeCycleTextField.getText();
    	String c = effortCategoryTextField.getText();
    	String d = planTextField.getText();
    	
    	p = new Project();
    	
    	p.setProjectName(a);
    	p.setLifeCycle(b);
    	p.setEffortCategory(c);
    	p.setPlan(d);
    	
    	if (a.isEmpty() || b.isEmpty() || c.isEmpty() || d.isEmpty()) return;
    	
    	list.add(p);
    	
    	setList(list);
    }*/
    
    @FXML
    public void ToEffortLoggerConsole(ActionEvent event) throws IOException {
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("EffortLoggerConsole.fxml"));
        Parent root = loader.load();
        
        EffortLoggerConsoleController controller = loader.getController();
        controller.setList(effortList);

        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    } 
}
