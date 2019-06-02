package com.artamonov.lessons.ui.home;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.artamonov.lessons.R;
import com.artamonov.lessons.models.WorkoutHistoryItem;

import java.util.List;

public class WorkoutHistoryAdapter extends RecyclerView.Adapter<WorkoutHistoryViewHolder> {

    List<WorkoutHistoryItem> items;

    public WorkoutHistoryAdapter(List<WorkoutHistoryItem> items) {
        this.items = items;
    }

    @NonNull
    @Override
    public WorkoutHistoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.workout_history_item, parent, false);

        return new WorkoutHistoryViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull WorkoutHistoryViewHolder holder, int position) {
        holder.bind(this.items.get(position));
    }

    @Override
    public int getItemCount() {
        return this.items.size();
    }
}
