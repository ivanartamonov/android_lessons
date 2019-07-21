package online.yourfit.data.exercises.local;

import android.util.Log;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Single;
import online.yourfit.core.App;
import online.yourfit.data.exercises.Exercise;

public class ExerciseLocalRepository {

    @Inject
    ExerciseDao exerciseDao;

    public ExerciseLocalRepository() {
        App.instance.getAppComponent().inject(this);
    }

    public Single<Exercise> findById(int id) {
        return exerciseDao.findById(id);
    }

    public Flowable<List<Exercise>> getAll() {
        Log.d("ExerciseRepository", "Single<List<Exercise>> getAll");
        return exerciseDao.getAll();
    }

    public Completable insert(Exercise exercise) {
        return Completable.fromAction(() -> exerciseDao.insert(exercise));
    }

    public Completable insertAll(List<Exercise> exercises) {
        return Completable.fromAction(() -> {
            for (Exercise exercise: exercises) {
                exerciseDao.insert(exercise);
            }
        });
    }
}
