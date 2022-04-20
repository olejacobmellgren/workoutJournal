package workoutJournal;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class WorkoutYearTest {

    private WorkoutYear workoutYear;
    private WorkoutMonth workoutMonth1;
    private WorkoutMonth workoutMonth2;
    private WorkoutMonth workoutMonth3;
    private WorkoutMonth workoutMonth4;
    private Workout workout1;
    private Workout workout2;
    private Workout workout3;
    private Workout workout4;
    private Workout workout5;


    @BeforeEach
    public void setup() {

        workoutYear = new WorkoutYear(2021);
        workoutMonth1 = new WorkoutMonth(01, 2021);
        workoutMonth2 = new WorkoutMonth(03, 2021);
        workoutMonth3 = new WorkoutMonth(05, 2021);

        workoutMonth4 = new WorkoutMonth(02, 2020);

        workout1 = new Workout(01, 01, 2021, "Running", 10, 40);
        workout2 = new Workout(02, 01, 2021, "Strength", 0, 20);
        workout3 = new Workout(03, 03, 2021, "Skiing", 20, 80);
        workout4 = new Workout(19, 05, 2021, "Other", 30, 120);  

        workout5 = new Workout(10, 03, 2022, "Other", 20, 40);
    }

    private void checkWorkoutYear(int year) {
        assertEquals(year, workoutYear.getYear(), "Wrong year");
    }

    @Test
    @DisplayName("Checks constructor")
    public void testConstructor() {

        checkWorkoutYear(2021);

        assertThrows(IllegalArgumentException.class, () -> {
            new WorkoutYear(2024);
        }, "IllegalArgumentException should be thrown when trying to log workouts in a future year");

        assertThrows(IllegalArgumentException.class, () -> {
            new WorkoutYear(1997);
        }, "IllegalArgumentException should be thrown when trying to log workouts year before year 2000");
    }

    @Test
    @DisplayName("Checks that you can add month in a year to correct year")
    public void testAddMonth() {

        workoutYear.addMonth(workoutMonth1);
        assertEquals(workoutMonth1, workoutYear.getMonthsWithWorkouts().get(0), "Month did not get added");
        workoutYear.addMonth(workoutMonth2);
        assertEquals(workoutMonth2, workoutYear.getMonthsWithWorkouts().get(1), "Month did not get added");
        workoutYear.addMonth(workoutMonth3);
        assertEquals(workoutMonth3, workoutYear.getMonthsWithWorkouts().get(2), "Month did not get added");
        

        assertThrows(IllegalArgumentException.class, () -> {
            workoutYear.addMonth(workoutMonth4);;
        }, "IllegalArgumentException should be thrown when trying to add month to wrong year");
    } 
    
    @Test
    @DisplayName("Checks that you can add workout to workout-year with correct month and year")
    public void testAddWorkoutToPeriod() {

        workoutYear.addWorkoutToPeriod(workout1);
        assertEquals(workout1, workoutYear.getMonthsWithWorkouts().get(0).getWorkouts().get(0), "Workout did not get added to January 2021");
        workoutYear.addWorkoutToPeriod(workout2);
        assertEquals(workout2, workoutYear.getMonthsWithWorkouts().get(0).getWorkouts().get(1), "Workout did not get added to January 2021");
        workoutYear.addWorkoutToPeriod(workout3);
        assertEquals(workout3, workoutYear.getMonthsWithWorkouts().get(1).getWorkouts().get(0), "Workout did not get added to March 2021");
        workoutYear.addWorkoutToPeriod(workout4);
        assertEquals(workout4, workoutYear.getMonthsWithWorkouts().get(2).getWorkouts().get(0), "Workout did not get added to May 2021");

        assertThrows(IllegalArgumentException.class, () -> {
            workoutYear.addWorkoutToPeriod(workout5);
        }, "IllegalArgumentException should be thrown when trying to add workout with year different from year assosiated with workout-year");
    }
    
}
