package online.yourfit.ui.workout_results;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import online.yourfit.R;
import online.yourfit.ui.BaseFragment;
import online.yourfit.ui.workout.WorkoutViewModel;

public class WorkoutResultsFragment extends BaseFragment {

    public static final String ARG_WORKOUT_ID = "workoutId";

    private WorkoutResultsViewModel viewModel;
    private int workoutId;
    private View root;
    private TextView tvWorkoutInfo;

    public WorkoutResultsFragment() {
        viewModel = WorkoutResultsViewModel.getInstance();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_workout_results, container, false);

        Bundle bundle = getArguments();
        if (bundle != null) {
            workoutId = bundle.getInt(ARG_WORKOUT_ID);
        }
        initViews();
        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        this.setActionBarTitle("Результаты");
        startObserving();
    }

    private void initViews() {
        tvWorkoutInfo = root.findViewById(R.id.tv_workoutInfo);
    }

    private void startObserving() {
        viewModel.getWorkout(workoutId).observe(this, workout -> {
            tvWorkoutInfo.setText("Результаты тренировки №" + workout.getId());
        });
    }

}
