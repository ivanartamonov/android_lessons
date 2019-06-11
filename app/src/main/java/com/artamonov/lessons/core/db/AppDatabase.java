package com.artamonov.lessons.core.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.artamonov.lessons.data.user.UserDao;
import com.artamonov.lessons.models.User;

@Database(entities = {User.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract UserDao userDao();
}
