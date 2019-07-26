package online.yourfit.ui.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.LiveDataReactiveStreams;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import online.yourfit.data.user.User;
import online.yourfit.data.user.UserRepository;
import online.yourfit.data.workout.Workout;
import online.yourfit.data.workout.WorkoutRepository;

public class HomeViewModel extends ViewModel {

    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    private static HomeViewModel instance;

    private UserRepository userRepository;
    private WorkoutRepository workoutRepository;

    private MutableLiveData<List<Workout>> lastWorkouts = new MutableLiveData<>();

    public HomeViewModel() {
        this.userRepository = new UserRepository();
        this.workoutRepository = new WorkoutRepository();
    }

    public static HomeViewModel getInstance() {
        if (instance == null) {
            instance = new HomeViewModel();
        }
        return instance;
    }

    public LiveData<User> getUser() {
        return LiveDataReactiveStreams.fromPublisher(userRepository.findById(17));
    }

    public LiveData<List<Workout>> getLastWorkouts() {
        Disposable disposable = workoutRepository.getAll()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(workouts -> {
                    this.lastWorkouts.setValue(workouts);
                });

        compositeDisposable.add(disposable);

        return this.lastWorkouts;
    }

    @Override
    protected void onCleared() {
        compositeDisposable.clear();
        super.onCleared();
    }
}
