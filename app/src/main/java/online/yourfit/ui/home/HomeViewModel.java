package online.yourfit.ui.home;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.LiveDataReactiveStreams;

import online.yourfit.data.user.User;
import online.yourfit.data.user.UserRepository;

public class HomeViewModel extends AndroidViewModel {

    private UserRepository userRepository;

    public HomeViewModel(@NonNull Application application) {
        super(application);
        this.userRepository = new UserRepository();
    }

    public LiveData<User> getUser() {
        return LiveDataReactiveStreams.fromPublisher(userRepository.findById(17));
    }
}
