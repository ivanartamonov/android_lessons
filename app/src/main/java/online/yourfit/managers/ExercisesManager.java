package online.yourfit.managers;

import java.util.ArrayList;
import java.util.List;

import online.yourfit.models.Exercise;

public class ExercisesManager {

    private static List<Exercise> exercises = new ArrayList<>();

    public static List<Exercise> getList() {
        exercises.add(new Exercise("Test 1", 1));
        exercises.add(new Exercise("Test 2", 2));

        return exercises;
    }
}
