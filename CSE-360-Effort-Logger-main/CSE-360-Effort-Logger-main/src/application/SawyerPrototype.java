// Sawyers Risk Prototype
// Name: Sawyer Kesti
// ASU ID: 12191187987
// Class Time: Tuesday 9:00 - 10:15 AM
package application;

import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.VBox;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.paint.Color;
import javafx.scene.layout.CornerRadii;
import javafx.scene.control.Label;
import javafx.scene.control.Button;


public class SawyerPrototype {
	public SawyerPrototype() {}
	
	public void openPrototypeStage(Stage primaryStage) {
		primaryStage.setTitle("Authorization Prototype"); // name the stage
		
		ComboBox<String> comboBox = new ComboBox<>(); // combo box for changing authorization
		comboBox.setPromptText("Select a Level of Authorization");
		
		comboBox.getItems().addAll("First Level Supervisor", "Firm Engineer", "Unauthorized User"); // add levels of auth. to combo box
		comboBox.setOnAction(event -> { // if combo box gets clicked
			String selectedOption = comboBox.getValue(); // record the level of auth.
			User user = new User(); // make a new user
			if (selectedOption != null) {
				switch (selectedOption) {
					case "First Level Supervisor":
						user.setUserID(2); // assign level of auth according to user
						break;
					case "Firm Engineer":
						user.setUserID(1); // assign level of auth according to user
						break;
					case "Unauthorized User":
						user.setUserID(-1); // assign level of auth according to user
						break;
					default:
						user.setUserID(0); // default case
				}
			primaryStage.close(); // close main stage
			openSecondScene(primaryStage, selectedOption, user.getUserID()); // open effortlogger with recorded values
			}
		});
		
		
		VBox vbox = new VBox(); // vbox to add labels, buttons, etc
		vbox.getChildren().add(comboBox); // add the combobox to the vbox
		
		// housekeeping to make it look pretty
		vbox.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT, CornerRadii.EMPTY, javafx.geometry.Insets.EMPTY)));
		vbox.setLayoutX(25);
		vbox.setLayoutY(50);
		
		// add the vbox to the scene
		Scene scene = new Scene(vbox, 300, 150);
		scene.setFill(Color.TRANSPARENT);
		
		primaryStage.setScene(scene); // set the scene and show the stage
		primaryStage.show();
	}
	
	public void openSecondScene(Stage primaryStage, String selectedOption, int userID) { // this is the second stage
		Stage secondStage = new Stage(); // make the second stage and title it
		secondStage.setTitle("Effort Logger V2");
		
		Label label = new Label("Authorization Level: " + selectedOption); // display current authorization
		
		Button changeAuth = new Button(); // button to change authorization
	
		changeAuth.setText("Change Authorization Level"); // event handler for the change authorization button
		changeAuth.setOnAction(event -> {
			primaryStage.show(); // open the primary stage and close the second stage
			secondStage.close();
			});
		
		Button testAuth1 = new Button(); // make the 3 feature buttons for testing
		Button testAuth2 = new Button();
		Button testAuth3 = new Button();
		
		testAuth1.setText("Feature 1"); // name the buttons
		testAuth2.setText("Feature 2");
		testAuth3.setText("Feature 3");
		
		testAuth1.setOnAction (event -> { // feature 1 event handler 
			int featureAuth = 2;		  // allows all users that have any level of authorization to use this feature
			if (userID == 1 || userID == 0 || userID == 2) {
				featureAuth = -1; // assign lowest level of auth to feature
			}
			featureStage(secondStage, userID, featureAuth, selectedOption); // open the feature stage
		});
		
		testAuth2.setOnAction (event -> { // feature 2 event handler
			int featureAuth = 2; // allows only certain users to access this feature
			if (userID == 1 || userID == 2) {
				featureAuth = 0; // assigns one level up from the previous level of authorization
			}
			featureStage(secondStage, userID, featureAuth, selectedOption); // open the feature stage
		});
		
		testAuth3.setOnAction (event -> { // feature 3 event handler
			int featureAuth = 2; 		  // allows only the highest level of authorization to access this feature
			if (userID == 2) {
				featureAuth = 1; // assigns the max authorization
			}
			featureStage(secondStage, userID, featureAuth, selectedOption); // open the feature stage
		});
		
		VBox secondLayout = new VBox(); // framework for testing buttons, labels, and change authorization button
		
		
		// house keeping for the spacing, padding, and preferred height of vbox
		secondLayout.setSpacing(10);
		secondLayout.setLayoutX(20);
		secondLayout.setLayoutY(20);
		secondLayout.setPrefWidth(100);
		secondLayout.setPrefHeight(100);
		BackgroundFill backgroundFill = new BackgroundFill(Color.TRANSPARENT, CornerRadii.EMPTY, javafx.geometry.Insets.EMPTY);
		Background background = new Background(backgroundFill);
		secondLayout.setBackground(background);
		
		// add all the labels, and buttons to the vbox
		secondLayout.getChildren().add(label);
		secondLayout.getChildren().add(changeAuth);
		secondLayout.getChildren().add(testAuth1);
		secondLayout.getChildren().add(testAuth2);
		secondLayout.getChildren().add(testAuth3);

		Scene secondScene = new Scene(secondLayout, 800, 600); // create the scene and adjust size
		
		secondStage.setScene(secondScene); // set the scene of the second stage and show
		secondStage.show();
	}
	
	public void featureStage(Stage secondStage, int userID, int featureAuth, String selectedOption) { // the feature stage, deals with user id and 	  
		Stage featureStage = new Stage();															// responds accordingly
		featureStage.setTitle("Output"); // create and set the text for the stage
		
		VBox vbox = new VBox(); // vbox for adding components into
		
		Label label = new Label(); // label for displaying if authorization was accepted or not
		
		Button btn = new Button(); // button for returning back to stage 2
		btn.setText("Return back");
		
		// if user id is bigger than feature authorization number, then allow for user to use the feature
		if (userID > featureAuth) { 
			label.setText("Congratulations you are authorized to use this feature!");
		} 
		if (userID < featureAuth) { // else not allowed to use the feature is feature authorization number is larger
			label.setText("Uh oh! You are not authorized to use this feature!");
		}
		
		// returns back to stage two if button is pushed
		btn.setOnAction (event -> {
			secondStage.show();
			featureStage.close();
		});
		
		// add the components to the vbox
		vbox.getChildren().add(label);
		vbox.getChildren().add(btn);
		
		// house keeping for formatting the vbox
		vbox.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT, CornerRadii.EMPTY, javafx.geometry.Insets.EMPTY)));
		vbox.setLayoutX(25);
		vbox.setLayoutY(50);
		
		// make and set the scene and show
		Scene featureScene = new Scene(vbox, 400, 150);
		featureStage.setScene(featureScene);
		featureStage.show();
		}
	}

