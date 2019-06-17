package online.yourfit.core.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import online.yourfit.user.UserDao;
import online.yourfit.models.User;

@Database(entities = {User.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract UserDao userDao();
}
