package online.yourfit.ui.home;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.LiveDataReactiveStreams;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import online.yourfit.data.blogs.BlogPost;
import online.yourfit.data.blogs.BlogRepository;
import online.yourfit.data.user.User;
import online.yourfit.data.user.UserRepository;
import online.yourfit.data.workout.Workout;
import online.yourfit.data.workout.WorkoutRepository;

public class HomeViewModel extends ViewModel {

    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    private static HomeViewModel instance;

    private UserRepository userRepository;
    private WorkoutRepository workoutRepository;
    private BlogRepository blogRepository;

    private MutableLiveData<List<Workout>> lastWorkouts = new MutableLiveData<>();

    public HomeViewModel() {
        this.userRepository = new UserRepository();
        this.workoutRepository = new WorkoutRepository();
        this.blogRepository = new BlogRepository();
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
        Disposable disposable = workoutRepository.getFinished(20)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(workouts -> this.lastWorkouts.setValue(workouts));

        compositeDisposable.add(disposable);

        return this.lastWorkouts;
    }

    public LiveData<Workout> getWorkoutById(int id) {
        MutableLiveData<Workout> workout = new MutableLiveData<>();

        Log.d("Workout", "getWorkoutById: " + id);

        Disposable disposable = workoutRepository.findById(id)
                .map(w -> {
                    Log.d("Workout", "map: " + w.getId());
                    return w;
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(w -> workout.setValue(w));

        compositeDisposable.add(disposable);

        return workout;
    }

    public LiveData<Boolean> deleteWorkout(Workout workout) {
        MutableLiveData<Boolean> isDeleted = new MutableLiveData<>();
        isDeleted.setValue(false);

        Disposable disposable = workoutRepository.delete(workout)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(() -> isDeleted.setValue(true));

        compositeDisposable.add(disposable);

        return isDeleted;
    }

    public LiveData<List<BlogPost>> getBlogPosts() {
        MutableLiveData<List<BlogPost>> blogPosts = new MutableLiveData<>();

        Disposable disposable = blogRepository.getAll()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(list -> blogPosts.setValue(list));

        compositeDisposable.add(disposable);

        return blogPosts;
    }

    @Override
    protected void onCleared() {
        compositeDisposable.clear();
        super.onCleared();
    }
}
