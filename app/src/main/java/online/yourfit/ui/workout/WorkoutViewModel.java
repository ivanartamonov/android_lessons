package online.yourfit.ui.workout;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

class WorkoutViewModel extends AndroidViewModel {

    public static final int STATUS_NEW = 1;
    public static final int STATUS_IN_PROGRESS = 2;
    public static final int STATUS_PAUSED = 3;
    public static final int STATUS_FINISHED = 4;

    private MutableLiveData<String> appBarTitle = new MutableLiveData<>();
    private MutableLiveData<Integer> status = new MutableLiveData<>();

    WorkoutViewModel(@NonNull Application application) {
        super(application);
        this.appBarTitle.setValue("Новая тренировка");
        this.status.setValue(STATUS_NEW);
    }

    LiveData<String> getAppBarTitle() {
        return this.appBarTitle;
    }

    LiveData<Integer> getStatus() {
        return this.status;
    }

    void startWorkout() {
        this.appBarTitle.setValue("Идет тренировка");
        this.status.setValue(STATUS_IN_PROGRESS);
    }

    void stopWorkout() {
        this.appBarTitle.setValue("Тренировка завершена");
        this.status.setValue(STATUS_FINISHED);
    }
}
