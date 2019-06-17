package online.yourfit.network;

import online.yourfit.models.User;

import retrofit2.Call;
import retrofit2.http.GET;

public interface JSONPlaceHolderApi {

    @GET("/api/user")
    public Call<User> getUser();

}
