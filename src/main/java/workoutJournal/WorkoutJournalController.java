package workoutJournal;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class WorkoutJournalController {


    @FXML private Button logWorkout, logSleep, overview;

    @FXML private void handleLogWorkout() throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("LogWorkout.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage)logWorkout.getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML private void handleLogSleep() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("LogSleep.fxml"));
        Stage stage = (Stage) logSleep.getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML private void handleOverview() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Overview.fxml"));
        Stage stage = (Stage) overview.getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    
}
