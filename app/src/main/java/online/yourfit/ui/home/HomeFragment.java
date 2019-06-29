package online.yourfit.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import online.yourfit.ui.MainActivity;
import online.yourfit.R;
import online.yourfit.ui.workout.StartWorkoutActivity;
import online.yourfit.data.workout_history.WorkoutHistoryManager;
import online.yourfit.ui.FragmentOpener;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class HomeFragment extends Fragment implements View.OnClickListener {

    private static final String LOG_TAG = "HomeFragment";

    private HomeViewModel homeViewModel;

    // Views
    private View root;
    private RecyclerView recyclerView;
    private FloatingActionButton fab;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_home, container, false);
        this.homeViewModel = ViewModelProviders.of(this).get(HomeViewModel.class);
        this.initViews();

        this.showWorkoutHistoryList();

        return root;
    }

    private void initViews() {
        recyclerView = root.findViewById(R.id.workout_recycler);
        fab = root.findViewById(R.id.fab_add_workout);

        fab.setOnClickListener(this);
    }

    private void showWorkoutHistoryList() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);

        FragmentOpener listener = (MainActivity) getActivity();

        WorkoutHistoryAdapter mAdapter = new WorkoutHistoryAdapter(WorkoutHistoryManager.getList(), listener);
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
        switch (v.getId()) {
            case R.id.fab_add_workout:
                Intent intent = new Intent(this.getContext(), StartWorkoutActivity.class);
                startActivityForResult(intent, 1);

                /*
                Activity activity = getActivity();
                if (activity != null) {
                    Intent intent = new Intent(this.getContext(), StartWorkout.class);
                    intent.setAction("StartWorkout Action");
                    activity.startService(intent);
                } else {
                    Log.d(LOG_TAG, "Activity is null");
                }
                */

                break;
        }
    }
}