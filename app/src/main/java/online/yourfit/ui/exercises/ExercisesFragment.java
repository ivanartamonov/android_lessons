package online.yourfit.ui.exercises;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import online.yourfit.MainActivity;
import online.yourfit.R;
import online.yourfit.managers.ExercisesManager;
import online.yourfit.managers.WorkoutHistoryManager;
import online.yourfit.ui.home.WorkoutHistoryAdapter;

public class ExercisesFragment extends Fragment {

    private static final String LOG_TAG = "ExercisesFragment";

    // Views
    private View root;
    private RecyclerView recyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_exercises, container, false);

        this.initViews();
        this.showWorkoutHistoryList();

        return root;
    }

    private void initViews() {
        recyclerView = root.findViewById(R.id.exercises_recycler);
    }

    private void showWorkoutHistoryList() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);

        //ExercisesAdapter.IDetailWorkoutListener listener = (MainActivity) getActivity();

        ExercisesAdapter adapter = new ExercisesAdapter(ExercisesManager.getList());
        recyclerView.setAdapter(adapter);
    }

}
