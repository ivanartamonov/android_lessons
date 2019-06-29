package online.yourfit.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import online.yourfit.R;
import online.yourfit.data.workout_history.WorkoutHistoryManager;
import online.yourfit.data.workout_history.WorkoutHistoryItem;

public class AddWorkoutActivity extends AppCompatActivity implements View.OnClickListener {

    EditText tvWorkoutDate;
    EditText tvWorkoutProgramName;
    EditText tvWorkoutDuration;
    EditText tvWorkoutTonnage;
    Button addWorkout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_workout);

        this.tvWorkoutDate = findViewById(R.id.tv_workout_date);
        this.tvWorkoutProgramName = findViewById(R.id.tv_workout_program_name);
        this.tvWorkoutDuration = findViewById(R.id.tv_workout_duration);
        this.tvWorkoutTonnage = findViewById(R.id.tv_workout_tonnage);
        this.addWorkout = findViewById(R.id.btn_workout_add);

        addWorkout.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        Intent intent = new Intent();

        String date = tvWorkoutDate.getText().toString();
        String programName = tvWorkoutProgramName.getText().toString();
        int duration = Integer.parseInt(tvWorkoutDuration.getText().toString());
        int tonnage = Integer.parseInt(tvWorkoutTonnage.getText().toString());

        WorkoutHistoryItem item = new WorkoutHistoryItem(date, programName, duration, tonnage);
        WorkoutHistoryManager.getList().add(item);

        setResult(RESULT_OK, intent);
        finish();
    }
}
