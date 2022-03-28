package workoutJournal;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class WorkoutMonth {

    private int year;
    private int month;
    private List<Workout> workoutList;
    private List<Double> sleepList;
    private List<Integer> moodList;

    public WorkoutMonth(int month, int year) {
        checkDateFuture(month, year);
        checkMonth(month);
        this.month = month;
        checkYear(year);
        this.year = year;
        this.workoutList = new ArrayList<>();
        this.sleepList = new ArrayList<>();
        this.moodList = new ArrayList<>();
    }

    public void checkDateFuture(int month, int year) {
        Calendar c = Calendar.getInstance();
        int yearNow = c.get(Calendar.YEAR);
        int monthNow = c.get(Calendar.MONTH) + 1;
        if ((year > yearNow) || (year >= yearNow && month > monthNow)) {
            throw new IllegalArgumentException("Cannot log workout for the future");
        }

    }

    private void checkYear(int year) {
        if (year < 2000) {
            throw new IllegalArgumentException("Cannot log workout before year 2000");
        }
    }

    public int getYear() {
        return year;
    }

    public void checkMonth(int month) {
        if (month < 1 || month > 12) {
            throw new IllegalArgumentException("Month is number between 01 and 12");
        }
    }

    public int getMonth() {
        return month;
    }

    public void addWorkoutToMonth(Workout workout) {
        if (!((this.month == workout.getMonth()) && (this.year == workout.getYear()))) {
            throw new IllegalArgumentException("Cannot add workout to wrong month or year");
        }
        this.workoutList.add(workout);

    }

    public List<Workout> getWorkouts() {
        return new ArrayList<>(workoutList);
    }

    public double getAverageDistance() {
        return workoutList.stream()
                          .filter(w -> !w.getType().equals("Strength"))
                          .mapToDouble(w -> w.getDistance())
                          .average()
                          .orElse(0);
    }

    public double getAverageDuration() {
        return workoutList.stream()
                          .mapToDouble(w -> w.getDuration())
                          .average()
                          .orElse(0);
    }

    public int getRunningAmount() {
        return workoutList.stream()
                          .filter(w -> w.getType().equals("Running"))
                          .toList()
                          .size();
    }

    public int getSkiingAmount() {
        return workoutList.stream()
                          .filter(w -> w.getType().equals("Skiing"))
                          .toList()
                          .size();
    }

    public int getStrengthAmount() {
        return workoutList.stream()
                          .filter(w -> w.getType().equals("Strength"))
                          .toList()
                          .size();
    }

    public int getOtherAmount() {
        return workoutList.stream()
                          .filter(w -> w.getType().equals("Other"))
                          .toList()
                          .size();
    }

    public double getTotalDistance() {
        return workoutList.stream()
                          .mapToDouble(w -> w.getDistance())
                          .sum();
    }

    public void addSleep(double sleepHours) {
        checkSleep(sleepHours);
        sleepList.add(sleepHours);
    }

    public void checkSleep(double sleepHours) {
        if (sleepHours < 0) {
            throw new IllegalArgumentException("Hours of sleep cannot be negative");
        } else if (sleepHours > 24) {
            throw new IllegalArgumentException("You should only log sleep for one day, and there are only 24 hours in a day");
        }
    }

    public void addMood(int mood) {
        moodList.add(mood);
    }

    public double getAverageSleep() {
        return sleepList.stream()
                        .mapToDouble(w -> w)
                        .average()
                        .orElse(0);
    }

    public double getAverageMood() {
        return moodList.stream()
                        .mapToInt(w -> w)
                        .average()
                        .orElse(0);
    }

    @Override
    public String toString() {
        String str = "";
        for (Workout workout : workoutList) {
            str += workout.toString() + "\n";
        }
        str += "Sleep list:" + sleepList + "\nMood list:" + moodList;
        return str;
    }

    public static void main(String[] args) {
        Workout workout1 = new Workout(01, 01, 2021, "Running", 50, 40);
        Workout workout2 = new Workout(02, 01, 2021, "Strength", 0, 20);
        Workout workout3 = new Workout(03, 01, 2021, "Skiing", 30, 30);
        Workout workout4 = new Workout(19, 01, 2021, "Skiing", 20, 30);
        Workout workout5 = new Workout(29, 01, 2021, "Skiing", 20, 30);

        WorkoutMonth workoutMonth = new WorkoutMonth(01, 2021);
        workoutMonth.addWorkoutToMonth(workout1);
        workoutMonth.addWorkoutToMonth(workout2);
        workoutMonth.addWorkoutToMonth(workout3);
        workoutMonth.addWorkoutToMonth(workout4);
        workoutMonth.addWorkoutToMonth(workout5);
        workoutMonth.addSleep(8);
        System.out.println(workoutMonth.getAverageDistance());
        System.out.println(workoutMonth.getAverageDuration());
        System.out.println(workoutMonth.getMonth());
        System.out.println(workoutMonth.getRunningAmount());
        System.out.println(workoutMonth);
        System.out.println(workoutMonth.getTotalDistance());





    }


    
}
