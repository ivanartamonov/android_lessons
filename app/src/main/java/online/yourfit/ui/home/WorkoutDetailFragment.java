package online.yourfit.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import online.yourfit.R;
import online.yourfit.data.workout_history.WorkoutHistoryManager;
import online.yourfit.data.workout_history.WorkoutHistoryItem;

public class WorkoutDetailFragment extends Fragment {

    public static WorkoutDetailFragment newInstance(int i) {
        Bundle args = new Bundle();
        args.putInt(WorkoutHistoryManager.ARG_WORKOUT_ID, i);
        WorkoutDetailFragment fragment = new WorkoutDetailFragment();
        fragment.setArguments(args);
        return fragment;
    }

    private TextView tvWorkoutDate;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_workout_detail, container, false);
        initViews(v);
        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        int i = getArguments().getInt(WorkoutHistoryManager.ARG_WORKOUT_ID, -1);
        if (i < 0) {
            throw new RuntimeException("wrong card id");
        }

        WorkoutHistoryItem item = WorkoutHistoryManager.getList().get(i);
        fillViews(item);
    }

    private void initViews(View v){
        tvWorkoutDate = v.findViewById(R.id.tv_workout_detail_date);
    }

    private void fillViews(WorkoutHistoryItem historyItem){
        tvWorkoutDate.setText(historyItem.getDate());
    }
}
