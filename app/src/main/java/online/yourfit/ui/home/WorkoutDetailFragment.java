package online.yourfit.ui.home;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import online.yourfit.R;
import online.yourfit.data.workout.Workout;

public class WorkoutDetailFragment extends Fragment implements View.OnClickListener {

    private HomeViewModel viewModel;
    private int workoutId;
    private Workout currentWorkout;

    // Views
    private TextView tvWorkoutDate;
    private Button btnDelete;

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
            currentWorkout = workout;
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
        btnDelete = v.findViewById(R.id.btn_workout_detail_delete);
        btnDelete.setOnClickListener(this);
    }

    private void fillViews(Workout workout){
        tvWorkoutDate.setText("Finished: " + workout.getFinishedAt());
    }

    @Override
    public void onClick(View v) {
        viewModel.deleteWorkout(currentWorkout).observe(this, isDeleted -> {
            if (isDeleted) {
                deleteWorkout();
            }
        });
    }

    private void deleteWorkout() {
        Toast.makeText(getActivity(), "Запись удалена", Toast.LENGTH_LONG).show();
        NavController controller = NavHostFragment.findNavController(this);
        controller.popBackStack();
    }
}
