package online.yourfit.ui.workout;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.ArrayList;
import online.yourfit.R;
import online.yourfit.data.exercises.Exercise;
import online.yourfit.services.StartWorkout;

public class WorkoutNewFragment extends Fragment implements View.OnClickListener {

    private AppCompatActivity activity;
    private WorkoutViewModel viewModel;
    private WorkoutExercisesAdapter adapter;

    private View root;
    private Button btnStartWorkout;
    private Button btnFinishWorkout;
    private Button btnAddExercise;
    private RecyclerView recyclerView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.viewModel = ViewModelProviders.of(this).get(WorkoutViewModel.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_workout_new, container, false);
        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        this.activity = (AppCompatActivity) getActivity();

        this.setActionBarTitle("Новая тренировка");
        this.initViews();
        this.setupObserving();
    }

    private void setupObserving() {
        LiveData<String> appBarLiveData = viewModel.getAppBarTitle();
        appBarLiveData.observe(this, new Observer<String>() {
            @Override
            public void onChanged(String title) {
                setActionBarTitle(title);
            }
        });

        LiveData<Integer> statusLiveData = viewModel.getStatus();
        statusLiveData.observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer status) {
                switch (status) {
                    case WorkoutViewModel.STATUS_IN_PROGRESS:
                        onStartWorkout();
                        break;
                    case WorkoutViewModel.STATUS_PAUSED:
                        onPauseWorkout();
                        break;
                    case WorkoutViewModel.STATUS_FINISHED:
                        onFinishWorkout();
                        break;
                }
            }
        });

        LiveData<ArrayList<Exercise>> exercisesLiveData = viewModel.getExercises();
        exercisesLiveData.observe(this, new Observer<ArrayList<Exercise>>() {
            @Override
            public void onChanged(ArrayList<Exercise> exercises) {
                adapter.setItems(exercises);
            }
        });
    }

    private void onStartWorkout() {
        this.btnStartWorkout.setVisibility(View.GONE);
        this.btnFinishWorkout.setVisibility(View.VISIBLE);
    }

    private void onPauseWorkout() {

    }

    private void onFinishWorkout() {
        this.btnStartWorkout.setVisibility(View.GONE);
        this.btnFinishWorkout.setVisibility(View.GONE);
    }

    private void initViews() {
        this.btnStartWorkout = root.findViewById(R.id.btn_start_workout);
        this.btnFinishWorkout = root.findViewById(R.id.btn_stop_workout);
        this.btnAddExercise = root.findViewById(R.id.btn_addExercise);
        this.recyclerView = root.findViewById(R.id.exercises_recycler);

        btnStartWorkout.setOnClickListener(this);
        btnFinishWorkout.setOnClickListener(this);
        btnAddExercise.setOnClickListener(this);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);

        adapter = new WorkoutExercisesAdapter();
        recyclerView.setAdapter(adapter);
    }

    private void setActionBarTitle(String title) {
        ActionBar actionBar = this.activity.getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle(title);
        }
    }

    @Override
    public void onClick(View v) {
        Intent intent;

        switch (v.getId()) {
            case R.id.btn_start_workout:
                this.viewModel.startWorkout();
                intent = new Intent(this.activity, StartWorkout.class);
                intent.setAction(StartWorkout.ACTION_START);
                this.activity.startService(intent);
                break;
            case R.id.btn_stop_workout:
                this.viewModel.stopWorkout();
                intent = new Intent(this.activity, StartWorkout.class);
                intent.setAction(StartWorkout.ACTION_STOP);
                this.activity.stopService(intent);
                break;
            case R.id.btn_addExercise:
                Log.d("Workout", "Add exercises btn clicked");
                this.viewModel.addExercise();
                break;
        }
    }
}
