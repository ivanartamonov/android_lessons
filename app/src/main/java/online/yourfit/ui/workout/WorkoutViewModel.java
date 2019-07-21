package online.yourfit.ui.workout;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.Date;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import online.yourfit.data.workout.Workout;
import online.yourfit.data.workout.WorkoutRepository;
import online.yourfit.domain.WorkoutManager;

public class WorkoutViewModel extends ViewModel {

    public static final int STATUS_NEW = 1;
    public static final int STATUS_IN_PROGRESS = 2;
    public static final int STATUS_PAUSED = 3;
    public static final int STATUS_FINISHED = 4;

    private CompositeDisposable compositeDisposable = new CompositeDisposable();
    private WorkoutRepository workoutRepository;

    private MutableLiveData<String> appBarTitle = new MutableLiveData<>();
    private MutableLiveData<Integer> status = new MutableLiveData<>();

    public WorkoutViewModel() {
        this.workoutRepository = new WorkoutRepository();
        this.status.setValue(STATUS_NEW);
    }

    void startWorkout() {
        this.appBarTitle.setValue("Идет тренировка");
        this.status.setValue(STATUS_IN_PROGRESS);

        Log.d("Workout", "Inserting...");
        WorkoutManager workoutManager = WorkoutManager.startNewWorkout();
        Workout workout = workoutManager.getWorkout();

        /*
        workout.setStartedAt(123);
        workout.setDuration(100);
        workout.setId(2);
        workout.setProgramId(1);
        workout.setTonnage(100);*/

        Disposable disposable = this.workoutRepository.insert(workout).subscribe(() -> {
            Log.d("Workout", "onComplete");
        }, throwable -> {
            Log.d("Workout", "onError: " + throwable.getMessage());
        });

        compositeDisposable.add(disposable);
    }

    void stopWorkout() {
        this.appBarTitle.setValue("Тренировка завершена");
        this.status.setValue(STATUS_FINISHED);

        workoutRepository.deleteAll().subscribe();

        Disposable disposable = this.workoutRepository.findOngoingWorkout()
                .subscribe(workout -> {
                    workout.setFinishedAt(new Date().getTime());
                    workoutRepository.insert(workout);
                    workoutRepository.deleteOngoingWorkouts();
                    Log.d("Workout", "onComplete");
                }, throwable -> {
                    Log.d("Workout", "onError: " + throwable.getMessage());
                }
        );

        compositeDisposable.add(disposable);
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        compositeDisposable.clear();
    }
}
