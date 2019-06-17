package online.yourfit.ui.home;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import online.yourfit.models.User;
import online.yourfit.user.UserRepository;

public class HomeViewModel extends AndroidViewModel {

    private UserRepository userRepository;

    public HomeViewModel(@NonNull Application application) {
        super(application);
        this.userRepository = new UserRepository(application);
    }

    public LiveData<User> getUser() {
        return userRepository.findById(17);
    }
}
