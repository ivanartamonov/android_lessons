package online.yourfit.data.user.remote;

import io.reactivex.Flowable;
import online.yourfit.data.user.User;

import retrofit2.http.GET;

public interface UserApi {

    @GET("/api/user")
    Flowable<User> getUser();
}
