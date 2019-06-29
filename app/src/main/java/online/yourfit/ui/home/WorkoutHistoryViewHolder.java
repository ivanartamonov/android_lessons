package online.yourfit.ui.home;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import online.yourfit.R;
import online.yourfit.data.workout_history.WorkoutHistoryItem;

public class WorkoutHistoryViewHolder extends RecyclerView.ViewHolder {

    private TextView tvProgramName;
    private TextView tvProgramDate;
    private TextView tvProgramDuration;

    public WorkoutHistoryViewHolder(@NonNull View itemView) {
        super(itemView);

        this.tvProgramName = itemView.findViewById(R.id.tv_programName);
        this.tvProgramDate = itemView.findViewById(R.id.tv_programDate);
        this.tvProgramDuration = itemView.findViewById(R.id.tv_programDuration);

    }

    public void bind(WorkoutHistoryItem item) {
        this.tvProgramName.setText(item.getProgramName());
        this.tvProgramDate.setText(item.getDate());
        this.tvProgramDuration.setText(item.getDuration() + "");
    }
}
