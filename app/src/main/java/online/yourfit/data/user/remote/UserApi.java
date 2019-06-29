package online.yourfit.data.user.remote;

import online.yourfit.data.user.User;

import retrofit2.Call;
import retrofit2.http.GET;

public interface UserApi {

    @GET("/api/user")
    Call<User> getUser();
}
