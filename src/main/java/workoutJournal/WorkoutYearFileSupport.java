package workoutJournal;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class WorkoutYearFileSupport implements IWorkoutYearFileReading {

    @Override
    public List<WorkoutYear> readWorkoutYear(String filename) throws IOException{
        WorkoutMonth workoutMonth = null;
        WorkoutYear workoutYear = null;
        List<Double> sleepList = new ArrayList<>();
        List<Integer> moodList = new ArrayList<>();

        try (Scanner scanner = new Scanner(getFile(filename))) {
            List<WorkoutYear> workoutYearsList = new ArrayList<>();
            while (scanner.hasNext()) {
                String line = scanner.nextLine();
                if (line.startsWith("Year")) {
                    String[] tmpYear = line.split(":");
                    int year = Integer.parseInt(tmpYear[1].strip());
                    workoutYear = new WorkoutYear(year);
                    workoutYearsList.add(workoutYear);
                } else if (line.startsWith("Month")) {
                    String[] tmpMonth = line.split(";");
                    int month= Integer.parseInt(tmpMonth[1].strip());
                    int mYear = Integer.parseInt(tmpMonth[2]);
                    workoutMonth = new WorkoutMonth(month, mYear);
                    workoutYear.addMonth(workoutMonth);
                } else if (line.startsWith("Sleep")) {
                    try {
                        String[] tmpSleep = line.split(":");
                        String[] sleepHours = tmpSleep[1].split(",");
                        for (int i = 0; i < sleepHours.length; i++) {
                            sleepList.add(Double.parseDouble(sleepHours[i]));
                        }
                    } catch (NumberFormatException e) {
                        continue;
                    }
                    
                } else if (line.startsWith("Mood")) {
                    try {
                        String[] tmpMood = line.split(":");
                        String[] moods = tmpMood[1].split(",");
                        for (int i = 1; i < moods.length; i++) {
                            moodList.add(Integer.parseInt(moods[i]));
                        }
                    } catch (NumberFormatException e) {
                        continue;
                    }
                } else if (line.isEmpty()) {
                    workoutMonth.setSleepList(sleepList);
                    workoutMonth.setMoodList(moodList);
                } else {
                    String[] workoutLine = line.split(",");
                    String[] date = workoutLine[0].split(";");
                    
                    int day = Integer.parseInt(date[0]);
                    int month = Integer.parseInt(date[1]);
                    int year = Integer.parseInt(date[2]);
                    String type = workoutLine[1].strip();
                    double distance = Double.parseDouble(workoutLine[2].strip());
                    double duration = Double.parseDouble(workoutLine[3].strip());
                    Workout tmpWorkout = new Workout(day, month, year, type, distance, duration);
                    workoutYear.addWorkoutToPeriod(tmpWorkout);
                }
                
            }
            scanner.close();
            return workoutYearsList;
        }
    }

    @Override
    public void writeWorkoutYear(String filename, List<WorkoutYear> workoutYearsList) throws IOException{
       try (PrintWriter writer = new PrintWriter(getFile(filename))) {
           for (WorkoutYear workoutYear : workoutYearsList) {
               writer.println(workoutYear.toString());
           }
       }
        
    }

    public File getFile(String filename) {
        return new File(WorkoutYearFileSupport.class.getResource("workoutYearsFiles/").getFile() + filename + ".txt");
    }
    
}
