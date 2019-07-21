package online.yourfit.ui.home;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import online.yourfit.R;
import online.yourfit.data.workout_history.WorkoutHistoryManager;
import online.yourfit.ui.MainViewModel;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class HomeFragment extends Fragment implements View.OnClickListener, WorkoutHistoryAdapter.WorkoutHistoryAdapterListener {

    private HomeViewModel homeViewModel;
    private MainViewModel mainViewModel;

    // Views
    private View root;
    private RecyclerView recyclerView;
    private TextView tvOngoingWorkoutMessage;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_home, container, false);
        this.homeViewModel = ViewModelProviders.of(this).get(HomeViewModel.class);
        this.mainViewModel = ViewModelProviders.of(this).get(MainViewModel.class);
        this.initViews();
        this.startObserving();

        this.showWorkoutHistoryList();

        return root;
    }

    private void initViews() {
        recyclerView = root.findViewById(R.id.workout_recycler);
        FloatingActionButton fab = root.findViewById(R.id.fab_add_workout);
        fab.setOnClickListener(this);
        tvOngoingWorkoutMessage = root.findViewById(R.id.tv_ongoingWorkoutMessage);
    }

    private void startObserving() {
        mainViewModel.getOngoingWorkout().observe(this, workout -> {
            Log.d("Workout", "ongoing changed BEGIN");

            if (workout != null) {
                Log.d("Workout", "Тренировка идет! ID: " + workout.getId());
                tvOngoingWorkoutMessage.setText("Тренировка идет! ID: " + workout.getId());
            } else {
                Log.d("Workout", "Ничего не происходит...");
                tvOngoingWorkoutMessage.setText("Ничего не происходит...");
            }

            Log.d("Workout", "ongoing changed END");
        });
    }

    private void showWorkoutHistoryList() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);

        WorkoutHistoryAdapter mAdapter = new WorkoutHistoryAdapter(WorkoutHistoryManager.getList(), this);
        recyclerView.setAdapter(mAdapter);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(),
                layoutManager.getOrientation());
        recyclerView.addItemDecoration(dividerItemDecoration);
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onClick(View v) {
        NavController controller = NavHostFragment.findNavController(this);
        controller.navigate(R.id.workoutNewFragment);
    }

    @Override
    public void navigateToWorkoutHistoryDetail(int id) {
        NavController controller = NavHostFragment.findNavController(this);

        Bundle args = new Bundle();
        args.putInt(WorkoutHistoryManager.ARG_WORKOUT_ID, id);

        controller.navigate(R.id.nav_workout_history_detail, args);
    }
}