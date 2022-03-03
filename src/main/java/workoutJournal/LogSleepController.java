package workoutJournal;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LogSleepController {

    @FXML private Button backFromLogSleep, confirmSleep, confirmMood;
    @FXML private  TextField sleepAmount;
    @FXML private RadioButton mood1, mood2, mood3, mood4, mood5;

    private List<Integer> sleepList = new ArrayList<>();
    private List<Integer> moodList = new ArrayList<>();
    
    @FXML private void handleBackFromLogSleep() throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("WorkoutJournal.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) backFromLogSleep.getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML private void handleConfirmSleep() {
        int sleep = Integer.parseInt(sleepAmount.getText());
        sleepList.add(sleep);
        sleepAmount.clear();
    }

    @FXML private void handleConfirmMood() {
        if (mood1.isSelected()) {
            moodList.add(1);
            mood1.setSelected(false);
        } else if (mood2.isSelected()) {
            moodList.add(2);
            mood2.setSelected(false);
        } else if (mood3.isSelected()) {
            moodList.add(3);
            mood3.setSelected(false);
        } else if (mood4.isSelected()) {
            moodList.add(4);
            mood4.setSelected(false);
        } else if (mood5.isSelected()) {
            moodList.add(5);
            mood5.setSelected(false);
        }
    }
}
