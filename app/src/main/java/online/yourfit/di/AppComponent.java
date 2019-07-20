package online.yourfit.di;

import javax.inject.Singleton;

import dagger.Component;
import online.yourfit.data.exercises.local.ExerciseLocalRepository;
import online.yourfit.data.exercises.remote.ExerciseRemoteRepository;
import online.yourfit.data.programs.local.ProgramLocalRepository;
import online.yourfit.data.user.local.UserLocalRepository;
import online.yourfit.data.user.remote.UserRemoteRepository;

@Singleton
@Component(modules = {NetworkModule.class, DataModule.class})
public interface AppComponent {
    void inject(UserLocalRepository userLocalRepository);
    void inject(UserRemoteRepository userRemoteRepository);
    void inject(ExerciseLocalRepository exerciseLocalRepository);
    void inject(ExerciseRemoteRepository exerciseRemoteRepository);

    void inject(ProgramLocalRepository programLocalRepository);
}
