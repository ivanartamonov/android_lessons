package online.yourfit.data.workout_history;

public class WorkoutHistoryItem {

    private String date;
    private String programName;
    private int duration;
    private int tonnage;

    public WorkoutHistoryItem(String date, String programName, int duration, int tonnage) {
        this.date = date;
        this.programName = programName;
        this.duration = duration;
        this.tonnage = tonnage;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getProgramName() {
        return programName;
    }

    public void setProgramName(String programName) {
        this.programName = programName;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public int getTonnage() {
        return tonnage;
    }

    public void setTonnage(int tonnage) {
        this.tonnage = tonnage;
    }


}
