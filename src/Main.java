import javafx.fxml.*;
import java.io.IOException;
import javafx.application.*;
import javafx.scene.Scene;
import javafx.scene.Parent;
import javafx.stage.*;

public class Main extends Application {
	@Override
	public void start(Stage mainStage) throws Exception {
		Parent rootParent = FXMLLoader.load(getClass().getResource("EffortLoggerConsole.fxml"));
		mainStage.setTitle("EffortLoggerV2.0");
		mainStage.setScene(new Scene(rootParent, 750, 500));
		mainStage.show();
	}
	public static void main(String[] args) {
		launch(args);
	}
}

