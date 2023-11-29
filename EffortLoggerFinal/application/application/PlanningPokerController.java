package application;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.Node;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class PlanningPokerController {
	private Stage stage;
	private Scene scene;
	private Parent root;
	private ArrayList<Effort> effortList = new ArrayList<Effort>();
	private ArrayList<Defect> defectList = new ArrayList<Defect>();

	@FXML
	private Button generateUserStory, submitValueButton, returnToEffortLoggerButton;
	
	@FXML
	private Label storyBoardText;
	
	@FXML
	TextField textEntry;
	
	@FXML
	MenuButton planningPokerStyleButton;
	
	@FXML
	private Text user1Text, user2Text, user3Text, user4Text, user5Text;
	
	@FXML
	private Text historicalEffortPlanningPoker, historicalDefetcsPlanningPoker, AvergeEffortPlanningPoker, CurrentProjPlanningPoker;
	
	
	private static int averageForFib;
	ID currentID = new ID();
	Defect defects = new Defect();
	Effort effort = new Effort();
	
	public void SetEffortList(ArrayList<Effort> newList) {
		effortList = newList;
	}
	
	public void SetDefectList(ArrayList<Defect> newList) {
		defectList = newList;
	}
	
	public void SetUserPlanningPoker(ID newID) {
		currentID = newID;
		//defects = newDefect;
		//effort = newEffort;
		
		historicalDefetcsPlanningPoker.setText(currentID.getUser()+ "'s Historical Defect Data: ");
		historicalEffortPlanningPoker.setText(currentID.getUser()+ "'s Historical Effort Data: ");
	}
	
	public void GoToLogginScreen(ActionEvent event) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("LoginScreen.fxml"));
		root  = loader.load();
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
}
	public void DisplayNewStory(ActionEvent event) {
		String[] Stories = {
				  "As a user, I want a search functionality to find similar projects by using keywords so that I can quickly identify relevant past experiences for reference during planning and not have a bubble sort going on in the background to determine which projects to show."
				, "As a developer, I want to be able to search for similar projects in my historical data using project attributes, such as size, complexity and technology used so that I can show them during planning poker sessions as evidence behind my chosen estimate."
				, "As a project manager, I want a more versatile search function that allows me to set additional fields for advanced searching essentially. This would make it so that I can find projects for a desired industry domain, customer type and project duration which would be helpful insights for me."
				, "As a team member, I care about the success and planning of the project, that is why I want the search to also consider the project outcome such as its failure or current status. This is so that we can take challenges and learnings from the project into account and gauge the risks and uncertainties accordingly."
				, "As a project manager, in order to identify which individuals should speak up after a round of planning poker has finished, I want to know the story points assigned for tasks within each project. This is so that in case there is no clear outlier, I can make a more informed suggestion to the team and help us reach consensus quicker."
				, "As a developer, I want the family of Effort Logger tools to allow me to access historical data effortlessly and intuitively without having to go through extensive user training. This is so that the application will empower and motivate me to use it rather than become a chore."
				, "As a user, I want Effort Logger to grant me user-specific access so that their data privacy is maintained. It will also enable me to make individualized decisions that are based on my own experiences, rather than influenced by others’."
				, "As a user, I want easy access to my historical data, ensuring that I can quickly find information about my past effort logs when participating in planning sessions. This is so that I can help make more accurate estimates for story points."
				, "As a team member, I want the tool to provide a convenient way to access historical data for each user, helping me make more informed decisions during planning sessions. Accessing my historical data will help me contribute better to collective decision making so we can finish faster."
				, "As a project manager, I want access to all the projects that have been completed alongside their project descriptions, languages used, effort logs, defects, repairs needed among other details. This is so that I can make sure that the users are able to make more informed decisions based on historical data."
		};
		int randVal = (int)(10 * Math.random());
		
		while (Stories[randVal].equals(storyBoardText.getText())) {
			randVal = (int)(10 * Math.random());
		}
		
		storyBoardText.setText(Stories[randVal]);
	}
	
	private static boolean isFib(String x) { 
		String[] fib = {
				"0"
				,"1/2"
				,"1"
				,"2"
				,"3"
				,"5"
				,"8"
				,"13"
				,"20"
				,"40"
				,"100"
				,"?"
		};
		int i = 0;
		while( i < fib.length) {
			if(x.equals(fib[i])) {
				averageForFib = i;
				return true;
			}
			
			i++;
		}
		
		return false;
	}
	
	public void UpdatePlanningPokerValue(ActionEvent event) throws IOException{
		
		DecimalFormat numberFormat = new DecimalFormat("#.00");

		try {
			SetUserPlanningPoker(currentID);
			
			
			String styleOption = planningPokerStyleButton.getText();
			String input = textEntry.getText();
			int effort = Integer.parseInt(input);
			
			if (!styleOption.equals("One to Ten") && !styleOption.equals("Fibonacci") ) {
				throw new IOException();
			}
			
			else if ((styleOption.equals("One to Ten") && (effort <=0 || effort > 10)) || (styleOption.equals("Fibonacci") && (!isFib(input) || input.equals("?")))) {
				
				throw new IOException();
			}

			else {		
			
			int[] randomOneTen = {1, 2, 3, 4, 5 ,6, 7, 8, 9, 10};
			String[] fib = {"0", "1/2", "1", "2", "3", "5", "8", "13", "20", "40", "100", "?"};
			Double average = 0.0;
			
			if(styleOption.equals("One to Ten") ) {
				int i = (int)(10*Math.random());
				i = randomOneTen[i];
				average = average + i ;
				historicalEffortPlanningPoker.setText(historicalEffortPlanningPoker.getText() + Integer.toString(i));

				user1Text.setText("User 1: " + Integer.toString(i)); 
				i = (int)(10*Math.random());
				i = randomOneTen[i];
				average = average + i ;
				
				user2Text.setText("User 2: " + Integer.toString(i));
				i = (int)(10*Math.random());
				i = randomOneTen[i];
				average = average + i ;
				
				user3Text.setText("User 3: " + Integer.toString(i));
				i = (int)(10*Math.random());
				i = randomOneTen[i];
				average = average + i ;
				
				user4Text.setText("User 4: " + Integer.toString(i));
				i = (int)(10*Math.random());
				i = randomOneTen[i];
				average = average + i ;
				
				user5Text.setText("User 5: " + Integer.toString(i));
				average = average + effort;
				average  = average / 6.0;
				
				AvergeEffortPlanningPoker.setText("Average Effort: " +  numberFormat.format(average));
			}
			
			else {
				
				int i = (int)(fib.length*Math.random());
				average = average + i;
				historicalEffortPlanningPoker.setText(historicalEffortPlanningPoker.getText() + fib[i]);

				
				user1Text.setText("User 1: " + fib[i]); 
				i = (int)(fib.length*Math.random());
				average = average + i;

				user2Text.setText("User 2: " + fib[i]);
				i = (int)(fib.length*Math.random());
				average = average + i;

				user3Text.setText("User 3: " + fib[i]);
				i = (int)(fib.length*Math.random());
				average = average + i;

				user4Text.setText("User 4: " + fib[i]);
				i = (int)(fib.length*Math.random());
				average = average + i;

				user5Text.setText("User 5: " + fib[i]);
				
				average = averageForFib + average;
				average  = average / 6;

				
				AvergeEffortPlanningPoker.setText("Average Effort: " +  fib[(int)Math.round(average)]);

				}
			}
			textEntry.clear();
			textEntry.setPromptText("Enter a new value!");
			
		} 
		catch (Exception e) {
			if(planningPokerStyleButton.getText().equals("One to Ten")) {
				textEntry.clear();
				textEntry.setPromptText("Enter a input 1 - 10");
			}
			else if (planningPokerStyleButton.getText().equals("Fibonacci")) {
				textEntry.clear();
				textEntry.setPromptText("Enter a valid Fib num");
			} 
			else {
			
			textEntry.clear();
			textEntry.setPromptText("Select a Style!");
			}
		}
	}
	
	public void ToOneToTen() {
		planningPokerStyleButton.setText("One to Ten");
	}
	
	public void ToFibonacci() { 
		planningPokerStyleButton.setText("Fibonacci");
	}
	
	public void ChangeStyle(ActionEvent event) {
	planningPokerStyleButton.getItems();
	}
	
}
