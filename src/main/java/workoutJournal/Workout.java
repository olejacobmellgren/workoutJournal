package workoutJournal;

import java.util.Calendar;

public class Workout {

    private int year;
    private int month;
    private int dayOfMonth;
    private int distance;
    private int duration;
    private String type;

    public Workout(int dayOfMonth, int month, int year, String type, int distance, int duration) {
        checkDayOfMonth(dayOfMonth);
        this.dayOfMonth = dayOfMonth;
        checkMonth(month);
        this.month = month;
        checkYear(year);
        this.year = year;
        checkType(type);
        this.type = type;
        checkDistance(distance);
        this.distance = distance;
        checkDuration(duration);
        this.duration = duration;
        
    }

    public Workout(int dayOfMonth, int month, int year, String type, int duration) {
        checkDayOfMonth(dayOfMonth);
        this.dayOfMonth = dayOfMonth;
        checkMonth(month);
        this.month = month;
        checkYear(year);
        this.year = year;
        checkTypeStrength(type);
        this.type = type;
        checkDuration(duration);
        this.duration = duration;
        
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
            throw new IllegalArgumentException("Cannot add workout over 1000 kilometres (Unhealthy)");
        }
        if (type.equals("Strength") && distance != 0) {
            throw new IllegalArgumentException("Distance must be 0 when type is 'Strength'");
        }
    }

    public int getDistance() {
        return distance;
    }

    private void checkDuration(int duration) {
        if (duration < 0) {
            throw new IllegalArgumentException("Duration cannot be negative");
        }
        if (duration > 480) {
            throw new IllegalArgumentException("Unable to log workout over 8 hours (Unhealthy)");
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

    private void checkTypeStrength(String type) {
        if (!(type.equals("Strength"))) {
            throw new IllegalArgumentException("Type must be 'Strength' if no distance added");
        }
    }

    public String getType() {
        return type;
    }

    @Override
    public String toString() {
        if (type.equals("Strength")) {
            return type + ": " + duration + " minutes of lifting weigths";
        }
        return type + ": " + distance +" kilometres in " + duration + " minutes";
    }

    public static void main(String[] args) {
        Workout workout1 = new Workout(20, 02, 2021, "Strength", 90);
        System.out.println(workout1);
    }
    

    

    
}
