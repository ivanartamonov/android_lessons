package online.yourfit.data.exercises.remote;

import java.util.List;

import io.reactivex.Flowable;
import online.yourfit.core.NetworkService;
import online.yourfit.data.exercises.Exercise;

public class ExerciseRemoteRepository {

    private ExercisesApi exercisesApi;

    public ExerciseRemoteRepository() {
        exercisesApi = NetworkService.getInstance().getExercisesApi();
    }

    public Flowable<List<Exercise>> getAll() {
        return exercisesApi.getExercises();
    }

}
