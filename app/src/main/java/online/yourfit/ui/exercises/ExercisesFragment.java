package online.yourfit.ui.exercises;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import online.yourfit.R;
import online.yourfit.ui.BaseFragment;
import online.yourfit.ui.workout.WorkoutViewModel;

public class ExercisesFragment extends BaseFragment implements ExercisesAdapter.ExercisesAdapterListener {

    public static final String ACTION_ARG = "Action";
    public static final int ACTION_EXPLORE = 1;
    public static final int ACTION_CHOOSE_EXERCISE = 2;

    private View root;
    private ExercisesAdapter adapter;
    private ExercisesViewModel exercisesViewModel;
    private int currentAction;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        exercisesViewModel = ViewModelProviders.of(this).get(ExercisesViewModel.class);
        currentAction = ACTION_EXPLORE;

        Bundle bundle = getArguments();
        if (bundle != null) {
            currentAction = bundle.getInt(ACTION_ARG);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_exercises, container, false);

        this.initViews();
        this.setupObserving();

        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (currentAction == ACTION_CHOOSE_EXERCISE) {
            this.setActionBarTitle("Выберите упражнение");
            this.removeUpButton();
        }
    }

    private void initViews() {
        RecyclerView recyclerView = root.findViewById(R.id.exercises_recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new ExercisesAdapter(this);
        recyclerView.setAdapter(adapter);
    }

    private void setupObserving() {
        this.exercisesViewModel.getExercises()
                .observe(this, exercises -> adapter.setItems(exercises));
    }

    @Override
    public void onExerciseSelected(int id) {
        if (currentAction == ACTION_CHOOSE_EXERCISE) {
            WorkoutViewModel workoutViewModel = WorkoutViewModel.getInstance();
            workoutViewModel.addDoingExerciseById(id);
            Toast.makeText(getActivity(), "Add " + id, Toast.LENGTH_SHORT).show();
            return;
        }

        NavController controller = NavHostFragment.findNavController(this);
        Bundle args = new Bundle();
        args.putInt("exerciseId", id);
        controller.navigate(R.id.nav_exercise_detail, args);
    }
}
