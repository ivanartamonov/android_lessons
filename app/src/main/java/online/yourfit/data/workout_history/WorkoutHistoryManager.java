package online.yourfit.data.workout_history;

import java.util.ArrayList;
import java.util.List;

public class WorkoutHistoryManager {

    public static final String ARG_WORKOUT_ID = "arg workout id";

    private static List<WorkoutHistoryItem> workoutHistoryItems = new ArrayList<>();

    public static List<WorkoutHistoryItem> getList() {
        if (workoutHistoryItems.size() == 0) {
            workoutHistoryItems.add(new WorkoutHistoryItem("02.05", "My program", 3600, 5400));
            workoutHistoryItems.add(new WorkoutHistoryItem("03.05", "My program", 3700, 5200));
            workoutHistoryItems.add(new WorkoutHistoryItem("04.05", "My program", 3800, 7300));
        }

        return workoutHistoryItems;
    }

}
