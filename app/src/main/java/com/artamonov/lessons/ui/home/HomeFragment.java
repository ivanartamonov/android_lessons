package com.artamonov.lessons.ui.home;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.artamonov.lessons.R;
import com.artamonov.lessons.models.WorkoutHistoryItem;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    public static final String LOG_TAG = "HomeFragment";

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.home_fragment, container, false);

        RecyclerView recyclerView = (RecyclerView) root.findViewById(R.id.workout_recycler);

        // 2. set layoutManger
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);

        // this is data for recycler view
        List<WorkoutHistoryItem> workoutHistoryItems = new ArrayList<>();
        workoutHistoryItems.add(new WorkoutHistoryItem("02.05", "My program", 3600, 5400));
        workoutHistoryItems.add(new WorkoutHistoryItem("03.05", "My program", 3700, 5200));
        workoutHistoryItems.add(new WorkoutHistoryItem("04.05", "My program", 3800, 7300));
        workoutHistoryItems.add(new WorkoutHistoryItem("05.05", "My program", 3900, 6420));
        workoutHistoryItems.add(new WorkoutHistoryItem("05.05", "My program", 3900, 6420));
        workoutHistoryItems.add(new WorkoutHistoryItem("05.05", "My program", 3900, 6420));
        workoutHistoryItems.add(new WorkoutHistoryItem("05.05", "My program", 3900, 6420));
        workoutHistoryItems.add(new WorkoutHistoryItem("05.05", "My program", 3900, 6420));
        workoutHistoryItems.add(new WorkoutHistoryItem("05.05", "My program", 3900, 6420));
        workoutHistoryItems.add(new WorkoutHistoryItem("05.05", "My program", 3900, 6420));
        workoutHistoryItems.add(new WorkoutHistoryItem("05.05", "My program", 3900, 6420));
        workoutHistoryItems.add(new WorkoutHistoryItem("05.05", "My program", 3900, 6420));
        workoutHistoryItems.add(new WorkoutHistoryItem("05.05", "My program", 3900, 6420));
        workoutHistoryItems.add(new WorkoutHistoryItem("05.05", "My program", 3900, 6420));
        workoutHistoryItems.add(new WorkoutHistoryItem("05.05", "My program", 3900, 6420));
        workoutHistoryItems.add(new WorkoutHistoryItem("05.05", "My program", 3900, 6420));
        workoutHistoryItems.add(new WorkoutHistoryItem("05.05", "My program", 3900, 6420));
        workoutHistoryItems.add(new WorkoutHistoryItem("05.05", "My program", 3900, 6420));
        workoutHistoryItems.add(new WorkoutHistoryItem("05.05", "My program", 3900, 6420));
        workoutHistoryItems.add(new WorkoutHistoryItem("05.05", "My program", 3900, 6420));
        workoutHistoryItems.add(new WorkoutHistoryItem("05.05", "My program", 3900, 6420));
        workoutHistoryItems.add(new WorkoutHistoryItem("05.05", "My program", 3900, 6420));
        workoutHistoryItems.add(new WorkoutHistoryItem("05.05", "My program", 3900, 6420));

        WorkoutHistoryAdapter mAdapter = new WorkoutHistoryAdapter(workoutHistoryItems);
        recyclerView.setAdapter(mAdapter);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(),
                layoutManager.getOrientation());
        recyclerView.addItemDecoration(dividerItemDecoration);


        return root;
    }
}