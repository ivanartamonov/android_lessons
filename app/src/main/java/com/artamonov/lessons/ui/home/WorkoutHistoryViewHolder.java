package com.artamonov.lessons.ui.home;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.artamonov.lessons.R;
import com.artamonov.lessons.models.WorkoutHistoryItem;

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
