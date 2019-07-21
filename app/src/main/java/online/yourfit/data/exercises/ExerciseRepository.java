package online.yourfit.data.exercises;

import java.util.List;

import io.reactivex.Flowable;
import online.yourfit.data.exercises.local.ExerciseLocalRepository;
import online.yourfit.data.exercises.remote.ExerciseRemoteRepository;

public class ExerciseRepository {

    private ExerciseRemoteRepository remoteRepository;
    private ExerciseLocalRepository localRepository;

    public ExerciseRepository() {
        remoteRepository = new ExerciseRemoteRepository();
        localRepository = new ExerciseLocalRepository();
    }

    public Flowable<Exercise> findById(int id) {
        return localRepository.findById(id).toFlowable();
    }

    public Flowable<List<Exercise>> getAll() {
        return this.getAllLocal()
                .map(list -> {
                    if(list.size() == 0) throw new Exception();
                    return list;
                })
                .onErrorResumeNext(throwable -> {
                    return getAllRemote();
                });
    }

    private Flowable<List<Exercise>> getAllLocal() {
        return localRepository.getAll();
    }

    private Flowable<List<Exercise>> getAllRemote() {
        return remoteRepository.getAll()
                .flatMap(exercises -> localRepository.insertAll(exercises)
                        .andThen(localRepository.getAll())
                );
    }

}
