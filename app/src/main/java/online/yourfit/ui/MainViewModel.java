package online.yourfit.ui;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import online.yourfit.data.user.User;
import online.yourfit.data.user.UserRepository;
import online.yourfit.data.workout.Workout;
import online.yourfit.data.workout.WorkoutRepository;

public class MainViewModel extends ViewModel {

    private static MainViewModel instance;

    private CompositeDisposable compositeDisposable = new CompositeDisposable();
    private UserRepository userRepository;
    private WorkoutRepository workoutRepository;

    private MutableLiveData<User> user = new MutableLiveData<>();
    private MutableLiveData<Workout> ongoingWorkout = new MutableLiveData<>();

    public MainViewModel() {
        this.userRepository = new UserRepository();
        this.workoutRepository = new WorkoutRepository();
    }

    public static MainViewModel getInstance() {
        if (instance == null) {
            instance = new MainViewModel();
        }
        return instance;
    }

    public LiveData<User> getUser() {
        if (user.getValue() == null) {
            Disposable disposable = this.userRepository.findById(17)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(u -> user.setValue(u));

            this.compositeDisposable.add(disposable);
        }

        return this.user;
    }

    public LiveData<Workout> getOngoingWorkout() {
        Disposable disposable = this.workoutRepository.findOngoingWorkout()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(workout -> ongoingWorkout.setValue(workout),
                        throwable -> ongoingWorkout.setValue(null));

        this.compositeDisposable.add(disposable);

        return ongoingWorkout;
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        this.compositeDisposable.clear();
    }
}
