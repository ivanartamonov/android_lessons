package online.yourfit.ui.workout_results;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import online.yourfit.data.workout.Workout;
import online.yourfit.data.workout.WorkoutRepository;

public class WorkoutResultsViewModel extends ViewModel {

    private static WorkoutResultsViewModel instance;

    private CompositeDisposable compositeDisposable = new CompositeDisposable();
    private WorkoutRepository workoutRepository;

    private MutableLiveData<Workout> workout = new MutableLiveData<>();

    public WorkoutResultsViewModel() {
        workoutRepository = new WorkoutRepository();
    }

    public static WorkoutResultsViewModel getInstance() {
        if (instance == null) {
            instance = new WorkoutResultsViewModel();
        }
        return instance;
    }

    public LiveData<Workout> getWorkout(int id) {
        Disposable disposable = workoutRepository.findById(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(w -> workout.setValue(w));
        compositeDisposable.add(disposable);

        return workout;
    }

    @Override
    protected void onCleared() {
        compositeDisposable.clear();
        super.onCleared();
    }
}
