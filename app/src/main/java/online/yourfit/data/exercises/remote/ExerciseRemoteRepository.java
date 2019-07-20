package online.yourfit.data.exercises.remote;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Flowable;
import online.yourfit.core.App;
import online.yourfit.data.exercises.Exercise;

public class ExerciseRemoteRepository {

    @Inject
    ExercisesApi exercisesApi;

    public ExerciseRemoteRepository() {
        App.instance.getAppComponent().inject(this);
    }

    public Flowable<List<Exercise>> getAll() {
        return exercisesApi.getExercises();
    }

}
