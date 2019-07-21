package online.yourfit.ui.exercises;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import online.yourfit.data.exercises.Exercise;
import online.yourfit.data.exercises.ExerciseRepository;

public class ExercisesViewModel extends ViewModel {

    public static final String TAG = "ExercisesViewModel";

    private CompositeDisposable compositeDisposable = new CompositeDisposable();
    private ExerciseRepository exerciseRepository;

    // Data
    private MutableLiveData<List<Exercise>> exercisesList = new MutableLiveData<>();
    private MutableLiveData<Exercise> currentExercise = new MutableLiveData<>();

    public ExercisesViewModel() {
        Log.d(TAG, "Constructor ExercisesViewModel");
        exerciseRepository = new ExerciseRepository();
        this.initExercises();
    }

    private void initExercises() {
        Log.d(TAG, "initExercises");

        exercisesList.setValue(new ArrayList<>());

        Disposable disposable = exerciseRepository.getAll()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(exercises -> exercisesList.setValue(exercises));

        compositeDisposable.add(disposable);
    }

    LiveData<List<Exercise>> getExercises() {
        return this.exercisesList;
    }

    public void initExerciseById(int id) {
        Log.d(TAG, "initExerciseById: " + id);
        Disposable disposable = exerciseRepository.findById(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(exercise -> currentExercise.setValue(exercise));

        compositeDisposable.add(disposable);
    }

    LiveData<Exercise> getCurrentExercise() {
        return this.currentExercise;
    }

    @Override
    protected void onCleared() {
        compositeDisposable.clear();
        super.onCleared();
    }
}
