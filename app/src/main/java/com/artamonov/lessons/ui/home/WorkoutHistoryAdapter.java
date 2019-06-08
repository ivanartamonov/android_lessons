package com.artamonov.lessons.ui.home;

import android.util.Log;
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

    public interface IDetailWorkoutListener{
        void openDetailWorkoutFragment(int i);
    }

    private IDetailWorkoutListener listener;

    public WorkoutHistoryAdapter(List<WorkoutHistoryItem> items, IDetailWorkoutListener listener) {
        this.items = items;
        this.listener = listener;
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
        Log.d("ADAPTER", "onBindViewHolder - " + position);
        final int id = position;
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("ADAPTER", "Open Fragment #" + id);
                if (listener == null) {
                    throw new RuntimeException("Listener must be initialized");
                }

                listener.openDetailWorkoutFragment(id);
            }
        });
    }

    @Override
    public int getItemCount() {
        return this.items.size();
    }
}
