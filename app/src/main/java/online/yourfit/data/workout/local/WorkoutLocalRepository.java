package online.yourfit.data.workout.local;

import android.util.Log;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import online.yourfit.core.App;
import online.yourfit.data.workout.Workout;

public class WorkoutLocalRepository {

    @Inject
    WorkoutDao workoutDao;

    public WorkoutLocalRepository() {
        App.instance.getAppComponent().inject(this);
    }

    public Flowable<List<Workout>> getAll() {
        return workoutDao.getAll();
    }

    public Flowable<Workout> findOngoingWorkout() {
        return workoutDao.findOngoing();
    }

    public Completable insert(Workout workout) {
        Log.d("Workout", "localInsert, ID: " + workout.getId() + ", started: " + workout.getFinishedAt() + " finished: " + workout.getFinishedAt());
        return Completable.fromAction(() -> {
            Log.d("Workout", "Dao insert");
            workoutDao.insert(workout);
        });
    }

    public Completable deleteOngoingWorkouts() {
        return Completable.fromAction(() -> workoutDao.deleteOngoingWorkouts());
    }

    public Completable deleteAll() {
        return Completable.fromAction(() -> workoutDao.deleteAll())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
