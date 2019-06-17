package online.yourfit.managers;

import online.yourfit.models.WorkoutHistoryItem;

import java.util.ArrayList;
import java.util.List;

public class WorkoutHistoryManager {

    public static final String ARG_WORKOUT_ID = "arg workout id";

    private static List<WorkoutHistoryItem> workoutHistoryItems = new ArrayList<>();

    public static List<WorkoutHistoryItem> getList() {
        workoutHistoryItems.add(new WorkoutHistoryItem("02.05", "My program", 3600, 5400));
        workoutHistoryItems.add(new WorkoutHistoryItem("03.05", "My program", 3700, 5200));
        workoutHistoryItems.add(new WorkoutHistoryItem("04.05", "My program", 3800, 7300));
        workoutHistoryItems.add(new WorkoutHistoryItem("05.05", "My program", 3900, 6420));
        workoutHistoryItems.add(new WorkoutHistoryItem("05.05", "My program", 3900, 6420));
        workoutHistoryItems.add(new WorkoutHistoryItem("05.05", "My program", 3900, 6420));
        workoutHistoryItems.add(new WorkoutHistoryItem("05.05", "My program", 3900, 6420));
        workoutHistoryItems.add(new WorkoutHistoryItem("05.05", "My program", 3900, 6420));
        workoutHistoryItems.add(new WorkoutHistoryItem("05.05", "My program", 3900, 6420));
        workoutHistoryItems.add(new WorkoutHistoryItem("05.05", "My program", 3900, 6420));
        workoutHistoryItems.add(new WorkoutHistoryItem("05.05", "My program", 3900, 6420));

        /*
        workoutHistoryItems.add(new WorkoutHistoryItem("05.05", "My program", 3900, 6420));
        workoutHistoryItems.add(new WorkoutHistoryItem("05.05", "My program", 3900, 6420));
        workoutHistoryItems.add(new WorkoutHistoryItem("05.05", "My program", 3900, 6420));
        workoutHistoryItems.add(new WorkoutHistoryItem("05.05", "My program", 3900, 6420));
        workoutHistoryItems.add(new WorkoutHistoryItem("05.05", "My program", 3900, 6420));
        workoutHistoryItems.add(new WorkoutHistoryItem("05.05", "My program", 3900, 6420));
        workoutHistoryItems.add(new WorkoutHistoryItem("05.05", "My program", 3900, 6420));
        workoutHistoryItems.add(new WorkoutHistoryItem("05.05", "My program", 3900, 6420));
        workoutHistoryItems.add(new WorkoutHistoryItem("05.05", "My program", 3900, 6420));
        workoutHistoryItems.add(new WorkoutHistoryItem("05.05", "My program", 3900, 6420));
        workoutHistoryItems.add(new WorkoutHistoryItem("05.05", "My program", 3900, 6420));
        workoutHistoryItems.add(new WorkoutHistoryItem("05.05", "My program", 3900, 6420));
        */

        return workoutHistoryItems;
    }

}
