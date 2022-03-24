package workoutJournal;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class WorkoutTest {
    
    private Workout workout;

    private void checkWorkoutWithDistance(int dayOfMonth, int month, int year, String type, double distance, int duration) {
        Assertions.assertEquals(dayOfMonth, workout.getDayOfMonth(), "Wrong day of workout");
        Assertions.assertEquals(month, workout.getMonth(), "Wrong month of workout");
        Assertions.assertEquals(year, workout.getYear(), "Wrong year of workout");
        Assertions.assertEquals(type, workout.getType(), "Wrong type of workout");
        Assertions.assertEquals(distance, workout.getDistance(), "Wrong distance of workout");
        Assertions.assertEquals(duration, workout.getDuration(), "Wrong duration of workout");
    }

    private void checkWorkoutWithoutDistance(int dayOfMonth, int month, int year, String type, int duration) {
        Assertions.assertEquals(dayOfMonth, workout.getDayOfMonth(), "Wrong day of workout");
        Assertions.assertEquals(month, workout.getMonth(), "Wrong month of workout");
        Assertions.assertEquals(year, workout.getYear(), "Wrong year of workout");
        Assertions.assertEquals("Strength", workout.getType(), "Wrong type of workout");
        Assertions.assertEquals(duration, workout.getDuration(), "Wrong duration of workout");
    }

    @Test
    @DisplayName("Checks that constructor WITH distance is correct")
    public void testConstructorWithDistance() {

        workout = new Workout(20, 02, 2021, "Running", 5, 90);
        checkWorkoutWithDistance(20, 02, 2021, "Running", 5, 90);

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new Workout(20, 05, 2022, "Running", 5, 90);
        }, "Should not be able to log workout for the future");

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new Workout(35, 02, 2021, "Running", 5, 90);
        }, "Should not be able to log workout of 35. day of a month");

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new Workout(20, 13, 2021, "Running", 5, 90);
        }, "Should not be able to log workout in the 13. month");

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new Workout(20, 02, 1999, "Running", 5, 90);
        }, "Should not be able to log workout before year 2000");

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new Workout(20, 02, 2021, "Running", 1050, 90);
        }, "Should not be able to log workout over 1000 km");

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new Workout(20, 02, 2021, "Strength", 10, 90);
        }, "Should not be able to log workout of type 'Strength' with a distance");

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new Workout(20, 02, 2021, "Running", -10, 90);
        }, "Should not be able to log workout with negative distance");

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new Workout(20, 02, 2021, "Running", 10, -90);
        }, "Should not be able to log workout with negative duration");

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new Workout(20, 02, 2021, "Running", 10, 990);
        }, "Should not be able to log workout with duration over 8 hours");

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new Workout(20, 02, 2021, "Volleyball", 10, 990);
        }, "Should not be able to log workout with type other than 'Running', 'Strength', 'Skiing' or 'Other'"); 
    }


    @Test
    @DisplayName("Checks the things that are different in the constructor WITHOUT distance")
    public void testConstructorWithoutDistance() {

        workout = new Workout(20, 02, 2021, "Strength", 90);
        checkWorkoutWithoutDistance(20, 02, 2021, "Strength", 90);

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new Workout(20, 02, 2021, "Running", 90);
        }, "Should not be able to log workout of type 'Running' without a distance");

    }
}
