package online.yourfit.data.user;

import android.app.Application;
import android.util.Log;

import io.reactivex.Flowable;
import io.reactivex.Single;
import online.yourfit.data.user.local.UserLocalRepository;
import online.yourfit.data.user.remote.UserRemoteRepository;

public class UserRepository {

    public static final String TAG = "UserRepository";

    private UserLocalRepository localRepository;
    private UserRemoteRepository remoteRepository;

    public UserRepository(Application application) {
        localRepository = new UserLocalRepository(application);
        remoteRepository = new UserRemoteRepository();
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

    public Single<User> findByIdLocal(int id) {
        return localRepository.findById(id);
    }

    public Flowable<User> findByIdRemote(int id) {
        Log.d("UserRepository", "findByIdRemote");
        return remoteRepository.findById(id).doOnNext(user -> {
            Log.d(TAG, "Save in local DB");
            localRepository.insert(user);
        });
    }

    public Flowable<User> findById(int id) {
        Log.d("UserRepository", "findById");
        return this.findByIdLocal(id).toFlowable()
                .onErrorResumeNext(throwable -> {
                    return findByIdRemote(id);
                });
    }
}
