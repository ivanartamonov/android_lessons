package online.yourfit.data.workout.local;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

import io.reactivex.Flowable;
import online.yourfit.data.workout.Workout;

@Dao
public interface WorkoutDao {

    @Query("SELECT * FROM workout WHERE id = :id")
    Flowable<Workout> findById(int id);

    @Query("SELECT * FROM workout ORDER BY id DESC")
    Flowable<List<Workout>> getAll();

    @Query("SELECT * FROM workout WHERE finishedAt > 0 ORDER BY id DESC LIMIT :limit")
    Flowable<List<Workout>> getFinished(int limit);

    @Query("SELECT * FROM workout WHERE finishedAt = 0")
    Flowable<Workout> findOngoing();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Long insert(Workout workout);

    @Query("DELETE FROM workout WHERE finishedAt = 0")
    void deleteOngoingWorkouts();

    @Query("DELETE FROM workout")
    void deleteAll();

    @Delete
    void delete(Workout workout);
}
