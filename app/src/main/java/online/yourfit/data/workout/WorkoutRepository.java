package online.yourfit.data.workout;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import online.yourfit.data.workout.local.WorkoutLocalRepository;

public class WorkoutRepository {

    private WorkoutLocalRepository localRepository;

    public WorkoutRepository() {
        localRepository = new WorkoutLocalRepository();
    }

    public Flowable<Workout> findById(int id) {
        return localRepository.findById(id);
    }

    public Flowable<List<Workout>> getAll() {
        return localRepository.getAll();
    }

    public Flowable<Workout> findOngoingWorkout() {
        return localRepository.findOngoingWorkout();
    }

    public Completable insert(Workout workout) {
        return localRepository.insert(workout)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Completable deleteOngoingWorkouts() {
        return localRepository.deleteOngoingWorkouts();
    }

    public Completable deleteAll() {
        return localRepository.deleteAll();
    }
}
