package online.yourfit.core;

import online.yourfit.data.exercises.remote.ExercisesApi;
import online.yourfit.data.user.remote.UserApi;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class NetworkService {

    private static NetworkService instance;

    private static final String BASE_URL = "https://yourfit.online";
    private Retrofit retrofit;

    private NetworkService() {
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    public static NetworkService getInstance() {
        if (instance == null) {
            instance = new NetworkService();
        }
        return instance;
    }

    public UserApi getUserApi() {
        return retrofit.create(UserApi.class);
    }

    public ExercisesApi getExercisesApi() {
        return retrofit.create(ExercisesApi.class);
    }

}
