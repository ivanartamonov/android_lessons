package online.yourfit.data.programs.local;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import online.yourfit.data.programs.Program;
import online.yourfit.data.user.User;

@Dao
public interface ProgramDao {

    @Query("SELECT * FROM program")
    LiveData<List<Program>> getAll();

    @Query("SELECT * FROM program WHERE id = :id")
    LiveData<Program> getById(long id);

    @Insert
    void insert(Program program);

    @Update
    void update(Program program);

    @Delete
    void delete(Program program);

    @Query("DELETE FROM program")
    void deleteAll();

    @Query("DELETE FROM program WHERE id = :id")
    void deleteById(int id);
}
