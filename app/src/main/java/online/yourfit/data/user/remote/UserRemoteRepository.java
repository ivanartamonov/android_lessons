package online.yourfit.data.user.remote;

import javax.inject.Inject;

import io.reactivex.Flowable;
import io.reactivex.schedulers.Schedulers;
import online.yourfit.core.App;
import online.yourfit.data.user.User;

public class UserRemoteRepository {

    @Inject
    UserApi userApi;

    public UserRemoteRepository() {
        App.instance.getAppComponent().inject(this);
    }

    public Flowable<User> findById(int id) {
        return userApi.getUser()
                .subscribeOn(Schedulers.io());
    }
}
