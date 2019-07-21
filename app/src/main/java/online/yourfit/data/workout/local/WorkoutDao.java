package online.yourfit.data.workout.local;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Single;
import online.yourfit.data.workout.Workout;

@Dao
public interface WorkoutDao {

    @Query("SELECT * FROM workout ORDER BY id DESC")
    Flowable<List<Workout>> getAll();

    @Query("SELECT * FROM workout WHERE finishedAt = 0")
    Single<Workout> findOngoing();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Workout workout);

    @Query("DELETE FROM workout WHERE finishedAt = 0")
    void deleteOngoingWorkouts();

    @Query("DELETE FROM workout")
    void deleteAll();
}
