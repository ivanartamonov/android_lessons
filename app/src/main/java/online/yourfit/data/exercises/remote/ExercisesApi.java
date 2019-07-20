package online.yourfit.data.exercises.remote;

import java.util.List;

import io.reactivex.Flowable;
import online.yourfit.data.exercises.Exercise;
import retrofit2.http.GET;

public interface ExercisesApi {
    @GET("/api/exercise")
    Flowable<List<Exercise>> getExercises();
}
