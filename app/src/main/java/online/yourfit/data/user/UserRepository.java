package online.yourfit.data.user;

import android.app.Application;

import io.reactivex.Flowable;
import online.yourfit.data.user.local.UserLocalRepository;

public class UserRepository {

    private UserLocalRepository localRepository;

    public UserRepository(Application application) {
        localRepository = new UserLocalRepository(application);
    }

    public void insert(User user) {
        localRepository.insert(user);
    }

    public void update(User user) {
        localRepository.update(user);
    }

    public void delete(User user) {
        localRepository.delete(user);
    }

    public void deleteAll() {
        localRepository.deleteAll();
    }

    public Flowable<User> findById(int id) {
        return localRepository.findById(id);
    }
}
