package online.yourfit.ui.workout;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import online.yourfit.R;
import online.yourfit.data.exercises.Exercise;
import online.yourfit.data.workout.Workout;
import online.yourfit.ui.BaseFragment;

public class WorkoutExerciseFragment extends BaseFragment implements View.OnClickListener {

    public static final String ARG_EXERCISE_ID = "exerciseId";

    private WorkoutViewModel workoutViewModel;
    private int exerciseId;
    private View root;
    private Button btnDelete;

    public WorkoutExerciseFragment() {
        workoutViewModel = WorkoutViewModel.getInstance();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_workout_exercise, container, false);

        Bundle bundle = getArguments();
        if (bundle != null) {
            exerciseId = bundle.getInt(ARG_EXERCISE_ID);
        }

        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        this.initViews();
        this.startObserving();
    }

    private void initViews() {
        btnDelete = root.findViewById(R.id.btn_deleteExercise);
        btnDelete.setOnClickListener(this);
    }

    public void startObserving() {
        Exercise exercise = workoutViewModel.getExerciseById(exerciseId);
        this.setActionBarTitle(exercise.getName());
    }

    @Override
    public void onClick(View v) {
        workoutViewModel.deleteDoingExerciseById(exerciseId);
        Toast.makeText(getActivity(), "Упражнение удалено", Toast.LENGTH_LONG).show();
        NavController controller = NavHostFragment.findNavController(this);
        controller.popBackStack();
    }
}
