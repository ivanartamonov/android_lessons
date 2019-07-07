package online.yourfit.data.user.remote;

import io.reactivex.Flowable;
import io.reactivex.schedulers.Schedulers;
import online.yourfit.core.NetworkService;
import online.yourfit.data.user.User;

public class UserRemoteRepository {

    private UserApi userApi;

    public UserRemoteRepository() {
        userApi = NetworkService.getInstance().getUserApi();
    }

    public Flowable<User> findById(int id) {
        return userApi.getUser()
                .subscribeOn(Schedulers.io());
    }
}
