package workoutJournal;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class WorkoutMonth extends WorkoutPeriod{

    private int month;
    private List<Workout> workoutList;
    private List<Double> sleepList;
    private List<Integer> moodList;

    public WorkoutMonth(int month, int year) {
        super(year);
        checkMonth(month, year);
        this.month = month;
        this.workoutList = new ArrayList<>();
        this.sleepList = new ArrayList<>();
        this.moodList = new ArrayList<>();
    }

    public void checkMonth(int month, int year) {
        if (month < 1 || month > 12) {
            throw new IllegalArgumentException("Month is number between 01 and 12");
        }

        Calendar c = Calendar.getInstance();
        int yearNow = c.get(Calendar.YEAR);
        int monthNow = c.get(Calendar.MONTH) + 1;
        if ((year == yearNow && month > monthNow)) {
            throw new IllegalArgumentException("Cannot log workout for the future");
        }
    }

    public int getMonth() {
        return month;
    }

    public void addWorkoutToPeriod(Workout workout) {
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

    private void checkSleep(double sleepHours) {
        if (sleepHours < 0) {
            throw new IllegalArgumentException("Hours of sleep cannot be negative");
        } else if (sleepHours > 24) {
            throw new IllegalArgumentException("You should only log sleep for one day, and there are only 24 hours in a day");
        }
    }

    public void setSleepList(List<Double> sleepList) {
        this.sleepList = sleepList;
    }

    public void addMood(int mood) {
        checkMood(mood);
        moodList.add(mood);
    }

    private void checkMood(int mood) {
        if (mood < 1 || mood > 5) {
            throw new IllegalArgumentException("Mood should be number between 1 and 5");
        }
    }

    public void setMoodList(List<Integer> moodList) {
        this.moodList = moodList;
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
        String str = "Month; " + month + ";" + year + "\n";
        for (Workout workout : workoutList) {
            str += workout.toString() + "\n";
        }
        String sleepStr = "";
        for (Double hours : sleepList) {
            sleepStr += hours + ", ";
        }
        String moodStr = "";
        for (Integer mood : moodList) {
            moodStr += mood + ", ";
        }
        str += "Sleep: " + sleepStr + "\nMood: " + moodStr;
        return str;
    }

    public static void main(String[] args) {
        Workout workout1 = new Workout(01, 01, 2021, "Running", 50, 40);
        Workout workout2 = new Workout(02, 01, 2021, "Strength", 0, 20);
        Workout workout3 = new Workout(03, 01, 2021, "Skiing", 30, 30);
        Workout workout4 = new Workout(19, 01, 2021, "Skiing", 20, 30);
        Workout workout5 = new Workout(29, 01, 2021, "Skiing", 20, 30);

        WorkoutMonth workoutMonth = new WorkoutMonth(01, 2021);
        workoutMonth.addWorkoutToPeriod(workout1);
        workoutMonth.addWorkoutToPeriod(workout2);
        workoutMonth.addWorkoutToPeriod(workout3);
        workoutMonth.addWorkoutToPeriod(workout4);
        workoutMonth.addWorkoutToPeriod(workout5);
        workoutMonth.addSleep(8);
        //System.out.println(workoutMonth.getAverageDistance());
        //System.out.println(workoutMonth.getAverageDuration());
        //System.out.println(workoutMonth.getMonth());
        //System.out.println(workoutMonth.getRunningAmount());
        System.out.println(workoutMonth);
        System.out.println(workoutMonth.getTotalDistance());





    }


    
}
