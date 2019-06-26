package online.yourfit.network;

import java.util.List;

import io.reactivex.Observable;
import online.yourfit.models.Exercise;
import online.yourfit.models.User;

import retrofit2.Call;
import retrofit2.http.GET;

public interface JSONPlaceHolderApi {

    @GET("/api/user")
    Call<User> getUser();

    @GET("/api/exercise")
    Observable<List<Exercise>> getExercises();
}
