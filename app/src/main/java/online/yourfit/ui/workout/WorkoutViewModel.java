package online.yourfit.ui.workout;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import online.yourfit.data.exercises.Exercise;
import online.yourfit.data.exercises.ExerciseRepository;
import online.yourfit.data.workout.Workout;
import online.yourfit.data.workout.WorkoutRepository;

public class WorkoutViewModel extends ViewModel {

    private static WorkoutViewModel instance;

    public static final int STATUS_NEW = 1;
    public static final int STATUS_IN_PROGRESS = 2;
    public static final int STATUS_FINISHED = 3;

    private CompositeDisposable compositeDisposable = new CompositeDisposable();
    private WorkoutRepository workoutRepository;
    private ExerciseRepository exerciseRepository;
    private Workout ongoingWorkout;

    private MutableLiveData<Integer> status = new MutableLiveData<>();
    private MutableLiveData<List<Exercise>> doingExercises = new MutableLiveData<>();

    private WorkoutViewModel() {
        this.workoutRepository = new WorkoutRepository();
        this.exerciseRepository = new ExerciseRepository();
        this.status.setValue(STATUS_NEW);
    }

    public static WorkoutViewModel getInstance() {
        if (instance == null) {
            instance = new WorkoutViewModel();
        }
        return instance;
    }

    public void startWorkout() {
        this.status.setValue(STATUS_IN_PROGRESS);

        ongoingWorkout = new Workout();
        Date startedAt = new Date();
        ongoingWorkout.setStartedAt(startedAt.getTime());

        Disposable disposable = this.workoutRepository.insert(ongoingWorkout)
            .subscribe(
                id -> ongoingWorkout.setId(id),
                throwable -> Log.d("Workout", "onError: " + throwable.getMessage())
            );

        compositeDisposable.add(disposable);
    }

    public void stopWorkout() {
        this.status.setValue(STATUS_FINISHED);

        if (ongoingWorkout != null) {
            ongoingWorkout.setFinishedAt(new Date().getTime());
            workoutRepository.insert(ongoingWorkout).subscribe();
        } else {
            Disposable disposable = this.workoutRepository.findOngoingWorkout()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(
                            workout -> {
                                workout.setFinishedAt(new Date().getTime());
                                workoutRepository.insert(workout).subscribe();
                            },
                            throwable -> Log.d("Workout", "onError: " + throwable.getMessage())
                    );
            compositeDisposable.add(disposable);
        }

        this.doingExercises = null;
    }

    public LiveData<List<Exercise>> getDoingExercises() {
        if (this.doingExercises.getValue() == null) {
            this.doingExercises.setValue(new ArrayList<>());
        }
        return this.doingExercises;
    }

    public void addDoingExerciseById(int id) {
        Disposable disposable = exerciseRepository.findById(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(exercise -> {
                    getDoingExercises().getValue().add(exercise);
                });
        compositeDisposable.add(disposable);
    }

    public Exercise getExerciseById(int id) {
        List<Exercise> list = this.getDoingExercises().getValue();
        if (list != null) {
            for (Exercise exercise : list) {
                if (exercise.getId() == id) {
                    return exercise;
                }
            }
        }
        return null;
    }

    public void deleteDoingExerciseById(int id) {
        List<Exercise> list = this.getDoingExercises().getValue();
        if (list != null) {
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i).getId() == id) {
                    list.remove(i);
                    return;
                }
            }
        }
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        compositeDisposable.clear();
    }
}
