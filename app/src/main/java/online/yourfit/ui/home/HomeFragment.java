package online.yourfit.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

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
    private Button btnOngoingWorkoutMessage;
    private FloatingActionButton fab;
    private WorkoutHistoryAdapter workoutHistoryAdapter;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_home, container, false);
        this.homeViewModel = ViewModelProviders.of(this).get(HomeViewModel.class);
        this.mainViewModel = MainViewModel.getInstance();
        this.initViews();
        this.startObserving();

        return root;
    }

    private void initViews() {
        recyclerView = root.findViewById(R.id.workout_recycler);
        fab = root.findViewById(R.id.fab_add_workout);
        fab.setOnClickListener(this);
        btnOngoingWorkoutMessage = root.findViewById(R.id.btn_ongoingWorkoutMessage);
        btnOngoingWorkoutMessage.setOnClickListener(this);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);

        workoutHistoryAdapter = new WorkoutHistoryAdapter(this);
        recyclerView.setAdapter(workoutHistoryAdapter);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(),
                layoutManager.getOrientation());
        recyclerView.addItemDecoration(dividerItemDecoration);
    }

    private void startObserving() {
        mainViewModel.getOngoingWorkout().observe(this, workout -> {
            if (workout != null) {
                btnOngoingWorkoutMessage.setText("Идет тренировка");
                btnOngoingWorkoutMessage.setVisibility(View.VISIBLE);
                fab.hide();
            } else {
                fab.show();
                btnOngoingWorkoutMessage.setVisibility(View.GONE);
            }
        });

        homeViewModel.getLastWorkouts().observe(this, workouts -> {
            workoutHistoryAdapter.setItems(workouts);
        });
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onClick(View v) {
        NavController controller = NavHostFragment.findNavController(this);

        switch (v.getId()) {
            case R.id.fab_add_workout:
                controller.navigate(R.id.workoutNewFragment);
                break;
            case R.id.btn_ongoingWorkoutMessage:
                controller.navigate(R.id.workoutProcessFragment);
                break;
        }

    }

    @Override
    public void navigateToWorkoutHistoryDetail(int id) {
        NavController controller = NavHostFragment.findNavController(this);

        Bundle args = new Bundle();
        args.putInt(WorkoutHistoryManager.ARG_WORKOUT_ID, id);

        controller.navigate(R.id.nav_workout_history_detail, args);
    }
}