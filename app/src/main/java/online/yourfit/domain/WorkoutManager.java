package online.yourfit.domain;

import java.util.Date;

import online.yourfit.data.workout.Workout;

public class WorkoutManager {

    private Workout workout;

    public WorkoutManager(Workout ongoingWorkout) {
        this.workout = ongoingWorkout;
    }

    public static WorkoutManager startNewWorkout() {
        Workout workout = new Workout();
        Date startedAt = new Date();
        workout.setStartedAt(startedAt.getTime());

        return new WorkoutManager(workout);
    }

    public void finishWorkout() {
        this.workout.setFinishedAt(new Date().getTime());
    }

    public Workout getWorkout() {
        return this.workout;
    }

}
