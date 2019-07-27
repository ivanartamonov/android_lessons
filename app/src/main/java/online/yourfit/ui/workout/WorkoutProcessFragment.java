package online.yourfit.ui.workout;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
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
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import online.yourfit.R;
import online.yourfit.services.workout.WorkoutService;
import online.yourfit.ui.BaseFragment;
import online.yourfit.ui.exercises.ExercisesAdapter;
import online.yourfit.ui.exercises.ExercisesFragment;

public class WorkoutProcessFragment extends BaseFragment implements View.OnClickListener,
        ExercisesAdapter.ExercisesAdapterListener {

    private WorkoutViewModel viewModel;
    private ExercisesAdapter adapter;

    private View root;
    private Button btnStopWorkout;
    private FloatingActionButton fabAddExercise;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        this.viewModel = WorkoutViewModel.getInstance();
        this.activity = (AppCompatActivity) getActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_workout_process, container, false);
        this.initViews();
        this.startObserving();
        return root;
    }

    private void initViews() {
        this.btnStopWorkout = root.findViewById(R.id.btn_stop_workout);
        btnStopWorkout.setOnClickListener(this);

        this.fabAddExercise = root.findViewById(R.id.fab_add_exercise);
        fabAddExercise.setOnClickListener(this);

        RecyclerView recyclerView = root.findViewById(R.id.workout_recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new ExercisesAdapter(this);
        recyclerView.setAdapter(adapter);
    }

    private void startObserving() {
        viewModel.getDoingExercises().observe(this, doingExercises -> {
            adapter.setItems(doingExercises);
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_stop_workout:
                this.stopWorkout();
                break;
            case R.id.fab_add_exercise:
                this.navigateToExercisesCatalog();
                break;
        }
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.workout_menu, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_finish_workout:
                stopWorkout();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void stopWorkout() {
        this.viewModel.stopWorkout();

        Intent intent = new Intent(this.activity, WorkoutService.class);
        intent.setAction(WorkoutService.ACTION_STOP);
        this.activity.stopService(intent);

        NavController controller = NavHostFragment.findNavController(this);
        controller.navigate(R.id.action_workoutProcessFragment_to_workoutResultsFragment);

        Toast.makeText(getActivity(), "Тренировка завершена!", Toast.LENGTH_LONG).show();
    }

    private void navigateToExercisesCatalog() {
        NavController controller = NavHostFragment.findNavController(this);
        Bundle args = new Bundle();
        args.putInt(ExercisesFragment.ACTION_ARG, ExercisesFragment.ACTION_CHOOSE_EXERCISE);
        controller.navigate(R.id.nav_exercises, args);
    }

    @Override
    public void onExerciseSelected(int id) {
        NavController controller = NavHostFragment.findNavController(this);
        Bundle args = new Bundle();
        args.putInt(WorkoutExerciseFragment.ARG_EXERCISE_ID, id);
        controller.navigate(R.id.workoutExercise, args);
    }
}
