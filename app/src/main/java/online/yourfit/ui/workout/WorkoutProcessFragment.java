package online.yourfit.ui.workout;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProviders;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import online.yourfit.R;
import online.yourfit.services.workout.WorkoutService;
import online.yourfit.ui.BaseFragment;

public class WorkoutProcessFragment extends BaseFragment implements View.OnClickListener {

    private WorkoutViewModel viewModel;

    private View root;
    private Button btnStopWorkout;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.viewModel = ViewModelProviders.of(this).get(WorkoutViewModel.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_workout_process, container, false);
        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        this.setActionBarTitle("Идёт тренировка");
        this.initViews();
    }

    private void initViews() {
        this.btnStopWorkout = root.findViewById(R.id.btn_stop_workout);
        btnStopWorkout.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_stop_workout:
                this.stopWorkout();
                break;
        }
    }

    private void stopWorkout() {
        this.viewModel.stopWorkout();
        Intent intent = new Intent(this.activity, WorkoutService.class);
        intent.setAction(WorkoutService.ACTION_STOP);
        this.activity.stopService(intent);
    }
}
