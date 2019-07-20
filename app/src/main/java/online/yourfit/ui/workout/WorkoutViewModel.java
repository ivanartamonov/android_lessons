package online.yourfit.ui.workout;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import online.yourfit.data.exercises.Exercise;
import online.yourfit.data.exercises.ExerciseRepository;

public class WorkoutViewModel extends ViewModel {

    public static final int STATUS_NEW = 1;
    public static final int STATUS_IN_PROGRESS = 2;
    public static final int STATUS_PAUSED = 3;
    public static final int STATUS_FINISHED = 4;

    private CompositeDisposable compositeDisposable = new CompositeDisposable();
    private List<Exercise> allExercises = new ArrayList<>();
    private int nextExerciseId = 0;

    private MutableLiveData<String> appBarTitle = new MutableLiveData<>();
    private MutableLiveData<Integer> status = new MutableLiveData<>();
    private MutableLiveData<ArrayList<Exercise>> exercises = new MutableLiveData<>();

    public WorkoutViewModel() {
        this.appBarTitle.setValue("Новая тренировка");
        this.status.setValue(STATUS_NEW);
        this.exercises.setValue(new ArrayList<Exercise>());
        this.initExercises();
    }

    private void initExercises() {
        ExerciseRepository exerciseRepository = new ExerciseRepository();
        Flowable<List<Exercise>> observable = exerciseRepository.getAll();

        compositeDisposable.add(observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<Exercise>>() {
                    @Override
                    public void accept(List<Exercise> exercises) {
                        setupExercises(exercises);
                        Log.d("Workout", exercises.size() + " exercises set");
                    }
                }));
    }

    LiveData<String> getAppBarTitle() {
        return this.appBarTitle;
    }

    LiveData<Integer> getStatus() {
        return this.status;
    }

    LiveData<ArrayList<Exercise>> getExercises() {
        return this.exercises;
    }

    private void setupExercises(List<Exercise> exercises) {
        this.allExercises = exercises;
    }

    void startWorkout() {
        this.appBarTitle.setValue("Идет тренировка");
        this.status.setValue(STATUS_IN_PROGRESS);
    }

    void stopWorkout() {
        this.appBarTitle.setValue("Тренировка завершена");
        this.status.setValue(STATUS_FINISHED);
    }

    void addExercise() {
        ArrayList<Exercise> exercises = this.exercises.getValue();
        Exercise e = allExercises.get(nextExerciseId);
        if (exercises != null && e != null) {
            exercises.add(e);
            this.exercises.setValue(exercises);
            nextExerciseId++;
        }
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        compositeDisposable.clear();
    }
}
