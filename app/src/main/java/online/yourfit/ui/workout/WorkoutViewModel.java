package online.yourfit.ui.workout;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.Date;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import online.yourfit.data.workout.Workout;
import online.yourfit.data.workout.WorkoutRepository;

public class WorkoutViewModel extends ViewModel {

    private static WorkoutViewModel instance;

    public static final int STATUS_NEW = 1;
    public static final int STATUS_IN_PROGRESS = 2;
    public static final int STATUS_PAUSED = 3;
    public static final int STATUS_FINISHED = 4;

    private CompositeDisposable compositeDisposable = new CompositeDisposable();
    private WorkoutRepository workoutRepository;

    private MutableLiveData<String> appBarTitle = new MutableLiveData<>();
    private MutableLiveData<Integer> status = new MutableLiveData<>();

    private WorkoutViewModel() {
        this.workoutRepository = new WorkoutRepository();
        this.status.setValue(STATUS_NEW);
    }

    public static WorkoutViewModel getInstance() {
        if (instance == null) {
            instance = new WorkoutViewModel();
        }
        return instance;
    }

    void startWorkout() {
        this.appBarTitle.setValue("Идет тренировка");
        this.status.setValue(STATUS_IN_PROGRESS);

        Workout ongoingWorkout = new Workout();
        Date startedAt = new Date();
        ongoingWorkout.setStartedAt(startedAt.getTime());

        Disposable disposable = this.workoutRepository.insert(ongoingWorkout)
            .subscribe(
                () -> Log.d("Workout", "onComplete"),
                throwable -> Log.d("Workout", "onError: " + throwable.getMessage())
            );

        compositeDisposable.add(disposable);
    }

    void stopWorkout() {
        this.appBarTitle.setValue("Тренировка завершена");
        this.status.setValue(STATUS_FINISHED);

        Disposable disposable = this.workoutRepository.findOngoingWorkout()
            .subscribe(workout -> {
                    workout.setFinishedAt(new Date().getTime());
                    workoutRepository.insert(workout).subscribe();
                }, throwable -> Log.d("Workout", "onError: " + throwable.getMessage())
            );

        compositeDisposable.add(disposable);
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        compositeDisposable.clear();
    }
}
