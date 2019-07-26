package online.yourfit.ui.home;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import online.yourfit.R;
import online.yourfit.data.workout.Workout;
import online.yourfit.data.workout_history.WorkoutHistoryItem;

import java.util.ArrayList;
import java.util.List;

public class WorkoutHistoryAdapter extends RecyclerView.Adapter<WorkoutHistoryViewHolder> {

    private List<Workout> items = new ArrayList<>();

    private WorkoutHistoryAdapterListener listener;

    public interface WorkoutHistoryAdapterListener {
        void navigateToWorkoutHistoryDetail(int id);
    }

    public WorkoutHistoryAdapter(WorkoutHistoryAdapterListener listener) {
        this.listener = listener;
    }

    public void setItems(List<Workout> workouts) {
        this.items = workouts;
        notifyDataSetChanged();
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
        final int id = position;
        holder.itemView.setOnClickListener(v -> {
            Log.d("ADAPTER", "Open Fragment #" + id);
            if (listener == null) {
                throw new RuntimeException("Listener must be initialized");
            }
            listener.navigateToWorkoutHistoryDetail(id);
        });
    }

    @Override
    public int getItemCount() {
        return this.items.size();
    }
}
