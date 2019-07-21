package online.yourfit.ui.workout;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import online.yourfit.R;
import online.yourfit.services.workout.WorkoutService;
import online.yourfit.ui.BaseFragment;

public class WorkoutStartFragment extends BaseFragment implements View.OnClickListener {

    private WorkoutViewModel viewModel;

    private View root;
    private Button btnStartWorkout;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.viewModel = ViewModelProviders.of(this).get(WorkoutViewModel.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_workout_new, container, false);
        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        this.setActionBarTitle("Новая тренировка");
        this.initViews();
    }


    private void initViews() {
        this.btnStartWorkout = root.findViewById(R.id.btn_start_workout);
        btnStartWorkout.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_start_workout:
                this.startWorkout();
                NavController controller = NavHostFragment.findNavController(this);
                controller.navigate(R.id.workoutProcessFragment);
                break;
        }
    }

    public void startWorkout() {
        this.viewModel.startWorkout();
        Intent intent = new Intent(this.activity, WorkoutService.class);
        intent.setAction(WorkoutService.ACTION_START);
        this.activity.startService(intent);
    }
}
