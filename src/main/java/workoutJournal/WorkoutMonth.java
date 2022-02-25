package workoutJournal;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class WorkoutMonth {

    private int year;
    private int month;
    private List<Workout> workoutList;

    public WorkoutMonth(int year, int month) {
        checkYear(year);
        this.year = year;
        checkMonth(month);
        this.month = month;
        this.workoutList = new ArrayList<>();
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
        if (!((this.month == workout.getMonth()) && (this.year == workout.getYear()))) {
            throw new IllegalArgumentException("Cannot add workout to wrong month and year");
        }
        workoutList.add(workout);
    }

    public double getAverageDistance() {
        int sum = 0;
        int amount = 0;
        for (Workout workout : workoutList) {
            if (workout.getDistance() != 0) {
                sum += workout.getDistance();
                amount ++;
            }
        }
        double average = sum/amount;
        return average;
    }

    public double getAverageDuration() {
        int sum = 0;
        int amount = 0;
        for (Workout workout : workoutList) {
            sum += workout.getDuration();
            amount ++;
        }
        double average = sum/amount;
        return average;
    }

    public int getRunningAmount() {
        int amount = 0;
        for (Workout workout : workoutList) {
            if (workout.getType().equals("Running")) {
                amount++;
            }
        }
        return amount;
    }

    public int getSkiingAmount() {
        int amount = 0;
        for (Workout workout : workoutList) {
            if (workout.getType().equals("Skiing")) {
                amount++;
            }
        }
        return amount;
    }

    public int getStrengthAmount() {
        int amount = 0;
        for (Workout workout : workoutList) {
            if (workout.getType().equals("Strength")) {
                amount++;
            }
        }
        return amount;
    }

    public int getOtherAmount() {
        int amount = 0;
        for (Workout workout : workoutList) {
            if (workout.getType().equals("Other")) {
                amount++;
            }
        }
        return amount;
    }

    public static void main(String[] args) {
        Workout workout1 = new Workout(01, 01, 2021, "Running", 50, 10);
        Workout workout2 = new Workout(02, 01, 2021, "Strength", 20);
        Workout workout3 = new Workout(03, 01, 2021, "Other", 30, 30);
        Workout workout4 = new Workout(05, 01, 2021, "Running", 10, 40);
        WorkoutMonth workoutMonth = new WorkoutMonth(2021, 01);
        workoutMonth.addWorkout(workout1);
        workoutMonth.addWorkout(workout2);
        workoutMonth.addWorkout(workout3);
        workoutMonth.addWorkout(workout4);
        System.out.println(workoutMonth.getAverageDistance());
        System.out.println(workoutMonth.getAverageDuration());
        System.out.println(workoutMonth.getMonth());
        System.out.println(workoutMonth.getRunningAmount());





    }


    
}
