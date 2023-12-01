package application;

import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.Node;
import javafx.stage.Stage;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.channels.NonWritableChannelException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class LoginViewController {
	private Scene scene;
	private Stage stage;
	@SuppressWarnings("unused")
	private ID currentID = new ID();
	private ArrayList<Effort> effortList = new ArrayList<Effort>();
	private ArrayList<Defect> defectList = new ArrayList<Defect>();
	private ArrayList<ID> usersArrayList = new ArrayList<ID>();
	
	@FXML
	private TextField usernameInput;
	@FXML
	private PasswordField passwordInput;
	@FXML
	private Label signUpText;
	@FXML
	private ComboBox<String> comboBoxInput; 

	public void SetUserLoginScreen(ID newID) {
		currentID = newID;
	}
	
	public void SetEffortList(ArrayList<Effort> newList) {
		effortList = newList;
	}
	
	public void SetDefectList(ArrayList<Defect> newList) {
		defectList = newList;
	}
	
	public void SignUpButtonOnAction(ActionEvent event) throws IOException {
		// TODO: WARNING that we are assuming user names are unique
		// Populates the users from the Users_List.csv file
		usersArrayList = importValidUsersList();
				
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("SignUpScreen.fxml"));
		stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		scene = new Scene(fxmlLoader.load(), 750, 500);
		stage.setTitle("Sign Up Page");
		
		stage.setScene(scene);
		stage.show();
	}
	
	public void LogInButtonOnAction(ActionEvent event) throws IOException {
		
		usersArrayList = importValidUsersList();
		
		// first hard-coded credential
		ID adam = new ID();
		adam.setUser("adam");
		adam.setPass("bonnet");
		adam.setLevel(2);
		usersArrayList.add(adam);
			
		// second hard-coded credential
		ID matt = new ID();
		matt.setUser("matt");
		matt.setPass("wang");
		matt.setLevel(2);
		usersArrayList.add(matt);
			
		// third hard-coded credential
		ID arjun = new ID();
		arjun.setUser("arjun");
		arjun.setPass("khetan");
		arjun.setLevel(1);
		usersArrayList.add(arjun);

		// third hard-coded credential
		ID sawyer = new ID();
		sawyer.setUser("sawyer");
		sawyer.setPass("kesti");
		sawyer.setLevel(2);
		usersArrayList.add(sawyer);

		// third hard-coded credential
		ID sam = new ID();
		sam.setUser("meshach");
		sam.setPass("samuel");
		sam.setLevel(0);
		usersArrayList.add(sam);

		// tracks user inputted details
		ID useridfinal = new ID();
	
		// save the username input and password input to string
		String username = usernameInput.getText().toString();
		String password = passwordInput.getText().toString();
		
		// if the username or password fall out of the range of acceptable number of characters
		if (username.length() > 32 || password.length() > 32) {
			
			// display an invalid message that instructs them to change it
			signUpText.setText("Please enter an input that is 32 characters or less.");
			
		}
		
		// if something has been inputted in both the username and password fields
		else if (username.length() > 0 && password.length() > 0) {
			
			// initialize the boolean that indicates whether the username password combo is valid
			boolean found = false;
			
			boolean perm = false;
			// for all elements in the array
			for (int i = 0; i < usersArrayList.size(); i++) {
				
				// if the username password combo is in this element of the array
				if (username.equals(usersArrayList.get(i).getUser()) && password.equals(usersArrayList.get(i).getPass())) {
					
					if (usersArrayList.get(i).getLevel() != 0) {
						
						// update found to be true
						found = true;
						useridfinal = usersArrayList.get(i);
						break;
						
					}
					else {		
						signUpText.setText("Your account does not have permission.");
						perm = true;
						break;	
					}	
				}
			}
			
			
			// if found is true, meaning the username and password are valid
			if (found == true) {
				
				// TODO: Silently import if effort and defect logs exist
				ImportData(effortList, defectList);
				
				String go = comboBoxInput.getValue().toString();
				
				if (go.equals("Effort Logger")) {
					FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("EffortLoggerConsole.fxml"));
					stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
					scene = new Scene(fxmlLoader.load(), 750, 500);
					stage.setTitle("EffortLogger V2");
					
					EffortLoggerConsoleController control = fxmlLoader.getController();
					control.SetUserEffortLoggerConsole(useridfinal);
					control.setList(effortList);
					control.setDefectList(defectList);
					
					stage.setScene(scene);
					stage.show();
					
				}
				else if(go.equals("Planning Poker")){
					FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("PlanningPoker.fxml"));
					stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
					scene = new Scene(fxmlLoader.load(), 750, 500);
					stage.setTitle("EffortLogger - Planning Poker Session");
					
					PlanningPokerController control = fxmlLoader.getController();
					control.SetUserPlanningPoker(useridfinal);
					control.SetDefectList(defectList);
					control.SetEffortList(effortList);
					
					stage.setScene(scene);
					stage.show();
				}
			}
			
			// if the username or password is not valid
			else {
				if (perm == false) {
					
					// display an invalid message that tells the user to try again
					signUpText.setText("Invalid Username or Password! Please try again. ");
				}
			}
		}
	}
	
	public void ImportData(ArrayList<Effort> effortLogs, ArrayList<Defect> defectLogs) throws IOException {
		// WARNING: Note that we are assuming the user does not rename their CSV file
		try {
			File effortLogsFile = new File("Effort_Logs.csv");
			File defectLogsFile = new File("Defect_Logs.csv");		
			Scanner effortLogListPopulatorScanner = new Scanner(effortLogsFile);
			Scanner defectLogListPopulatorScanner = new Scanner(defectLogsFile);
			
			effortLogListPopulatorScanner.nextLine();
			while (effortLogListPopulatorScanner.hasNext()) {
				String[] oStrings = effortLogListPopulatorScanner.nextLine().split(",");
				effortList.add(new Effort(oStrings[0], oStrings[1], oStrings[2], oStrings[3], oStrings[4],
										oStrings[5], oStrings[6], oStrings[7]));
			}
			
			defectLogListPopulatorScanner.nextLine();
			while (defectLogListPopulatorScanner.hasNext()) {
				String[] oStrings = defectLogListPopulatorScanner.nextLine().split(",");
				defectLogs.add(new Defect(oStrings[0], oStrings[1], oStrings[2], oStrings[3], oStrings[4],
										oStrings[5], oStrings[6]));
			}
		
			// Reading Defect Logs
			effortLogListPopulatorScanner.close();
			defectLogListPopulatorScanner.close();
			
		} 
		catch (FileNotFoundException e) {
			System.out.println("We cold not find the effort or defect log CSV files in your current directory. Skill issue.");
		}
		catch (IOException e) {
			System.out.println("We cold not find the effort or defect log CSV files in your current directory. Skill issue.");
		}

	}
	
	public ArrayList<ID> importValidUsersList() {
		// Reading user login information from the CSV file
		ArrayList<ID> usersList = new ArrayList<ID>();
		try {
			File csvUsersListFile = new File("Users_List.csv");
			
			// To account for the case where the user has no users list file, we offer a default login
			if (!csvUsersListFile.exists()) {
				FileWriter fw = new FileWriter(csvUsersListFile);
				fw.write("Username,Password,Level\n");
				fw.write("test,test,2\n");
				fw.close();
			}
			
			Scanner sc = new Scanner(csvUsersListFile);
			sc.nextLine();
			while (sc.hasNextLine()) {
				String[] userLoginInfoOuptutStrings = sc.nextLine().split(",");
				usersList.add(new ID(userLoginInfoOuptutStrings[0], userLoginInfoOuptutStrings[1],
						Integer.parseInt(userLoginInfoOuptutStrings[2])));
			}
			sc.close();
		}
		catch (NoSuchElementException e) {
			System.out.println("It appears that your users list file may be empty.");
		} catch(FileNotFoundException e) {
			System.out.println("Please try to make a Users_List.csv with login info."); 
		} catch (IOException e) {
			System.out.println("An unexpected error occurred when trying to read from users list.");
		}
		
		// Finished populating the valid users list
		return usersList;
	}
	
}
