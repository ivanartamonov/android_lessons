package online.yourfit.di;

import android.content.Context;

import androidx.room.Room;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import online.yourfit.core.db.AppDatabase;
import online.yourfit.data.exercises.local.ExerciseDao;
import online.yourfit.data.programs.local.ProgramDao;
import online.yourfit.data.user.local.UserDao;

@Module
public class DataModule {

    private Context context;

    public DataModule(Context context) {
        this.context = context;
    }

    @Provides
    @Singleton
    public AppDatabase provideDatabase() {
        return Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, "database")
                .fallbackToDestructiveMigration()
                .build();
    }

    @Provides
    @Singleton
    public UserDao provideUserDao(AppDatabase appDatabase) {
        return appDatabase.userDao();
    }

    @Provides
    @Singleton
    public ProgramDao provideProgramDao(AppDatabase appDatabase) {
        return appDatabase.programDao();
    }

    @Provides
    @Singleton
    public ExerciseDao provideExerciseDao(AppDatabase appDatabase) {
        return appDatabase.exerciseDao();
    }
}
