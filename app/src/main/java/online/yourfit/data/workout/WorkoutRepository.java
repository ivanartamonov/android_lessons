package online.yourfit.data.workout;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import online.yourfit.data.workout.local.WorkoutLocalRepository;

public class WorkoutRepository {

    private WorkoutLocalRepository localRepository;

    public WorkoutRepository() {
        localRepository = new WorkoutLocalRepository();
    }

    public Flowable<List<Workout>> getAll() {
        return localRepository.getAll()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Single<Workout> findOngoingWorkout() {
        return localRepository.findOngoingWorkout()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
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
