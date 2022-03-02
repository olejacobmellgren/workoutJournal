package workoutJournal;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LogWorkoutController {

    private Workout workout;
    private List<WorkoutYear> workoutYearsList = new ArrayList<>();
    private List<Button> buttons = new ArrayList<>();

    @FXML DatePicker date;
    @FXML Button running, strength, skiing, other, reset, addWorkout, backFromLogWorkout;
    @FXML TextField distanceField, durationField;

    @FXML private void handleBackFromLogWorkout() throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("WorkoutJournal.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage)backFromLogWorkout.getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML private void handleRunning() {
        strength.setDisable(true);
        other.setDisable(true);
        skiing.setDisable(true);
    }

    @FXML private void handleStrength() {
        running.setDisable(true);
        other.setDisable(true);
        skiing.setDisable(true);
    }

    @FXML private void handleSkiing() {
        strength.setDisable(true);
        other.setDisable(true);
        running.setDisable(true);
    }

    @FXML private void handleOther() {
        strength.setDisable(true);
        running.setDisable(true);
        skiing.setDisable(true);
    }

    @FXML private void handleReset() {
        date.getEditor().clear();;
        strength.setDisable(false);
        running.setDisable(false);
        other.setDisable(false);
        skiing.setDisable(false);
        distanceField.clear();
        durationField.clear();;
    }

    String type;
    @FXML private void handleAddWorkout () {
        int dayOfMonth = date.getValue().getDayOfMonth();
        int month = date.getValue().getMonthValue();
        int year = date.getValue().getYear();

        for (Button button : buttons) {
            if (!button.isDisabled()){
                type = button.getText();
            }
        }

        double distance = Double.parseDouble(distanceField.getText());
        int duration = Integer.parseInt(durationField.getText());

        if (type.equals("Strength")) {
            workout = new Workout(dayOfMonth, month, year, type, duration);
        } else {
            workout = new Workout(dayOfMonth, month, year, type, distance, duration);
        }

        for (WorkoutYear workoutYear : workoutYearsList) {
            if (workoutYear.getYear() == year) {
                workoutYear.addWorkout(workout);
                break;
            }
            WorkoutYear newWorkoutYear = new WorkoutYear(workout.getYear());
            workoutYearsList.add(newWorkoutYear);
        }
        
        

    }





    
    
}
