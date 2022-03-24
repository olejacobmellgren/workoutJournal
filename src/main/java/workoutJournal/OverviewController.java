package workoutJournal;

import java.io.IOException;
import java.util.ArrayList;
//import java.net.URL;
import java.util.Calendar;
import java.util.List;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
//import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
//import javafx.scene.control.Spinner;
//import javafx.scene.control.SpinnerValueFactory;
import javafx.stage.Stage;

public class OverviewController { // implements Initializable

    private List<WorkoutYear> workoutYears;

    @FXML private Button backFromOverview, updateOverveiw;
    //@FXML private Spinner<Integer> monthsSpinner, yearsSpinner;
    @FXML private Label runningAmount, skiingAmount, strengthAmount, otherAmount, averageDistance, averageDuration;
    @FXML private TextField month, year;


    public void initialize(List<WorkoutYear> workoutYears) { //URL location, ResourceBundle resources
        this.workoutYears = workoutYears;

        //SpinnerValueFactory<Integer> monthsFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 12);
        //monthsFactory.setValue(Calendar.getInstance().get(Calendar.MONTH));
        //monthsSpinner.setValueFactory(monthsFactory); 
        //SpinnerValueFactory<Integer> yearsFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(2000, Calendar.getInstance().get(Calendar.YEAR));
        //yearsFactory.setValue(Calendar.getInstance().get(Calendar.YEAR));
        //yearsSpinner.setValueFactory(yearsFactory); 
    }

    @FXML private void handleBackFromOverview() throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("WorkoutJournal.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) backFromOverview.getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML private void handleUpdateOverview() {
        //int month = monthsSpinner.getValue();
        //int year = yearsSpinner.getValue();
        int monthChoice = Integer.valueOf(month.getText());
        int yearChoice = Integer.valueOf(year.getText());
        for (WorkoutYear workoutYear : workoutYears) { //TODO logworkoutcontroller er null, hvordan skal jeg hente listen?
            if (yearChoice == workoutYear.getYear()) {
                for (WorkoutMonth workoutMonth : workoutYear.getMonthsWithWorkouts()) {
                    if (monthChoice == workoutMonth.getMonth()) {
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
