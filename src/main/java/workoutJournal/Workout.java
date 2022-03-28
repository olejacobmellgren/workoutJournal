package workoutJournal;

import java.util.Calendar;

public class Workout {

    private int year;
    private int month;
    private int dayOfMonth;
    private double distance;
    private double duration;
    private String type;

    public Workout(int dayOfMonth, int month, int year, String type, double distance, double duration) {
        checkDateFuture(dayOfMonth, month, year);
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

    //public Workout(int dayOfMonth, int month, int year, String type, double duration) {
    //    checkDateFuture(dayOfMonth, month, year);
    //    checkDayOfMonth(dayOfMonth);
    //    this.dayOfMonth = dayOfMonth;
    //    checkMonth(month);
    //    this.month = month;
    //    checkYear(year);
    //    this.year = year;
    //    checkTypeStrength(type);
    //    this.type = type;
    //    checkDuration(duration);
    //    this.duration = duration;
    //    
    //}

    private void checkDateFuture(int dayOfMonth, int month, int year) {
        Calendar c = Calendar.getInstance();
        int yearNow = c.get(Calendar.YEAR);
        int monthNow = c.get(Calendar.MONTH) + 1;
        int dayNow = c.get(Calendar.DAY_OF_MONTH);
        if ((year > yearNow) || (year >= yearNow && month > monthNow) || (year >= yearNow && month >= monthNow && dayOfMonth > dayNow)){
            throw new IllegalArgumentException("You are unable log for the future!");
        }
    }
    
    private void checkYear(int year) {
        if (year < 2000) {
            throw new IllegalArgumentException("You are unable log before year 2000!");
        }
    }

    public int getYear() {
        return year;
    }

    private void checkMonth(int month) {
        if (month < 1 || month > 12) {
            throw new IllegalArgumentException("Month must be a number between 01 and 12");
        }
    }

    public int getMonth() {
        return month;
    }

    private void checkDayOfMonth(int dayOfMonth) {
        if (dayOfMonth < 1 || dayOfMonth > 31) {
            throw new IllegalArgumentException("Day must be a number between 01 and 31");
        }
    }

    public int getDayOfMonth() {
        return dayOfMonth;
    }

    private void checkDistance(double distance) {
        if (distance > 999) {
            throw new IllegalArgumentException("You are unable add workout over 1000 kilometres (Unhealthy)");
        } else if (distance < 0) {
            throw new IllegalArgumentException("Distance must be a positive number");
        } else if (type.equals("Strength") && distance != 0) {
            throw new IllegalArgumentException("Distance must be 0 when type is 'Strength'");
        }
    }

    public double getDistance() {
        return distance;
    }

    private void checkDuration(double duration) {
        if (duration < 0) {
            throw new IllegalArgumentException("Duration must be a positive number");
        } else if (duration > 480) {
            throw new IllegalArgumentException("Unable to log workout over 8 hours (Unhealthy)");
        }
    }

    public double getDuration() {
        return duration;
    }

    private void checkType(String type) {
        if (!(type.equals("Running") || type.equals("Strength") || type.equals("Skiing") || type.equals("Other"))) {
            throw new IllegalArgumentException("Type must be 'Running', 'Strength', 'Skiing' or 'Other'");
        }
    }

    //private void checkTypeStrength(String type) {
    //    if (!(type.equals("Strength"))) {
    //        throw new IllegalArgumentException("Type must be 'Strength' if no distance added");
    //    }
    //}

    public String getType() {
        return type;
    }

    @Override
    public String toString() {
        if (type.equals("Strength")) {
            return dayOfMonth + "." + month + "." + year + ": " + type + ", " + duration + " min";
        }
        return dayOfMonth + "." + month + "." + year + ": " + type + ", " + distance +" km, " + duration + " min";
    }

    public static void main(String[] args) {
        Workout workout1 = new Workout(20, 02, 2021, "Running", 1, 90);
        System.out.println(workout1);
        System.out.println(workout1.getDistance());
        //System.out.println(Calendar.getInstance().get(Calendar.MONTH));
        
    }
    

    

    
}
