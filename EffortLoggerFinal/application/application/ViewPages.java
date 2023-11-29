package application;

import javafx.application.*;
import javafx.event.*;
import javafx.geometry.Pos;
import javafx.scene.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.*;

public class ViewPages extends Application implements EventHandler<ActionEvent> {
	
	Stage window;
	Scene mainScene, employeeScene, supervisorScene;
	
	Button employeeButton;
	Button supervisorButton;
	Button submitButton;
	Button rejectButton;
	
	public static void main(String[] args) {
		launch(args);
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked", "deprecation" })
	@Override
	public void start(Stage primaryStage) throws Exception{
		
		submitButton = new Button();
		submitButton.setText("Submit");
		
		rejectButton = new Button();
		rejectButton.setText("Reject");
		
		// Main Window		
		window = primaryStage;
		window.setTitle("Main Menu");
		
		// Employee Button		
		employeeButton = new Button();
		employeeButton.setText("Employee View");
		employeeButton.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				window.setScene(employeeScene);
				window.setTitle("Employee Input Form");
			}
		});
		
		// Supervisor Button		
		supervisorButton = new Button();
		supervisorButton.setText("Supervisor View");
		supervisorButton.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				window.setScene(supervisorScene);
				window.setTitle("Supervisor View");
			}
		});
		
		// Main Menu Page		
		VBox mainMenu = new VBox(35);
		mainMenu.setAlignment(Pos.CENTER);
		mainMenu.getChildren().addAll(employeeButton, supervisorButton);
		mainScene = new Scene(mainMenu, 500, 500);
		
		// Employee Page
		VBox employeeView = new VBox(35);
		employeeView.setAlignment(Pos.CENTER);
		Label hoursLabel = new Label("Number of Hours:");
		TextField hoursTextField = new TextField();
		hoursTextField.setMaxWidth(100);
		employeeView.getChildren().addAll(hoursLabel, hoursTextField, submitButton);
		employeeScene = new Scene(employeeView, 500, 500);
		
		// Supervisor Page
		VBox supervisorView = new VBox(15);
		supervisorView.setAlignment(Pos.CENTER);
		
		TableView table = new TableView<Person>();
		TableColumn firstNameColumn = new TableColumn<Person, String>("First Name");
		firstNameColumn.setCellValueFactory(new PropertyValueFactory<Person, String>("firstName"));
		TableColumn lastNameColumn = new TableColumn<Person, String>("Last Name");
		lastNameColumn.setCellValueFactory(new PropertyValueFactory<Person, String>("lastName"));
		TableColumn hoursColumn = new TableColumn<Person, Integer>("Hours");
		hoursColumn.setCellValueFactory(new PropertyValueFactory<Person, Integer>("hours"));
		
		table.getColumns().add(firstNameColumn);
		table.getColumns().add(lastNameColumn);
		table.getColumns().add(hoursColumn);
		
		table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		
		table.getItems().add(new Person("Meshach", "Samuel", 5));
		table.getItems().add(new Person("Matthew", "Wang", 8));
		table.getItems().add(new Person("Arjun", "Khetan", 7));
		table.getItems().add(new Person("Adam", "Bonnet", 6));
		table.getItems().add(new Person("Sawyer", "Kesti", 9));
		
		// Reject Button
		rejectButton.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				table.getItems().removeAll(table.getSelectionModel().getSelectedItem());
			}
		});
		
		supervisorView.getChildren().addAll(table, rejectButton);
		
		supervisorScene = new Scene(supervisorView, 500, 300);
		
		// Final Rendering		
		window.setScene(mainScene);
		window.show();
	}

	@Override
	public void handle(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}