package workoutJournal;

import java.util.Calendar;

public abstract class WorkoutPeriod {

    protected int year;

    public WorkoutPeriod(int year) {
        checkYear(year);
        this.year = year;
    }

    protected void checkYear(int year) {
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

    public abstract void addWorkoutToPeriod(Workout workout);
    
    
}
