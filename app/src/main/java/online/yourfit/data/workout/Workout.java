package online.yourfit.data.workout;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
public class Workout {

    public static final String ARG_WORKOUT_ID = "workoutId";

    @PrimaryKey(autoGenerate = true)
    @SerializedName("id")
    private int id;

    @SerializedName("startedAt")
    private long startedAt;

    @SerializedName("finishedAt")
    private long finishedAt;

    @SerializedName("duration")
    private long duration;

    @SerializedName("programId")
    private int programId;

    @SerializedName("tonnage")
    private int tonnage;

    public String getWorkoutDate() {
        Timestamp timestamp = new Timestamp(this.startedAt);
        Date date = new Date(timestamp.getTime());
        return new SimpleDateFormat("dd MMM, HH:mm:ss").format(date);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getStartedAt() {
        return startedAt;
    }

    public void setStartedAt(long startedAt) {
        this.startedAt = startedAt;
    }

    public long getFinishedAt() {
        return finishedAt;
    }

    public void setFinishedAt(long finishedAt) {
        this.finishedAt = finishedAt;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }

    public int getProgramId() {
        return programId;
    }

    public void setProgramId(int programId) {
        this.programId = programId;
    }

    public int getTonnage() {
        return tonnage;
    }

    public void setTonnage(int tonnage) {
        this.tonnage = tonnage;
    }

    public boolean isFinished() {
        return this.finishedAt > 0;
    }

    public long getDuration() {
        if (this.duration == 0) {
            if (this.finishedAt == 0) {
                this.finishedAt = new Date().getTime();
            }
            this.duration = this.finishedAt - this.startedAt;
        }
        return this.duration;
    }

}
