package online.yourfit.data.exercises.local;

import android.app.Application;
import android.os.AsyncTask;
import android.util.Log;

import java.util.List;

import io.reactivex.Single;
import online.yourfit.core.db.AppDatabase;
import online.yourfit.data.exercises.Exercise;

public class ExerciseLocalRepository {

    private ExerciseDao exerciseDao;

    public ExerciseLocalRepository(Application application) {
        exerciseDao = AppDatabase.getInstance(application.getApplicationContext()).exerciseDao();
    }

    public Single<List<Exercise>> getAll() {
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
