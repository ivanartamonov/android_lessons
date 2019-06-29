package online.yourfit.ui;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import online.yourfit.data.user.User;
import online.yourfit.data.user.UserRepository;

public class MainViewModel extends AndroidViewModel {

    private UserRepository userRepository;

    public MainViewModel(@NonNull Application application) {
        super(application);
        this.userRepository = new UserRepository(application);
    }

    public LiveData<User> getUser() {
        return userRepository.findById(17);
    }

}
