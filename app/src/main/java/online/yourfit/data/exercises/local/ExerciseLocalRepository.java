package online.yourfit.data.exercises.local;

import android.os.AsyncTask;
import android.util.Log;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Flowable;
import online.yourfit.core.App;
import online.yourfit.data.exercises.Exercise;

public class ExerciseLocalRepository {

    @Inject
    ExerciseDao exerciseDao;

    public ExerciseLocalRepository() {
        App.instance.getAppComponent().inject(this);
    }

    public Flowable<List<Exercise>> getAll() {
        Log.d("ExerciseRepository", "Single<List<Exercise>> getAll");
        return exerciseDao.getAll();
    }

    public void insert(Exercise exercise) {
        new InsertExerciseAsyncTask(exerciseDao).execute(exercise);
    }

    private static class InsertExerciseAsyncTask extends AsyncTask<Exercise, Void, Void> {
        private ExerciseDao exerciseDao;

        InsertExerciseAsyncTask(ExerciseDao exerciseDao) {
            this.exerciseDao = exerciseDao;
        }

        @Override
        protected Void doInBackground(Exercise... exercises) {
            exerciseDao.insert(exercises[0]);
            return null;
        }
    }
}
