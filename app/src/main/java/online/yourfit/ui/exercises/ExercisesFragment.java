package online.yourfit.ui.exercises;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import online.yourfit.R;

public class ExercisesFragment extends Fragment implements ExercisesAdapter.ExercisesAdapterListener {

    private View root;
    private ExercisesAdapter adapter;
    private ExercisesViewModel exercisesViewModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        exercisesViewModel = ViewModelProviders.of(this).get(ExercisesViewModel.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_exercises, container, false);

        this.initViews();
        this.setupObserving();

        return root;
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
    public void navigateToExerciseDetails(int id) {
        NavController controller = NavHostFragment.findNavController(this);

        Bundle args = new Bundle();
        args.putInt("exerciseId", id);

        controller.navigate(R.id.nav_exercise_detail, args);
    }
}
