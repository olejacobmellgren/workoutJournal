package workoutJournal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)

public class WorkoutYearFileSupportTest {

    private static final String validWorkoutYearsFileContent = """
        Year: 2021
        Month; 1;2021
        1;1;2021,	Running,     50.0,	10.0
        2;1;2021,	Strength,     0.0,	20.0
        Sleep, 8.0
        Mood, 4, 2
        
        Month; 2;2021
        3;2;2021,	Other,     30.0,	30.0
        Sleep
        Mood, 3
        
        Year: 2022
        Month; 3;2022
        5;3;2022,	Running,     10.0,	40.0
        Sleep, 10.0, 12.0
        Mood""".replaceAll("\\R", System.getProperty("line.separator"));

    private static final String invalidWorkoutYearsFileContent = """
        Year: 2021 Month; 1;2021
        1;1;2021,	Running,     50.0,	10.0
        2;1;2021,	Strength,     0.0,	20.0
        Sleep: 8 Mood: 4, 2, 
        //
        Month; 2;2021
        3;2;2021,	Other,     30.0,	30.0
        Sleep: 
        Mood: 3, 
        
        Year: 2022
        Month; 3;2022, 5;3;2022,	Running,     10.0,	40.0
        Sleep: 10, 12
        Mood:
        
        """.replaceAll("\\R", System.getProperty("line.separator"));

    private IWorkoutYearFileReading getWorkoutYearFileSupport() {
        return new WorkoutYearFileSupport();
    }

    private List<WorkoutYear> getFilledListOfWorkoutYears() {
        List<WorkoutYear> workoutYearsList = new ArrayList<>();

        Workout workout1 = new Workout(01, 01, 2021, "Running", 50, 10);
        Workout workout2 = new Workout(02, 01, 2021, "Strength", 0, 20);
        Workout workout3 = new Workout(03, 02, 2021, "Other", 30, 30);
        Workout workout4 = new Workout(05, 03, 2022, "Running", 10, 40);

        WorkoutMonth workoutMonth = new WorkoutMonth(01, 2021);
        WorkoutMonth workoutMonth2 = new WorkoutMonth(02, 2021);
        WorkoutMonth workoutMonth3 = new WorkoutMonth(03, 2022);

        workoutMonth.addWorkoutToPeriod(workout1);
        workoutMonth.addWorkoutToPeriod(workout2);
        workoutMonth.addMood(4);
        workoutMonth.addMood(2);
        workoutMonth.addSleep(8);
        workoutMonth2.addWorkoutToPeriod(workout3);
        workoutMonth2.addMood(3);
        workoutMonth3.addWorkoutToPeriod(workout4);
        workoutMonth3.addSleep(10);
        workoutMonth3.addSleep(12);

        WorkoutYear workoutYear = new WorkoutYear(2021);
        WorkoutYear workoutYear2 = new WorkoutYear(2022);

        workoutYear.addMonth(workoutMonth);
        workoutYear.addMonth(workoutMonth2);
        workoutYear2.addMonth(workoutMonth3);

        workoutYearsList.add(workoutYear);
        workoutYearsList.add(workoutYear2);
        return workoutYearsList;
    }

    @BeforeAll
    public void setup() throws IOException {
        PrintWriter writer = new PrintWriter(new File(WorkoutYearFileSupport.class.getResource("workoutYearsFiles/").getFile() + "validFile.txt")); // new File(WorkoutYearFileSupport.class.getResource("resources/").getFile() + filename + ".txt")
        writer.write(validWorkoutYearsFileContent);
        writer.close();
        writer = new PrintWriter(new File(WorkoutYearFileSupport.class.getResource("workoutYearsFiles/").getFile() + "invalidFile.txt")); // new File(WorkoutYearFileSupport.class.getResource("resources/").getFile() + "invalidFile.txt")
        writer.write(invalidWorkoutYearsFileContent);
        writer.close();
    }

    @Test
    public void testReadWorkoutYearsFile() throws IOException {
        List<WorkoutYear> expectedWorkoutYearsList = getFilledListOfWorkoutYears();
        List<WorkoutYear> actualWorkoutYearsList = getWorkoutYearFileSupport().readWorkoutYears("validFile");

        try {

            for (int i = 0; i < expectedWorkoutYearsList.size(); i++) {
                assertEquals(expectedWorkoutYearsList.get(i), actualWorkoutYearsList.get(i));   
            }
        } catch (IndexOutOfBoundsException e){
            fail("Actual list contains wrong amount of workout-years");

        }
    }

    @Test
    public void testReadInvalidWorkoutYearsFile() throws IOException {

        assertThrows(IllegalArgumentException.class, () -> {
            getWorkoutYearFileSupport().readWorkoutYears("invalidFile");
        }, "IllegalArgumentException should be thrown when trying to read from invalid file");
    }

    @Test
    public void testLoadNonExistingFile() {
        assertThrows(
                IOException.class,
                () -> getWorkoutYearFileSupport().readWorkoutYears("non_existing_file"),
                "IOException should be thrown if file does not exist!");
    }

    @Test
    public void testWriteWorkoutYearsFile() throws IOException {
        getWorkoutYearFileSupport().writeWorkoutYears("newFile", getFilledListOfWorkoutYears());
        String expectedString = validWorkoutYearsFileContent + System.getProperty("line.separator");
        BufferedReader reader = new BufferedReader(new FileReader(WorkoutYearFileSupportTest.class.getResource("workoutYearsFiles/").getFile() + "newFile.txt"));
        String actualString = "";
        String str = reader.readLine();
        while (str != null) {
            actualString += str + "\n";
            str = reader.readLine();
        }
        reader.close();
    
        assertEquals(expectedString, actualString.replaceAll("\\R", System.getProperty("line.separator")), "Content of files are not the same");  
    }

    @AfterAll
    public void teardown() {
        getWorkoutYearFileSupport().getFile("validFile").delete();
        getWorkoutYearFileSupport().getFile("invalidFile").delete();
        getWorkoutYearFileSupport().getFile("newFile").delete();

    }
}
