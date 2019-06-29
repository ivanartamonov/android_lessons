package online.yourfit.ui.home;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import online.yourfit.R;
import online.yourfit.data.workout_history.WorkoutHistoryItem;
import online.yourfit.ui.FragmentOpener;

import java.util.List;

public class WorkoutHistoryAdapter extends RecyclerView.Adapter<WorkoutHistoryViewHolder> {

    List<WorkoutHistoryItem> items;

    private FragmentOpener listener;

    public WorkoutHistoryAdapter(List<WorkoutHistoryItem> items, FragmentOpener listener) {
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

                listener.showFragment(WorkoutDetailFragment.newInstance(id));
            }
        });
    }

    @Override
    public int getItemCount() {
        return this.items.size();
    }
}
