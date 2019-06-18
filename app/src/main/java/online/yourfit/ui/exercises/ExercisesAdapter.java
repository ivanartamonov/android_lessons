package online.yourfit.ui.exercises;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import online.yourfit.R;
import online.yourfit.managers.ExercisesManager;
import online.yourfit.models.Exercise;

public class ExercisesAdapter extends RecyclerView.Adapter<ExercisesAdapter.ExerciseViewHolder> {

    private List<Exercise> items = new ArrayList<>();

    private IDetailExerciseListener listener;

    public interface IDetailExerciseListener {
        void openDetailExerciseFragment(int i);
    }

    ExercisesAdapter(IDetailExerciseListener listener) {
        this.listener = listener;
    }

    void setItems(List<Exercise> exercises) {
        this.items = exercises;
        ExercisesManager.setList(exercises);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ExerciseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.exercise_item, parent, false);

        return new ExercisesAdapter.ExerciseViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ExerciseViewHolder holder, int position) {
        holder.bind(this.items.get(position));

        final int id = position;
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener == null) {
                    throw new RuntimeException("Listener must be initialized");
                }
                listener.openDetailExerciseFragment(id);
            }
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    static class ExerciseViewHolder extends RecyclerView.ViewHolder {

        TextView tvExerciseName;
        ImageView img;

        ExerciseViewHolder(@NonNull View itemView) {
            super(itemView);

            this.tvExerciseName = itemView.findViewById(R.id.tv_exerciseName);
            this.img = itemView.findViewById(R.id.img_exercise_primary);
        }

        void bind(Exercise exercise) {
            this.tvExerciseName.setText(exercise.getName());

            Glide.with(itemView)
                    .load(exercise.getPrimaryImgUrl())
                    .into(img);
        }
    }
}
