package online.yourfit.data.user.remote;

import io.reactivex.Observable;
import online.yourfit.core.NetworkService;
import online.yourfit.data.user.User;

public class UserRemoteRepository {

    private UserApi userApi;

    public UserRemoteRepository() {
        userApi = NetworkService.getInstance().getUserApi();
    }

    public Observable<User> findById(int id) {
        return userApi.getUser();
    }
}
