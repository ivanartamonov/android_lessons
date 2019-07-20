package online.yourfit.data.exercises;

import android.util.Log;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Single;
import online.yourfit.data.exercises.local.ExerciseLocalRepository;
import online.yourfit.data.exercises.remote.ExerciseRemoteRepository;

public class ExerciseRepository {

    private ExerciseRemoteRepository remoteRepository;
    private ExerciseLocalRepository localRepository;

    public ExerciseRepository() {
        remoteRepository = new ExerciseRemoteRepository();
        localRepository = new ExerciseLocalRepository();
    }

    public Single<List<Exercise>> getAllLocal() {
        Log.d("ExerciseRepository", "getAllLocal");
        return localRepository.getAll();
    }

    public Flowable<List<Exercise>> getAllRemote() {
        Log.d("ExerciseRepository", "findByIdRemote");
        return remoteRepository.getAll().doOnNext(exercises -> {
            Log.d("ExerciseRepository", "Save in local DB");
            //localRepository.insert(exercises);
        });
    }

    public Flowable<List<Exercise>> getAll() {
        return this.getAllRemote();
        /*
        Log.d("ExerciseRepository", "findById");
        return this.getAllLocal().toFlowable()
                .onErrorResumeNext(throwable -> {
                    return getAllRemote();
                });

         */
    }

}
