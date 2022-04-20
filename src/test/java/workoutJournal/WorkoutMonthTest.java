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

        workoutMonth.addWorkoutToPeriod(workout1);
        workoutMonth.addWorkoutToPeriod(workout2);
        workoutMonth.addWorkoutToPeriod(workout3);
        workoutMonth.addWorkoutToPeriod(workout4);  
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
        }, "IllegalArgumentException should be thrown when trying to log workouts in a future workout-month");

        assertThrows(IllegalArgumentException.class, () -> {
            new WorkoutMonth(13, 2021);
        }, "IllegalArgumentException should be thrown when month not between 01 and 12");

        assertThrows(IllegalArgumentException.class, () -> {
            new WorkoutMonth(01, 1999);
        }, "IllegalArgumentException should be thrown when trying to log workouts in a month before year 2000");
    }

    @Test
    @DisplayName("Checks that you can add workout to workout-month with correct month")
    public void testAddWorkoutToPeriod() {

        assertEquals(workout1, workoutMonth.getWorkouts().get(0), "Workout did not get added");
        assertEquals(workout2, workoutMonth.getWorkouts().get(1), "Workout did not get added");
        assertEquals(workout3, workoutMonth.getWorkouts().get(2), "Workout did not get added");
        assertEquals(workout4, workoutMonth.getWorkouts().get(3), "Workout did not get added");

        workoutMonth.addWorkoutToPeriod(workout5);
        assertEquals(workout5, workoutMonth.getWorkouts().get(4));

        workoutMonth.addWorkoutToPeriod(workout6);
        assertEquals(workout6, workoutMonth.getWorkouts().get(5));

        assertThrows(IllegalArgumentException.class, () -> {
            workoutMonth.addWorkoutToPeriod(workout7);
        }, "IllegalArgumentException should be thrown when trying to add workout with month different from month assosiated with workout-month");

        assertThrows(IllegalArgumentException.class, () -> {
            workoutMonth.addWorkoutToPeriod(workout8);
        }, "IllegalArgumentException should be thrown when trying to add workout with year different from year assosiated with workout-month");
    }

    @Test
    @DisplayName("Checks average distance is correct before and after workouts added")
    public void testGetAverageDistance() {
        
        assertEquals(20, workoutMonth.getAverageDistance(), "Average distance for workout 1, 2, 3 and 4 should be 20 (workout 2 does not count)");

        workoutMonth.addWorkoutToPeriod(workout5);
        assertEquals(16, workoutMonth.getAverageDistance());

        workoutMonth.addWorkoutToPeriod(workout6);
        assertEquals(14, workoutMonth.getAverageDistance()); 

        workoutMonth2 = new WorkoutMonth(01, 2021);
        assertEquals(0, workoutMonth2.getAverageDistance(), "Average distance should be 0 in an empty month"); 
    }

    @Test
    @DisplayName("Checks average duration is correct before and after workouts added")
    public void testGetAverageDuration() {
        
        assertEquals(65, workoutMonth.getAverageDuration(),"Average duration for workout 1, 2, 3 and 4 should be 65");

        workoutMonth.addWorkoutToPeriod(workout5);
        assertEquals(56, workoutMonth.getAverageDuration());

        workoutMonth.addWorkoutToPeriod(workout6);
        assertEquals(50, workoutMonth.getAverageDuration()); 

        workoutMonth2 = new WorkoutMonth(06, 2021);
        assertEquals(0, workoutMonth2.getAverageDuration(), "Average duration should be 0 in an empty month"); 
    }

    @Test
    @DisplayName("Checks correct amount of workout of type 'Running'")
    public void testGetRunningAmount() {
        
        assertEquals(1, workoutMonth.getRunningAmount(), "Only workout 1 is of type 'Running' for the first four workouts");

        workoutMonth.addWorkoutToPeriod(workout5);
        assertEquals(2, workoutMonth.getRunningAmount());

        workoutMonth.addWorkoutToPeriod(workout6);
        assertEquals(3, workoutMonth.getRunningAmount());

        workoutMonth2 = new WorkoutMonth(06, 2021);
        assertEquals(0, workoutMonth2.getRunningAmount(), "Amount of workouts of type 'Running' should be 0 in an empty month"); 
    }

    @Test
    @DisplayName("Checks correct amount of workout of type 'Skiing'")
    public void testGetSkiingAmount() {
        
        assertEquals(2, workoutMonth.getSkiingAmount(), "Workout 3 and 4 are of type 'Skiing'");

        workoutMonth.addWorkoutToPeriod(workout5);
        workoutMonth.addWorkoutToPeriod(workout6);
        assertEquals(2, workoutMonth.getSkiingAmount());


        workoutMonth2 = new WorkoutMonth(06, 2021);
        assertEquals(0, workoutMonth2.getSkiingAmount(), "Amount of workouts of type 'Skiing' should be 0 in an empty month"); 
    }

    @Test
    @DisplayName("Checks correct amount of workout of type 'Strength'")
    public void testGetStrengthAmount() {
        
        assertEquals(1, workoutMonth.getStrengthAmount(), "Workout 2 is of type 'Strength'");

        workoutMonth.addWorkoutToPeriod(workout5);
        workoutMonth.addWorkoutToPeriod(workout6);
        assertEquals(1, workoutMonth.getStrengthAmount());


        workoutMonth2 = new WorkoutMonth(06, 2021);
        assertEquals(0, workoutMonth2.getStrengthAmount(), "Amount of workouts of type 'Strength' should be 0 in an empty month"); 
    }

    @Test
    @DisplayName("Checks correct amount of workout of type 'Other'")
    public void testGetOtherAmount() {
        
        assertEquals(0, workoutMonth.getOtherAmount(), "None of the workout 1, 2, 3 or 4 are of type 'Other'");

        workoutMonth.addWorkoutToPeriod(workout5);
        workoutMonth.addWorkoutToPeriod(workout6);
        assertEquals(0, workoutMonth.getOtherAmount());

        workoutMonth.addWorkoutToPeriod(workout9);
        assertEquals(1, workoutMonth.getOtherAmount());

        workoutMonth2 = new WorkoutMonth(06, 2021);
        assertEquals(0, workoutMonth2.getOtherAmount(), "Amount of workouts of type 'Other' should be 0 in an empty month"); 
    }

    @Test
    @DisplayName("Checks that total distance is correct for month")
    public void testGetTotalDistance() {

        assertEquals(60, workoutMonth.getTotalDistance(), "Total distance is 60 for workout 1-4");

        workoutMonth.addWorkoutToPeriod(workout5);
        assertEquals(64, workoutMonth.getTotalDistance());

        workoutMonth.addWorkoutToPeriod(workout6);
        assertEquals(70, workoutMonth.getTotalDistance());
    }

    @Test
    @DisplayName("Checks that you can only add valid hours of sleep")
    public void testAddSleep() {

        workoutMonth.addSleep(7);
        workoutMonth.addSleep(12);

        assertThrows(IllegalArgumentException.class, () -> {
            workoutMonth.addSleep(26);
        }, "IllegalArgumentException should be thrown when trying to add sleep longer than 24 hours");

        assertThrows(IllegalArgumentException.class, () -> {
            workoutMonth.addSleep(-6);
        }, "IllegalArgumentException should be thrown when trying to add negative hours of sleep");
    }
    
    @Test
    @DisplayName("Checks that you can only add valid mood")
    public void testAddMood() {

        workoutMonth.addMood(4);
        workoutMonth.addMood(1);

        assertThrows(IllegalArgumentException.class, () -> {
            workoutMonth.addMood(10);
        }, "IllegalArgumentException should be thrown when trying to add mood higher than 5");

        assertThrows(IllegalArgumentException.class, () -> {
            workoutMonth.addMood(-6);
        }, "IllegalArgumentException should be thrown when trying to add negative mood");
    }   

    @Test
    @DisplayName("Checks that average hours of sleep is correct")
    public void testAverageSleep() {

        workoutMonth.addSleep(8);
        workoutMonth.addSleep(2);
        assertEquals(5, workoutMonth.getAverageSleep());
        workoutMonth.addSleep(11);
        assertEquals(7, workoutMonth.getAverageSleep());
    }

    @Test
    @DisplayName("Checks that average hours of sleep is correct")
    public void testAverageMood() {

        workoutMonth.addMood(4);
        workoutMonth.addMood(4);
        assertEquals(4, workoutMonth.getAverageMood());
        workoutMonth.addMood(1);
        assertEquals(3, workoutMonth.getAverageMood());
    }
}
