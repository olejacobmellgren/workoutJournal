package workoutJournal;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class WorkoutYear extends WorkoutPeriod{

    private List<WorkoutMonth> workoutMonthsList = new ArrayList<>();

    public WorkoutYear(int year) {
        super(year);
    }

    public List<WorkoutMonth> getMonthsWithWorkouts() {
        return new ArrayList<>(workoutMonthsList);
    }

    private void checkMonth(WorkoutMonth workoutMonth) {
        if (!(this.year == workoutMonth.getYear())) {
            throw new IllegalArgumentException("Cannot add workout month to wrong year");
        }
    }

    public void addMonth(WorkoutMonth workoutMonth) {
        checkMonth(workoutMonth);
        this.workoutMonthsList.add(workoutMonth);
    }

    public void addWorkoutToPeriod(Workout workout) {
        for (WorkoutMonth workoutMonth : workoutMonthsList) {
            if (workoutMonth.getMonth() == workout.getMonth()) {
                workoutMonth.addWorkoutToPeriod(workout);
                return;
            }
        }
        WorkoutMonth workoutMonth = new WorkoutMonth(workout.getMonth(), this.year);
        this.addMonth(workoutMonth);
        workoutMonth.addWorkoutToPeriod(workout);
    }

    @Override
    public String toString() {
        String str = "Year: "+ year + "\n";
        for (int i = 0; i < workoutMonthsList.size(); i++) {
            if (workoutMonthsList.get(i) == workoutMonthsList.get(workoutMonthsList.size()-1)) {
                str += workoutMonthsList.get(i).toString() + "\n";
            } else {
                str += workoutMonthsList.get(i).toString() + "\n\n";
            }
        }{
            

        }
        return str;
    }

    public static void main(String[] args) {
        Workout workout1 = new Workout(01, 01, 2021, "Running", 50, 10);
        Workout workout2 = new Workout(02, 01, 2021, "Strength", 0, 20);
        Workout workout3 = new Workout(03, 02, 2021, "Other", 30, 30);
        Workout workout4 = new Workout(05, 03, 2022, "Running", 10, 40);
        //Workout workout5 = new Workout(02, 04, 2021, "Running", 20, 40);
        WorkoutMonth workoutMonth = new WorkoutMonth(01, 2021);
        WorkoutMonth workoutMonth2 = new WorkoutMonth(02, 2021);
        WorkoutMonth workoutMonth3 = new WorkoutMonth(03, 2022);
        //WorkoutMonth workoutMonth4 = new WorkoutMonth(04, 2021);
        workoutMonth.addWorkoutToPeriod(workout1);
        workoutMonth.addWorkoutToPeriod(workout2);
        workoutMonth.addMood(4);
        workoutMonth.addMood(2);
        workoutMonth.addSleep(8);
        workoutMonth2.addWorkoutToPeriod(workout3);
        workoutMonth2.addMood(3);
        workoutMonth3.addWorkoutToPeriod(workout4);
        workoutMonth3.addSleep(10);
        workoutMonth3.addSleep(12);
        //System.out.println(workoutMonth);
        WorkoutYear workoutYear = new WorkoutYear(2021);
        WorkoutYear workoutYear2 = new WorkoutYear(2022);

        workoutYear.addMonth(workoutMonth);
        workoutYear.addMonth(workoutMonth2);
        workoutYear2.addMonth(workoutMonth3);
        //workoutYear.addWorkoutToYear(workout5);
        //System.out.println(workoutYear2);
        //System.out.println(workoutYear.getYear());
        List<WorkoutYear> workoutYearsList = new ArrayList<>(Arrays.asList(workoutYear, workoutYear2));
        for (WorkoutYear workoutYears : workoutYearsList) {
            //System.out.println(workoutYears.toString());
        }

        if (workoutYearsList.get(0).equals(workoutYear)) {
            System.out.println(workoutYearsList.get(0));
            System.out.println(true);
            System.out.println(workoutYear);
        }
        




    }
    
}
