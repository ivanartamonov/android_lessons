package online.yourfit.data.workout.local;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Single;
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

    public Flowable<List<Workout>> getFinished(int limit) {
        return workoutDao.getFinished(limit);
    }

    public Flowable<Workout> findOngoingWorkout() {
        return workoutDao.findOngoing();
    }

    public Flowable<Workout> findById(int id) {
        return workoutDao.findById(id);
    }

    public Single<Integer> insert(Workout workout) {
        return Single.fromCallable(() -> workoutDao.insert(workout).intValue());
    }

    public Completable deleteOngoingWorkouts() {
        return Completable.fromAction(() -> workoutDao.deleteOngoingWorkouts());
    }

    public Completable deleteAll() {
        return Completable.fromAction(() -> workoutDao.deleteAll());
    }

    public Completable delete(Workout workout) {
        return Completable.fromAction(() -> workoutDao.delete(workout));
    }
}
