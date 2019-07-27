package online.yourfit.ui.home;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import online.yourfit.R;
import online.yourfit.data.workout.Workout;

public class WorkoutDetailFragment extends Fragment {

    private HomeViewModel viewModel;
    private int workoutId;
    private TextView tvWorkoutDate;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        if (bundle == null) {
            throw new RuntimeException("Invalid ID");
        }

        workoutId = bundle.getInt(Workout.ARG_WORKOUT_ID, -1);
        if (workoutId < 0) {
            throw new RuntimeException("Invalid ID");
        }

        viewModel = HomeViewModel.getInstance();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_workout_detail, container, false);
        initViews(v);
        return v;
    }

    private void startObserving() {
        Log.d("Workout", "Start observing");
        viewModel.getWorkoutById(workoutId).observe(this, workout -> {
            Log.d("Workout", "FOUND: " + workout.getId());
            fillViews(workout);
        });
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        startObserving();
    }

    private void initViews(View v){
        tvWorkoutDate = v.findViewById(R.id.tv_workout_detail_date);
    }

    private void fillViews(Workout workout){
        tvWorkoutDate.setText("Finished: " + workout.getFinishedAt());
    }
}
