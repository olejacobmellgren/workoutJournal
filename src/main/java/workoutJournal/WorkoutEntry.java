package workoutJournal;

import java.util.Calendar;

public class WorkoutEntry {

    private int year;
    private int month;
    private int dayOfMonth;
    private int distance;
    private int duration;
    private String type;

    public WorkoutEntry(int year, int month, int dayOfMonth, int distance, int duration, String type) {
        checkYear(year);
        this.year = year;
        checkMonth(month);
        this.month = month;
        checkDayOfMonth(dayOfMonth);
        this.dayOfMonth = dayOfMonth;
        checkDistance(distance);
        this.distance = distance;
        checkDuration(duration);
        this.duration = duration;
        checkType(type);
        this.type = type;
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

    private void checkDayOfMonth(int dayOfMonth) {
        if (dayOfMonth < 1 || dayOfMonth > 31) {
            throw new IllegalArgumentException("Day is number between 01 and 31");
        }
    }

    public int getDayOfMonth() {
        return dayOfMonth;
    }

    private void checkDistance(int distance) {
        if (distance > 999) {
            throw new IllegalArgumentException("Cannot add workout over 1000 kilometres");
        }
    }

    public int getDistance() {
        return distance;
    }

    private void checkDuration(int distance) {
        if (duration < 0) {
            throw new IllegalArgumentException("Duration cannot be negative");
        }
        if (duration > 480) {
            throw new IllegalArgumentException("Unable to log workout over 8 hours");
        }
    }
    public int getDuration() {
        return duration;
    }

    private void checkType(String type) {
        if (!(type.equals("Running") || type.equals("Strength") || type.equals("Skiing") || type.equals("Other"))) {
            throw new IllegalArgumentException("Type must be 'Running', 'Strength', 'Skiing' or 'Other'");
        }
    }
    public String getType() {
        return type;
    }

    @Override
    public String toString() {
        return type + ":" + distance +"km in " + duration;
    }

    public static void main(String[] args) {
        WorkoutEntry workout1 = new WorkoutEntry(2001, 01, 20, 4, 30, "Running");
        System.out.println(workout1.getDayOfMonth());
    }
    

    

    
}
