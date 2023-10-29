package src;

import javafx.application.Application;
import javafx.fxml.*;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class EffortLoggerOutput extends Application{
	
	
	@Override
	public void start(Stage mainStage) throws Exception{
		Parent rootParent = FXMLLoader.load(getClass().getResource("SelectUserPage.fxml"));
		mainStage.setTitle("EffortLogger - Test Output");
		mainStage.setScene(new Scene(rootParent, 800, 500));
		mainStage.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}

}