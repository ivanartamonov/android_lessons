package online.yourfit.ui;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import online.yourfit.data.user.User;
import online.yourfit.data.user.UserRepository;

public class MainViewModel extends AndroidViewModel {

    private CompositeDisposable compositeDisposable = new CompositeDisposable();
    private UserRepository userRepository;

    private MutableLiveData<User> user = new MutableLiveData<>();

    MainViewModel(@NonNull Application application) {
        super(application);
        this.userRepository = new UserRepository(application);
    }

    public LiveData<User> getUser() {
        Log.d("UserRepository", "getUser");

        if (user.getValue() == null) {
            Log.d("UserRepository", "user is null");
            Disposable disposable = this.userRepository.findById(17)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(u -> {
                        Log.d("UserRepository", "Found in local db: " + u.getName());
                        user.setValue(u);
                    }, error -> {
                        Log.d("UserRepository", error.getMessage());
                    }, () -> {
                        Log.d("UserRepository", "On Complete");
                    });

            this.compositeDisposable.add(disposable);
        }

        return this.user;
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        this.compositeDisposable.clear();
    }
}
