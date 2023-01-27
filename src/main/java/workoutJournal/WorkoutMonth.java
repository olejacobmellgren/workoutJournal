package workoutJournal;

import java.math.BigDecimal;
import java.math.RoundingMode;
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

    private void checkMonth(int month, int year) {
        if (month < 1 || month > 12) {
            throw new IllegalArgumentException("Month is number between 01 and 12");
        }

        Calendar c = Calendar.getInstance();
        int yearNow = c.get(Calendar.YEAR);
        int monthNow = c.get(Calendar.MONTH);
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
        double averageDistance = workoutList.stream()
                                 .filter(w -> !w.getType().equals("Strength"))
                                 .mapToDouble(w -> w.getDistance())
                                 .average()
                                 .orElse(0);
        return new BigDecimal(averageDistance).setScale(1, RoundingMode.HALF_UP).doubleValue();
    }  

    public double getAverageDuration() {
        double averageDuration = workoutList.stream()
                                 .mapToDouble(w -> w.getDuration())
                                 .average()
                                 .orElse(0);
        return new BigDecimal(averageDuration).setScale(1, RoundingMode.HALF_UP).doubleValue();

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
        double totalDistance =  workoutList.stream()
                                .mapToDouble(w -> w.getDistance())
                                .sum();
        return new BigDecimal(totalDistance).setScale(1, RoundingMode.HALF_UP).doubleValue();

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
        } else if (sleepList.size() == 31) {
            throw new IllegalStateException("You should only enter one time of sleep per day, and you have already logged every sleep for this month");
        }
    }

    public void setSleepList(List<Double> sleepList) {
        this.sleepList = sleepList;
    }

    public List<Double> getSleepList() {
        return new ArrayList<>(sleepList);
    }

    public void addMood(int mood) {
        checkMood(mood);
        moodList.add(mood);
    }

    private void checkMood(int mood) {
        if (mood < 1 || mood > 5) {
            throw new IllegalArgumentException("Mood should be number between 1 and 5");
        } else if (moodList.size() == 31) {
            throw new IllegalStateException("You should only enter one mood per day, and you have already logged every mood for this month");
        }
    }

    public void setMoodList(List<Integer> moodList) {
        this.moodList = moodList;
    }

    public List<Integer> getMoodList() {
        return new ArrayList<>(moodList);
    }


    public double getAverageSleep() {
        double averageSleep = sleepList.stream()
                              .mapToDouble(w -> w)
                              .average()
                              .orElse(0);
        return new BigDecimal(averageSleep).setScale(1, RoundingMode.HALF_UP).doubleValue();
    }

    public double getAverageMood() {
        return moodList.stream()
                        .mapToInt(w -> w)
                        .average()
                        .orElse(0);
    }

   @Override
   public boolean equals(Object obj) {
       if (obj instanceof WorkoutMonth) {
           WorkoutMonth workoutMonth = (WorkoutMonth) obj;
           for (int i = 0; i < this.getWorkouts().size(); i++) {
               if (!this.getWorkouts().get(i).equals(workoutMonth.getWorkouts().get(i))) {
                   return false;
               }
           }
           if ((!this.getSleepList().equals(workoutMonth.getSleepList()) || (!this.getMoodList().equals(workoutMonth.getMoodList())))) {
               return false;
           }
           return true;
       }
       return false;
   }

    @Override
    public String toString() {
        String str = "Month; " + month + ";" + year + "\n";
        for (Workout workout : workoutList) {
            str += workout.toString() + "\n";
        }
        String sleepStr = "";
        for (Double hours : sleepList) {
            sleepStr += ", " + hours;
        }
        String moodStr = "";
        for (Integer mood : moodList) {
            moodStr += ", " + mood;
        }
        str += "Sleep" + sleepStr + "\nMood" + moodStr;
        return str;
    } 
}

