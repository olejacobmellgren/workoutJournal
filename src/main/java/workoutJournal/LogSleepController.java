package workoutJournal;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class LogSleepController {

    @FXML Button backFromLogSleep;
    
    @FXML public void handleBackFromLogSleep() throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("WorkoutJournal.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) backFromLogSleep.getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
}
