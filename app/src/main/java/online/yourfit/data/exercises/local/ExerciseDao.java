package online.yourfit.data.exercises.local;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

import io.reactivex.Flowable;
import online.yourfit.data.exercises.Exercise;

@Dao
public interface ExerciseDao {

    @Query("SELECT * FROM exercise")
    Flowable<List<Exercise>> getAll();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Exercise exercise);
}
