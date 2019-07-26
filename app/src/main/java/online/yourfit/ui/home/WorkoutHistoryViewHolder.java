package online.yourfit.ui.home;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import online.yourfit.R;
import online.yourfit.data.workout.Workout;

class WorkoutHistoryViewHolder extends RecyclerView.ViewHolder {

    private TextView tvProgramName;
    private TextView tvProgramDate;
    private TextView tvProgramDuration;

    WorkoutHistoryViewHolder(@NonNull View itemView) {
        super(itemView);
        this.tvProgramName = itemView.findViewById(R.id.tv_programName);
        this.tvProgramDate = itemView.findViewById(R.id.tv_programDate);
        this.tvProgramDuration = itemView.findViewById(R.id.tv_programDuration);
    }

    void bind(Workout item) {
        this.tvProgramName.setText("ID: " + item.getId());
        this.tvProgramDate.setText("Star|Stop: " + item.getStartedAt() + " - " + item.getFinishedAt());
        this.tvProgramDuration.setText("Duration: " + item.getDuration());
    }
}
