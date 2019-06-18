package online.yourfit.managers;

import java.util.ArrayList;
import java.util.List;

import online.yourfit.models.Exercise;

public class ExercisesManager {

    public static final String ARG_EXERCISE_ID = "arg exercise id";

    private static List<Exercise> exercises = new ArrayList<>();

    public static List<Exercise> getList() {
        if (exercises.size() == 0) {
            exercises.add(new Exercise(
                    "Жим штанги лежа на горизонтальной скамье",
                    0,
                    "https://yourfit.online/uploads/exercise_pics/img_45.png",
                    "https://yourfit.online/uploads/exercise_pics/img2_45.png"
            ));
            exercises.add(new Exercise(
                    "Жим от груди в тренажере",
                    0,
                    "https://yourfit.online/uploads/exercise_pics/img_46.png",
                    "https://yourfit.online/uploads/exercise_pics/img2_46.png"
            ));
        }

        return exercises;
    }

    public static void setList(List<Exercise> list) {
        exercises = list;
    }
}
