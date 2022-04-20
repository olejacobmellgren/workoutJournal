package workoutJournal;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.shape.CubicCurve;
import javafx.stage.Stage;

public class WorkoutJournalController {

    @FXML private Button logWorkout, logSleep, overview;

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
    
    private static List<WorkoutYear> workoutYearsList = new ArrayList<>();

    private Workout workout; //TODO
    private String type;
    //public List<WorkoutYear> workoutYearsList = new ArrayList<>();
    //private List<Button> buttons = Arrays.asList(running, strength, skiing, other);

    private void showErrorMessage(String errorMessage) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("ERROR");
        alert.setHeaderText("Something went wrong!");
        alert.setContentText(errorMessage);
        alert.showAndWait();
    }

    private void showConfirmedMessage(String confirmedMessage) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("SUCCESSFUL");
        alert.setHeaderText("Success!");
        alert.setContentText(confirmedMessage);
        alert.showAndWait();
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
        
        try {
            Double.parseDouble(distanceField.getText());
        } catch (NumberFormatException e) {
            if (e.getMessage().equals("empty String")) {
                showErrorMessage("Distance field is empty");
            } else {
                showErrorMessage("Distance must be number, not text");
            }
            return;
        }

        try {
            Double.parseDouble(durationField.getText());
        } catch (NumberFormatException e) {
            if (e.getMessage().equals("empty String")) {
                showErrorMessage("Duration field is empty");
            } else {
            showErrorMessage("Duration must be number, not text");
            }
            return;
        }
        
        double distance = Double.parseDouble(distanceField.getText());
        double duration = Double.parseDouble(durationField.getText());

        try {
            workout = new Workout(dayOfMonth, month, year, type, distance, duration);

            for (WorkoutYear workoutYear : workoutYearsList) {
                if (workoutYear.getYear() == year) {
                    workoutYear.addWorkoutToPeriod(workout);
                    showConfirmedMessage("You successfully added this workout:\n" + workout);
                    handleReset();
                    return;
                }
            }
            WorkoutYear newWorkoutYear = new WorkoutYear(workout.getYear());
            workoutYearsList.add(newWorkoutYear);
            newWorkoutYear.addWorkoutToPeriod(workout);
            showConfirmedMessage("You successfully added this workout:\n" + workout);
            handleReset();
        } catch (IllegalArgumentException e) {
            showErrorMessage(e.getMessage());
        }
    }
        

    //LogSleepController

    @FXML private Button backFromLogSleep, confirmSleep, confirmMood;
    @FXML private TextField sleepMonth, sleepYear, sleepAmount;
    @FXML private RadioButton mood1, mood2, mood3, mood4, mood5;
    @FXML private ToggleGroup mood;

    //private static List<Double> sleepList = new ArrayList<>();
    //private static List<Integer> moodList = new ArrayList<>();
    
    @FXML private void handleBackFromLogSleep() throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("WorkoutJournal.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) backFromLogSleep.getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML private void handleLogSleepAndMood() {

        try {
            Integer.parseInt(sleepMonth.getText());
            Integer.parseInt(sleepYear.getText());
        } catch (NumberFormatException e) {
            showErrorMessage("Month and Year should be a number");
            return;
        }

        try {

            double sleepHours = Double.parseDouble(sleepAmount.getText());
            RadioButton selectedButton = (RadioButton) mood.getSelectedToggle();
            int selectedMood = Integer.parseInt(selectedButton.getText());

            int month = Integer.parseInt(sleepMonth.getText());
            int year = Integer.parseInt(sleepYear.getText());

            for (WorkoutYear workoutYear : workoutYearsList) {
                if (workoutYear.getYear() == year) {
                    for (WorkoutMonth workoutMonth : workoutYear.getMonthsWithWorkouts()) {
                        if (workoutMonth.getMonth() == month) {
                            workoutMonth.addSleep(sleepHours);
                            workoutMonth.addMood(selectedMood);
                            showConfirmedMessage("You successfully added "
                            + sleepHours + " hours of sleep and a mood to " + month + "." + year);
                            sleepAmount.clear();
                            sleepYear.clear();
                            sleepMonth.clear();
                            selectedButton.setSelected(false);
                            System.out.println(workoutYearsList);
                            return;
                        }
                    }
                }
            }
            throw new IllegalArgumentException("You can only log sleep and mood for months containing workouts"); //TODO


        } catch (NumberFormatException e) {
            showErrorMessage("Hours of Sleep should be a number less than 24");
            return;
        } catch (IllegalArgumentException e) {
            showErrorMessage(e.getMessage());
            return;
        } catch (NullPointerException e) {
            showErrorMessage("You need to select a mood");
            return;
        }
    }


    //OverviewController

    WorkoutYear workoutYear;

    @FXML private Button backFromOverview, updateOverveiw, setGoal;
    @FXML private Label runningAmount, skiingAmount, strengthAmount, otherAmount,
                         averageDistance, averageDuration, averageSleep;
    @FXML private Label currentGoal;
    @FXML private TextField overviewMonth, overviewYear, distanceGoalField;
    @FXML private ProgressBar distanceProgress;
    @FXML private CubicCurve sad, lessSad, neutral, lessHappy, happy;

    private static double distanceGoal;
    private IWorkoutYearFileReading workoutYearsFileHandler = new WorkoutYearFileSupport();

    @FXML private void handleBackFromOverview() throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("WorkoutJournal.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) backFromOverview.getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML private void handleUpdateOverview() {
        happy.setVisible(false);
        lessHappy.setVisible(false);
        neutral.setVisible(false);
        lessSad.setVisible(false);
        sad.setVisible(false);
        currentGoal.setText(String.valueOf(distanceGoal));

        try {
            Integer.parseInt(overviewMonth.getText());
            Integer.parseInt(overviewYear.getText());
        } catch (NumberFormatException e) {
            showErrorMessage("Month and Year should be a number");
            return;
        }

        try {
            int monthChoice = Integer.valueOf(overviewMonth.getText());
            int yearChoice = Integer.valueOf(overviewYear.getText());
            for (WorkoutYear workoutYear : workoutYearsList) {
                if (yearChoice == workoutYear.getYear()) {
                    for (WorkoutMonth workoutMonth : workoutYear.getMonthsWithWorkouts()) {
                        if (monthChoice == workoutMonth.getMonth()) {
                            if (workoutMonth.getAverageMood() > 4.5) {
                                sad.setVisible(true);
                            } else if (workoutMonth.getAverageMood() > 3.5) {
                                lessSad.setVisible(true);
                            } else if (workoutMonth.getAverageMood() > 2.5) {
                                neutral.setVisible(true);
                            } else if (workoutMonth.getAverageMood() > 1.5) {
                                lessHappy.setVisible(true);
                            } else if (workoutMonth.getAverageMood() < 0) {
                                happy.setVisible(true);
                            }
                            averageSleep.setText(String.valueOf(workoutMonth.getAverageSleep()));
                            distanceProgress.setProgress(workoutMonth.getTotalDistance()/distanceGoal); //TODO
                            runningAmount.setText(String.valueOf(workoutMonth.getRunningAmount()));
                            skiingAmount.setText(String.valueOf(workoutMonth.getSkiingAmount()));
                            strengthAmount.setText(String.valueOf(workoutMonth.getStrengthAmount()));
                            otherAmount.setText(String.valueOf(workoutMonth.getOtherAmount()));
                            averageDistance.setText(String.valueOf(workoutMonth.getAverageDistance()));
                            averageDuration.setText(String.valueOf(workoutMonth.getAverageDuration()));
                            return;
                        }
                    }

                }
            }
        } catch (ArithmeticException e) {
            showErrorMessage("You need to set a distance goal");
        }
        showErrorMessage("You can only see overview for months containing workouts");
    }

    @FXML private void handleSaveToFile() {
        try {
            workoutYearsFileHandler.writeWorkoutYear("workoutYearsFile.txt", workoutYearsList);
            showConfirmedMessage("You successfully saved to workoutYearsFile.txt");
        } catch (IOException e) {
            showErrorMessage("Saving to file did not work, try again later");
        }
    }

    @FXML private void handleLoadFromFile() {
        try {
            workoutYearsList = workoutYearsFileHandler.readWorkoutYear("workoutYearsFile.txt");
            showConfirmedMessage("You successfully loaded from workoutYearsFile.txt");

        } catch (IOException e) {
            showErrorMessage("Loading from file did not work, try again later");
        }
    }

    @FXML private void handleSetGoal() {
        try {
            distanceGoal = Double.parseDouble(distanceGoalField.getText());
            if (distanceGoal < 0) {
                throw new IllegalArgumentException("Distance goal must be a positive number"); //TODO
            }
            currentGoal.setText(String.valueOf(distanceGoal) + " km");
            distanceGoalField.clear();
        } catch (NumberFormatException e) {
            showErrorMessage("Distance goal must be a number");
        } catch (IllegalArgumentException e) {
            showErrorMessage(e.getMessage());
        }
    }
    
}
