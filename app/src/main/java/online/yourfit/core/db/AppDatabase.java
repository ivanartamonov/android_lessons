package online.yourfit.core.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import online.yourfit.data.programs.Program;
import online.yourfit.data.programs.local.ProgramDao;
import online.yourfit.data.user.local.UserDao;
import online.yourfit.data.user.User;

@Database(entities = {User.class, Program.class}, version = 2)
public abstract class AppDatabase extends RoomDatabase {

    private static AppDatabase instance;

    public static synchronized AppDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, "database")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }

    public abstract UserDao userDao();

    public abstract ProgramDao programDao();
}
