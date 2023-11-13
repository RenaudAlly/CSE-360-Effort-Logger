package applet;

import javafx.application.Application;
import javafx.fxml.*;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Applet extends Application{
	@Override
	public void start(Stage mainStage) throws Exception{
		Parent rootParent = FXMLLoader.load(getClass().getResource("PlanningPoker.fxml"));
		mainStage.setTitle("EffortLogger - Planning Poker");
		mainStage.setScene(new Scene(rootParent));
		mainStage.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
