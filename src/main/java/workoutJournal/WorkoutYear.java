package workoutJournal;

import java.util.ArrayList;
import java.util.List;

public class WorkoutYear extends WorkoutPeriod{

    private List<WorkoutMonth> workoutMonthsList;

    public WorkoutYear(int year) {
        super(year);
        this.workoutMonthsList = new ArrayList<>();
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
    public boolean equals(Object obj) {
        if (obj instanceof WorkoutYear) {
            WorkoutYear workoutYear = (WorkoutYear) obj;
            for (int i = 0; i < this.workoutMonthsList.size(); i++) {
                if (!workoutYear.getMonthsWithWorkouts().get(i).equals(this.getMonthsWithWorkouts().get(i))) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        String str = "Year: "+ year + "\n";
        for (int i = 0; i < workoutMonthsList.size(); i++) {
            if (workoutMonthsList.get(i).equals(workoutMonthsList.get(workoutMonthsList.size()-1))) {
                str += workoutMonthsList.get(i).toString();
            } else {
                str += workoutMonthsList.get(i).toString() + "\n\n";
            }
        }
        return str;
    }
}
