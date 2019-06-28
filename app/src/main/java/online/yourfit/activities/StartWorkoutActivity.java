package online.yourfit.activities;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import online.yourfit.R;
import online.yourfit.services.StartWorkout;

public class StartWorkoutActivity extends AppCompatActivity implements View.OnClickListener {

    Button btnStartWorkout;
    Button btnFinishWorkout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_workout);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle("Новая тренировка");
        }

        this.initViews();
    }

    private void initViews() {
        this.btnStartWorkout = findViewById(R.id.btn_start_workout);
        this.btnFinishWorkout = findViewById(R.id.btn_stop_workout);

        btnStartWorkout.setOnClickListener(this);
        btnFinishWorkout.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent;

        switch (v.getId()) {
            case R.id.btn_start_workout:
                intent = new Intent(this, StartWorkout.class);
                intent.setAction(StartWorkout.ACTION_START);
                startService(intent);
                break;
            case R.id.btn_stop_workout:
                intent = new Intent(this, StartWorkout.class);
                intent.setAction(StartWorkout.ACTION_STOP);
                stopService(intent);
                break;
        }
    }
}
