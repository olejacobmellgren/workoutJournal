package workoutJournal;

import java.io.IOException;
import java.net.URL;
import java.util.Calendar;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.stage.Stage;

public class OverviewController implements Initializable{

    LogWorkoutController logWorkoutController; //Ønsker å hente listen med år slik at jeg kan hente ut måneder for et år
    WorkoutYear workoutYear;

    @FXML private Button backFromOverview, updateOverveiw;
    @FXML private Spinner<Integer> monthsSpinner, yearsSpinner;
    @FXML private Label runningAmount, skiingAmount, strengthAmount, otherAmount, averageDistance, averageDuration;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        SpinnerValueFactory<Integer> monthsFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 12);
        monthsFactory.setValue(Calendar.getInstance().get(Calendar.MONTH));
        monthsSpinner.setValueFactory(monthsFactory); 
        SpinnerValueFactory<Integer> yearsFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(2000, Calendar.getInstance().get(Calendar.YEAR));
        yearsFactory.setValue(Calendar.getInstance().get(Calendar.YEAR));
        yearsSpinner.setValueFactory(yearsFactory); 
    }

    @FXML private void handleBackFromOverview() throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("WorkoutJournal.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) backFromOverview.getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML private void handleUpdateOverview() {
        int month = monthsSpinner.getValue();
        int year = yearsSpinner.getValue();
        for (WorkoutYear workoutYear : logWorkoutController.workoutYearsList) {
            if (year == workoutYear.getYear()) {
                for (WorkoutMonth workoutMonth : workoutYear.getMonthsWithWorkouts()) {
                    if (month == workoutMonth.getMonth()) {
                        runningAmount.setText(String.valueOf(workoutMonth.getRunningAmount()));
                        skiingAmount.setText(String.valueOf(workoutMonth.getSkiingAmount()));
                        strengthAmount.setText(String.valueOf(workoutMonth.getStrengthAmount()));
                        otherAmount.setText(String.valueOf(workoutMonth.getOtherAmount()));
                        averageDistance.setText(String.valueOf(workoutMonth.getAverageDistance()));
                        averageDuration.setText(String.valueOf(workoutMonth.getAverageDuration()));
                    }
                }
                
            }
        }
    }

    
    
}
