package workoutJournal;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class LogWorkoutController {

    //@FXML DatePicker date;
    //@FXML Button running;
    //@FXML Button strength;
    //@FXML Button skiing;
    //@FXML Button other;
    //@FXML Button reset;
    //@FXML Button addWorkout;
    @FXML Button backFromLogWorkout;
    //@FXML TextField distance;
    //@FXML TextField duration;

    @FXML public void handleBackFromLogWorkout() throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("WorkoutJournal.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage)backFromLogWorkout.getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }





    
    
}
