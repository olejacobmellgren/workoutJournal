package workoutJournal;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class WorkoutMonthTest {

    private WorkoutMonth workoutMonth;
    private WorkoutMonth workoutMonth2;
    private Workout workout1;
    private Workout workout2;
    private Workout workout3;
    private Workout workout4;
    private Workout workout5;
    private Workout workout6;
    private Workout workout7;
    private Workout workout8;
    private Workout workout9;



    @BeforeEach
    public void setup() {

        workoutMonth = new WorkoutMonth(01, 2021);

        workout1 = new Workout(01, 01, 2021, "Running", 10, 40);
        workout2 = new Workout(02, 01, 2021, "Strength", 0, 20);
        workout3 = new Workout(03, 01, 2021, "Skiing", 20, 80);
        workout4 = new Workout(19, 01, 2021, "Skiing", 30, 120);
        workout5 = new Workout(25, 01, 2021, "Running", 4, 20);
        workout6 = new Workout(30, 01, 2021, "Running", 6, 20);

        workout7 = new Workout(10, 03, 2021, "Other", 20, 40);
        workout8 = new Workout(10, 01, 2019, "Running", 20, 40);
        workout9 = new Workout(15, 01, 2021, "Other", 20, 40);

        workoutMonth.addWorkoutToMonth(workout1);
        workoutMonth.addWorkoutToMonth(workout2);
        workoutMonth.addWorkoutToMonth(workout3);
        workoutMonth.addWorkoutToMonth(workout4);
        
    }

    private void checkWorkoutMonth(int month, int year) {
        assertEquals(month, workoutMonth.getMonth(), "Wrong month for month of workout");
        assertEquals(year, workoutMonth.getYear(), "Wrong year for month of workout");
    }

    @Test
    @DisplayName("Checks constructor")
    public void testConstructor() {

        checkWorkoutMonth(01, 2021);

        assertThrows(IllegalArgumentException.class, () -> {
            new WorkoutMonth(05, 2022);
        }, "Should not be able to log future workout-month");

        assertThrows(IllegalArgumentException.class, () -> {
            new WorkoutMonth(13, 2021);
        }, "Month should be between 01 and 12");

        assertThrows(IllegalArgumentException.class, () -> {
            new WorkoutMonth(01, 1999);
        }, "Should not be able to create month before year 2000");
    }

    @Test
    @DisplayName("Checks that you can add workout to workout-month with correct month")
    public void testAddWorkoutToMonth() {

        assertEquals(workout1, workoutMonth.getWorkouts().get(0));
        assertEquals(workout2, workoutMonth.getWorkouts().get(1));
        assertEquals(workout3, workoutMonth.getWorkouts().get(2));
        assertEquals(workout4, workoutMonth.getWorkouts().get(3));

        workoutMonth.addWorkoutToMonth(workout5);
        assertEquals(workout5, workoutMonth.getWorkouts().get(4));

        workoutMonth.addWorkoutToMonth(workout6);
        assertEquals(workout6, workoutMonth.getWorkouts().get(5));

        assertThrows(IllegalArgumentException.class, () -> {
            workoutMonth.addWorkoutToMonth(workout7);
        }, "Should not be able to add workout with month different from month assosiated with workout-month");

        assertThrows(IllegalArgumentException.class, () -> {
            workoutMonth.addWorkoutToMonth(workout8);
        }, "Should not be able to add workout with year different from year assosiated with workout-month");
    }

    @Test
    @DisplayName("Checks average distance is correct before and after workouts added")
    public void testGetAverageDistance() {
        
        assertEquals(20, workoutMonth.getAverageDistance());

        workoutMonth.addWorkoutToMonth(workout5);
        assertEquals(16, workoutMonth.getAverageDistance());

        workoutMonth.addWorkoutToMonth(workout6);
        assertEquals(14, workoutMonth.getAverageDistance()); 

        workoutMonth2 = new WorkoutMonth(01, 2021);
        assertEquals(0, workoutMonth2.getAverageDistance()); 
    }

    @Test
    @DisplayName("Checks average duration is correct before and after workouts added")
    public void testGetAverageDuration() {
        
        assertEquals(65, workoutMonth.getAverageDuration());

        workoutMonth.addWorkoutToMonth(workout5);
        assertEquals(56, workoutMonth.getAverageDuration());

        workoutMonth.addWorkoutToMonth(workout6);
        assertEquals(50, workoutMonth.getAverageDuration()); 

        workoutMonth2 = new WorkoutMonth(06, 2021);
        assertEquals(0, workoutMonth2.getAverageDuration()); 
    }

    @Test
    @DisplayName("Checks correct amount of workout of type 'Running'")
    public void testGetRunningAmount() {
        
        assertEquals(1, workoutMonth.getRunningAmount());

        workoutMonth.addWorkoutToMonth(workout5);
        assertEquals(2, workoutMonth.getRunningAmount());

        workoutMonth.addWorkoutToMonth(workout6);
        assertEquals(3, workoutMonth.getRunningAmount());

        workoutMonth2 = new WorkoutMonth(06, 2021);
        assertEquals(0, workoutMonth2.getRunningAmount(), "Amount of workouts of type 'Running' should be 0 in an empty month"); 
    }

    @Test
    @DisplayName("Checks correct amount of workout of type 'Skiing'")
    public void testGetSkiingAmount() {
        
        assertEquals(2, workoutMonth.getSkiingAmount());

        workoutMonth.addWorkoutToMonth(workout5);
        workoutMonth.addWorkoutToMonth(workout6);
        assertEquals(2, workoutMonth.getSkiingAmount());


        workoutMonth2 = new WorkoutMonth(06, 2021);
        assertEquals(0, workoutMonth2.getSkiingAmount(), "Amount of workouts of type 'Skiing' should be 0 in an empty month"); 
    }

    @Test
    @DisplayName("Checks correct amount of workout of type 'Strength'")
    public void testGetStrengthAmount() {
        
        assertEquals(1, workoutMonth.getStrengthAmount());

        workoutMonth.addWorkoutToMonth(workout5);
        workoutMonth.addWorkoutToMonth(workout6);
        assertEquals(1, workoutMonth.getStrengthAmount());


        workoutMonth2 = new WorkoutMonth(06, 2021);
        assertEquals(0, workoutMonth2.getStrengthAmount(), "Amount of workouts of type 'Strength' should be 0 in an empty month"); 
    }

    @Test
    @DisplayName("Checks correct amount of workout of type 'Other'")
    public void testGetOtherAmount() {
        
        assertEquals(0, workoutMonth.getOtherAmount());

        workoutMonth.addWorkoutToMonth(workout5);
        workoutMonth.addWorkoutToMonth(workout6);
        assertEquals(0, workoutMonth.getOtherAmount());

        workoutMonth.addWorkoutToMonth(workout9);
        assertEquals(1, workoutMonth.getOtherAmount());

        workoutMonth2 = new WorkoutMonth(06, 2021);
        assertEquals(0, workoutMonth2.getOtherAmount(), "Amount of workouts of type 'Other' should be 0 in an empty month"); 
    }
    
}
