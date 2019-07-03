package online.yourfit.data.programs;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

@Entity
public class Program {

    public static final int LEVEL_BEGINNER = 1;
    public static final int LEVEL_MIDDLE = 2;
    public static final int LEVEL_PRO = 3;

    public static final int GOAL_MASS = 1;
    public static final int GOAL_WEIGHT_LOSS = 2;
    public static final int GOAL_GOOD_SHAPE = 3;

    public static final int AUDITORY_MALE = 1;
    public static final int AUDITORY_FEMALE = 2;
    public static final int AUDITORY_ALL = 3;

    @PrimaryKey
    @SerializedName("id")
    private int id;

    @SerializedName("title")
    private String title;

    @SerializedName("level")
    private int level;

    @SerializedName("goal")
    private int goal;

    @SerializedName("auditory")
    private int auditory;

    public Program(int id, String title, int level, int goal, int auditory) {
        this.id = id;
        this.title = title;
        this.level = level;
        this.goal = goal;
        this.auditory = auditory;
    }

    @Ignore
    public Program(String title) {
        this.title = title;
    }

    @Ignore
    public Program(String title, int level, int goal, int auditory) {
        this.title = title;
        this.level = level;
        this.goal = goal;
        this.auditory = auditory;
    }

    public int getId() {
        return this.id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getLevelName() {
        switch (this.level) {
            case LEVEL_BEGINNER: return "Новичок";
            case LEVEL_MIDDLE: return "Любитель";
            case LEVEL_PRO: return "Профессионал";
            default: return "Неизвестно";
        }
    }

    public int getGoal() {
        return goal;
    }

    public void setGoal(int goal) {
        this.goal = goal;
    }

    public String getGoalName() {
        switch (this.level) {
            case GOAL_WEIGHT_LOSS: return "Похудение";
            case GOAL_MASS: return "Набор массы";
            case GOAL_GOOD_SHAPE: return "Поддержание формы";
            default: return "Неизвестно";
        }
    }

    public int getAuditory() {
        return auditory;
    }

    public void setAuditory(int auditory) {
        this.auditory = auditory;
    }

    public String getAuditoryName() {
        switch (this.level) {
            case AUDITORY_MALE: return "Для мужчин";
            case AUDITORY_FEMALE: return "Для женщин";
            case AUDITORY_ALL: return "Для всех";
            default: return "Неизвестно";
        }
    }
}
