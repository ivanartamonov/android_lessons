package online.yourfit.data.user.local;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import io.reactivex.Flowable;
import io.reactivex.Single;
import online.yourfit.data.user.User;

import java.util.List;

@Dao
public interface UserDao {

    @Query("SELECT * FROM user")
    Flowable<List<User>> getAll();

    @Query("SELECT * FROM user WHERE id = :id")
    Single<User> getById(long id);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(User user);

    @Update
    void update(User user);

    @Delete
    void delete(User user);

    @Query("DELETE FROM user")
    void deleteAll();

}
