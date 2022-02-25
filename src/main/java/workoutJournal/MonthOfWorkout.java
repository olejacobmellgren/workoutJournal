package workoutJournal;

import java.util.Calendar;
import java.util.List;

public class MonthOfWorkout {

    private int year;
    private int month;
    private List<Workout> workoutList;

    public MonthOfWorkout(int year, int month) {
        checkYear(year);
        this.year = year;
        checkMonth(month);
        this.month = month;
    }

    private void checkYear(int year) {
        if (year < 2000) {
            throw new IllegalArgumentException("Cannot log workout before year 2000");
        }
        int currentYear = Calendar.getInstance().get(Calendar.YEAR);
        if (year > currentYear) {
            throw new IllegalArgumentException("Cannot log workout for the future");
        }
    }

    public int getYear() {
        return year;
    }

    private void checkMonth(int month) {
        if (month < 1 || month > 12) {
            throw new IllegalArgumentException("Month is number between 01 and 12");
        }
    }

    public int getMonth() {
        return month;
    }

    public void addWorkout(Workout workout) {
        workoutList.add(workout);
    } 
    
}
