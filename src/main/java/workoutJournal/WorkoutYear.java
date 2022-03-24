package workoutJournal;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class WorkoutYear {

    private int year;
    private List<WorkoutMonth> workoutMonthsList = new ArrayList<>();

    public WorkoutYear(int year) {
        checkYear(year);
        this.year = year;
    }

    public void checkYear(int year) {
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

    public List<WorkoutMonth> getMonthsWithWorkouts() {
        return new ArrayList<>(workoutMonthsList);
    }

    public void addMonth(WorkoutMonth workoutMonth) {
        if (!(this.year == workoutMonth.getYear())) {
            throw new IllegalArgumentException("Cannot add workout month to wrong year");
        }
        this.workoutMonthsList.add(workoutMonth);
    }

    public void addWorkoutToYear(Workout workout) {
        for (WorkoutMonth workoutMonth : workoutMonthsList) {
            if (workoutMonth.getMonth() == workout.getMonth()) {
                workoutMonth.addWorkoutToMonth(workout);
                return;
            }
        }
        WorkoutMonth workoutMonth = new WorkoutMonth(this.year, workout.getMonth());
        this.addMonth(workoutMonth);
        workoutMonth.addWorkoutToMonth(workout);
    }

    @Override
    public String toString() {
        String str = "";
        str += this.year + "\n\n";
        for (WorkoutMonth workoutMonth : workoutMonthsList) {
            str += workoutMonth.toString() + "\n";
        }
        return str;
    }

    public static void main(String[] args) {
        Workout workout1 = new Workout(01, 01, 2021, "Running", 50, 10);
        Workout workout2 = new Workout(02, 01, 2021, "Strength", 20);
        Workout workout3 = new Workout(03, 02, 2021, "Other", 30, 30);
        Workout workout4 = new Workout(05, 03, 2021, "Running", 10, 40);
        Workout workout5 = new Workout(02, 04, 2021, "Running", 20, 40);
        WorkoutMonth workoutMonth = new WorkoutMonth(2021, 01);
        WorkoutMonth workoutMonth2 = new WorkoutMonth(2021, 02);
        WorkoutMonth workoutMonth3 = new WorkoutMonth(2021, 03);
        WorkoutMonth workoutMonth4 = new WorkoutMonth(2021, 04);
        workoutMonth.addWorkoutToMonth(workout1);
        workoutMonth.addWorkoutToMonth(workout2);
        workoutMonth2.addWorkoutToMonth(workout3);
        workoutMonth3.addWorkoutToMonth(workout4);
        //System.out.println(workoutMonth);
        WorkoutYear workoutYear = new WorkoutYear(2021);
        workoutYear.addMonth(workoutMonth);
        workoutYear.addMonth(workoutMonth2);
        workoutYear.addMonth(workoutMonth3);
        //workoutYear.addWorkoutToYear(workout5);
        System.out.println(workoutYear);
        System.out.println(workoutYear.getYear());



    }
    
}
