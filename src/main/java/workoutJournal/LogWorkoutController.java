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
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LogWorkoutController {


    @FXML DatePicker date;
    @FXML Button running, strength, skiing, other, reset, addWorkout, backFromLogWorkout;
    @FXML TextField distanceField, durationField;

    private Workout workout; //TODO
    private String type;
    public List<WorkoutYear> workoutYears;
    //private List<Button> buttons = Arrays.asList(running, strength, skiing, other);

    public void initialize(List<WorkoutYear> workoutYears) {
        this.workoutYears = workoutYears;
    }

    @FXML private void handleBackFromLogWorkout() throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("WorkoutJournal.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage)backFromLogWorkout.getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML private void handleRunning() {
        type = "Running";
        strength.setDisable(true);
        other.setDisable(true);
        skiing.setDisable(true);
    }

    @FXML private void handleStrength() {
        type = "Strength";
        running.setDisable(true);
        other.setDisable(true);
        skiing.setDisable(true);
    }

    @FXML private void handleSkiing() {
        type = "Skiing";
        strength.setDisable(true);
        other.setDisable(true);
        running.setDisable(true);
    }

    @FXML private void handleOther() {
        type = "Other";
        strength.setDisable(true);
        running.setDisable(true);
        skiing.setDisable(true);
    }

    @FXML private void handleReset() {
        date.getEditor().clear();
        strength.setDisable(false);
        running.setDisable(false);
        other.setDisable(false);
        skiing.setDisable(false);
        distanceField.clear();
        durationField.clear();;
    }

    @FXML private void handleAddWorkout () {
        date.getEditor().clear();
        int dayOfMonth = date.getValue().getDayOfMonth();
        int month = date.getValue().getMonthValue();
        int year = date.getValue().getYear();

        double distance = Double.parseDouble(distanceField.getText());
        int duration = Integer.parseInt(durationField.getText());

        if (type.equals("Strength")) {
            workout = new Workout(dayOfMonth, month, year, type, duration);
        } else {
            workout = new Workout(dayOfMonth, month, year, type, distance, duration);
        }

        if (workoutYears.isEmpty()) {
            workoutYears.add(new WorkoutYear(workout.getYear()));
        }
        for (WorkoutYear workoutYear : workoutYears) {
            if (workoutYear.getYear() == year) {
                workoutYear.addWorkoutToYear(workout);
                break;
            }
            WorkoutYear newWorkoutYear = new WorkoutYear(workout.getYear());
            workoutYears.add(newWorkoutYear);
            handleReset();
        }
        
        

    }





    
    
}
