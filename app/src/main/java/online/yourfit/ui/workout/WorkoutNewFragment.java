package online.yourfit.ui.workout;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import online.yourfit.R;
import online.yourfit.services.StartWorkout;

public class WorkoutNewFragment extends Fragment implements View.OnClickListener {

    private AppCompatActivity activity;

    private View root;
    private Button btnStartWorkout;
    private Button btnFinishWorkout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_workout_new, container, false);
        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        this.activity = (AppCompatActivity) getActivity();
        this.setActionBarTitle("Новая тренировка");
        this.initViews();
    }

    private void initViews() {
        this.btnStartWorkout = root.findViewById(R.id.btn_start_workout);
        this.btnFinishWorkout = root.findViewById(R.id.btn_stop_workout);

        btnStartWorkout.setOnClickListener(this);
        btnFinishWorkout.setOnClickListener(this);
    }

    private void setActionBarTitle(String title) {
        ActionBar actionBar = this.activity.getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle(title);
        }
    }

    @Override
    public void onClick(View v) {
        Intent intent;

        switch (v.getId()) {
            case R.id.btn_start_workout:
                intent = new Intent(this.activity, StartWorkout.class);
                intent.setAction(StartWorkout.ACTION_START);
                this.activity.startService(intent);
                break;
            case R.id.btn_stop_workout:
                intent = new Intent(this.activity, StartWorkout.class);
                intent.setAction(StartWorkout.ACTION_STOP);
                this.activity.stopService(intent);
                break;
        }
    }
}
