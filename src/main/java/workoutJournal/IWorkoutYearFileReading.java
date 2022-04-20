package workoutJournal;

import java.io.File;
import java.io.IOException;
import java.util.List;

public interface IWorkoutYearFileReading {

    List<WorkoutYear> readWorkoutYear(String filename) throws IOException;

    void writeWorkoutYear(String filename, List<WorkoutYear> workoutYearsList) throws IOException;  
    
    File getFile(String filename);
}
