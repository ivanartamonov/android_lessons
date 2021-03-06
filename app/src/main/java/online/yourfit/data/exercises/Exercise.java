package online.yourfit.data.exercises;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

@Entity
public class Exercise {

    public static final int TYPE_BASE = 1;
    public static final int TYPE_ISOLATED = 2;

    @PrimaryKey
    @SerializedName("id")
    private int id;

    @SerializedName("name")
    private String name;

    @SerializedName("type")
    private int type;

    @SerializedName("primaryImgUrl")
    private String primaryImgUrl;

    @SerializedName("secondaryImgUrl")
    private String secondaryImgUrl;

    @SerializedName("mainMuscleId")
    private int mainMuscleId;

    @SerializedName("muscleGroupId")
    private int muscleGroupId;

    public Exercise(String name, int type, String primaryImgUrl, String secondaryImgUrl) {
        this.name = name;
        this.type = type;
        this.primaryImgUrl = primaryImgUrl;
        this.secondaryImgUrl = secondaryImgUrl;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getTypeName() {
        if (this.getType() == TYPE_BASE) {
            return "Базовое";
        } else if (this.getType() == Exercise.TYPE_ISOLATED) {
            return "Изолирующее";
        }
        return "";
    }

    public String getPrimaryImgUrl() {
        return primaryImgUrl;
    }

    public void setPrimaryImgUrl(String primaryImgUrl) {
        this.primaryImgUrl = primaryImgUrl;
    }

    public String getSecondaryImgUrl() {
        return secondaryImgUrl;
    }

    public void setSecondaryImgUrl(String secondaryImgUrl) {
        this.secondaryImgUrl = secondaryImgUrl;
    }

    public int getMainMuscleId() {
        return mainMuscleId;
    }

    public void setMainMuscleId(int mainMuscleId) {
        this.mainMuscleId = mainMuscleId;
    }

    public int getMuscleGroupId() {
        return muscleGroupId;
    }

    public void setMuscleGroupId(int muscleGroupId) {
        this.muscleGroupId = muscleGroupId;
    }
}
