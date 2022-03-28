package workoutJournal;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class WorkoutTest {
    
    private Workout workout;

    private void checkWorkout(int dayOfMonth, int month, int year, String type, double distance, int duration) {
        assertEquals(dayOfMonth, workout.getDayOfMonth(), "Wrong day of workout");
        assertEquals(month, workout.getMonth(), "Wrong month of workout");
        assertEquals(year, workout.getYear(), "Wrong year of workout");
        assertEquals(type, workout.getType(), "Wrong type of workout");
        assertEquals(distance, workout.getDistance(), "Wrong distance of workout");
        assertEquals(duration, workout.getDuration(), "Wrong duration of workout");
    }

    @Test
    @DisplayName("Checks constructor")
    public void testConstructor() {

        workout = new Workout(20, 02, 2021, "Running", 5, 90);
        checkWorkout(20, 02, 2021, "Running", 5, 90);

        assertThrows(IllegalArgumentException.class, () -> {
            new Workout(20, 05, 2022, "Running", 5, 90);
        }, "Should not be able to log workout for the future");

        assertThrows(IllegalArgumentException.class, () -> {
            new Workout(35, 02, 2021, "Running", 5, 90);
        }, "Day of month should be between 01 and 31");

        assertThrows(IllegalArgumentException.class, () -> {
            new Workout(20, 13, 2021, "Running", 5, 90);
        }, "Month should be between 01 and 12");

        assertThrows(IllegalArgumentException.class, () -> {
            new Workout(20, 02, 1999, "Running", 5, 90);
        }, "Should not be able to log workout before year 2000");

        assertThrows(IllegalArgumentException.class, () -> {
            new Workout(20, 02, 2021, "Running", 1050, 90);
        }, "Should not be able to log workout over 1000 km");

        assertThrows(IllegalArgumentException.class, () -> {
            new Workout(20, 02, 2021, "Strength", 10, 90);
        }, "Should not be able to log workout of type 'Strength' with a distance");

        assertThrows(IllegalArgumentException.class, () -> {
            new Workout(20, 02, 2021, "Running", -10, 90);
        }, "Should not be able to log workout with negative distance");

        assertThrows(IllegalArgumentException.class, () -> {
            new Workout(20, 02, 2021, "Running", 10, -90);
        }, "Should not be able to log workout with negative duration");

        assertThrows(IllegalArgumentException.class, () -> {
            new Workout(20, 02, 2021, "Running", 10, 990);
        }, "Should not be able to log workout with duration over 8 hours");

        assertThrows(IllegalArgumentException.class, () -> {
            new Workout(20, 02, 2021, "Volleyball", 10, 990);
        }, "Should not be able to log workout with type other than 'Running', 'Strength', 'Skiing' or 'Other'");
        
        assertThrows(IllegalArgumentException.class, () -> {
            new Workout(20, 02, 2021, "Strength", 10, 20);
        }, "Distance should be 0 when type is 'Strength'");
    }
}
