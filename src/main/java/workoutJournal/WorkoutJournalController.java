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
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
//import javafx.scene.control.Spinner;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class WorkoutJournalController {

    // @FXML LogSleepController logSleepController;
    // @FXML LogWorkoutController logWorkoutController;
    // @FXML OverviewController overviewController;
    // @FXML Scene logWorkoutScene;
    // @FXML Scene overviewScene;
    // @FXML Scene logSleepScene;

    private static List<WorkoutYear> workoutYearsList = new ArrayList<>();
    private List<Integer> sleepList;
    private List<Integer> moodList;

    @FXML private Button logWorkout, logSleep, overview;

    //@FXML public void initialize() throws IOException{
    //    workoutYearsList = new ArrayList<>();
    //    sleepList = new ArrayList<>();
//
    //    FXMLLoader logWorkoutLoader = new FXMLLoader(getClass().getResource("LogWorkout.fxml"));
    //    logWorkoutScene = new Scene(logWorkoutLoader.load());
    //    logWorkoutController = logWorkoutLoader.getController();
//
    //    FXMLLoader overviewLoader = new FXMLLoader(getClass().getResource("Overview.fxml"));
    //    overviewScene = new Scene(overviewLoader.load());
    //    overviewController = overviewLoader.getController();
//
    //    FXMLLoader logSleepLoader = new FXMLLoader(getClass().getResource("LogSleep.fxml"));
    //    logSleepScene = new Scene(logSleepLoader.load());
    //    logSleepController = logSleepLoader.getController();
    //    
    //    logWorkoutController.initialize(workoutYearsList);
    //    overviewController.initialize(workoutYearsList);
    //    logSleepController.initialize(sleepList, moodList);
    //}


    //MainPageController

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

    //LogWorkoutController
    
    @FXML DatePicker date;
    @FXML Button running, strength, skiing, other, reset, addWorkout, backFromLogWorkout;
    @FXML TextField distanceField, durationField;

    private Workout workout; //TODO
    private String type;
    //public List<WorkoutYear> workoutYearsList = new ArrayList<>();
    //private List<Button> buttons = Arrays.asList(running, strength, skiing, other);


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
        date.getEditor().clear();;
        strength.setDisable(false);
        running.setDisable(false);
        other.setDisable(false);
        skiing.setDisable(false);
        distanceField.clear();
        durationField.clear();;
    }

    @FXML private void handleAddWorkout () {
        int dayOfMonth = date.getValue().getDayOfMonth();
        int month = date.getValue().getMonthValue();
        int year = date.getValue().getYear();

        double distance = 0;
        if (!type.equals("Strength")) {
            distance = Double.parseDouble(distanceField.getText());
        }
        int duration = Integer.parseInt(durationField.getText());

        if (type.equals("Strength")) {
            workout = new Workout(dayOfMonth, month, year, type, duration);
        }
        else {
            workout = new Workout(dayOfMonth, month, year, type, distance, duration);
        }

        for (WorkoutYear workoutYear : workoutYearsList) {
            System.out.println("Year=" + workoutYear.getYear());
            System.out.println("workoutYear=" + workoutYear.getYear());

            if (workoutYear.getYear() == year) {
                workoutYear.addWorkoutToYear(workout);
                handleReset();
                System.out.println(workoutYearsList); //
                return;
            }
        }
        WorkoutYear newWorkoutYear = new WorkoutYear(workout.getYear());
        workoutYearsList.add(newWorkoutYear);
        newWorkoutYear.addWorkoutToYear(workout);
        System.out.println(workoutYearsList); //
        handleReset();
    }

    //LogSleepController

    @FXML private Button backFromLogSleep, confirmSleep, confirmMood;
    @FXML private TextField sleepAmount;
    @FXML private RadioButton mood1, mood2, mood3, mood4, mood5;

    //public List<Integer> sleepList = new ArrayList<>();
    //public List<Integer> moodList = new ArrayList<>();
    
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

    //OverviewController

    WorkoutYear workoutYear;

    @FXML private Button backFromOverview, updateOverveiw;
    //@FXML private Spinner<Integer> monthsSpinner, yearsSpinner;
    @FXML private Label runningAmount, skiingAmount, strengthAmount, otherAmount, averageDistance, averageDuration;
    @FXML private TextField month, year;


    //@Override
    //public void initialize(URL location, ResourceBundle resources) {
    //    SpinnerValueFactory<Integer> monthsFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 12);
    //    monthsFactory.setValue(Calendar.getInstance().get(Calendar.MONTH));
    //    monthsSpinner.setValueFactory(monthsFactory); 
    //    SpinnerValueFactory<Integer> yearsFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(2000, Calendar.getInstance().get(Calendar.YEAR));
    //    yearsFactory.setValue(Calendar.getInstance().get(Calendar.YEAR));
    //    yearsSpinner.setValueFactory(yearsFactory); 
    //}

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
        System.out.println(workoutYearsList);
        for (WorkoutYear workoutYear : workoutYearsList) {
            System.out.println(workoutYear.getYear());
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
