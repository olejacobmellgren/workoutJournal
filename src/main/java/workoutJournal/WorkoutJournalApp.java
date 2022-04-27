package workoutJournal;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class WorkoutJournalApp extends Application{


    public static void main(String[] args) {
        Application.launch(args);
    }
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent parent = FXMLLoader.load(getClass().getResource("WorkoutJournal.fxml"));
        stage.setScene(new Scene(parent));
        stage.setTitle("Workout Journal");
        stage.show();
    }  
}
