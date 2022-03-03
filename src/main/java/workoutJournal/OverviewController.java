package workoutJournal;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.stage.Stage;

public class OverviewController implements Initializable{

    @FXML private Button backFromOverview;
    @FXML private Spinner<String> monthsSpinner;

    private final ObservableList<String> months = FXCollections.observableArrayList("January", "February", "Mars", "April", "May", "June", "July", "August", "September", "October", "November", "December");

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        SpinnerValueFactory<String> monthsFactory = new SpinnerValueFactory.ListSpinnerValueFactory<>(months);
        monthsFactory.setValue("January");
        monthsSpinner.setValueFactory(monthsFactory); 
    }

    @FXML private void handleBackFromOverview() throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("WorkoutJournal.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) backFromOverview.getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    
    
}
