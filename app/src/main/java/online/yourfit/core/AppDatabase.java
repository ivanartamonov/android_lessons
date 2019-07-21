package online.yourfit.core;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import online.yourfit.data.exercises.Exercise;
import online.yourfit.data.exercises.local.ExerciseDao;
import online.yourfit.data.programs.Program;
import online.yourfit.data.programs.local.ProgramDao;
import online.yourfit.data.user.local.UserDao;
import online.yourfit.data.user.User;

@Database(entities = {User.class, Program.class, Exercise.class}, version = 4)
public abstract class AppDatabase extends RoomDatabase {

    public abstract UserDao userDao();

    public abstract ProgramDao programDao();

    public abstract ExerciseDao exerciseDao();
}
