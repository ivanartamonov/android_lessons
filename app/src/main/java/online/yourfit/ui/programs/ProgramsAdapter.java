package online.yourfit.ui.programs;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import online.yourfit.R;
import online.yourfit.data.programs.Program;

import java.util.List;

public class ProgramsAdapter extends RecyclerView.Adapter<ProgramsAdapter.ProgramsViewHolder> {

    List<Program> items;

    public ProgramsAdapter(List<Program> items) {
        this.items = items;
    }

    @NonNull
    @Override
    public ProgramsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.program_item, parent, false);

        return new ProgramsViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ProgramsViewHolder holder, int position) {
        holder.bind(this.items.get(position));
    }

    @Override
    public int getItemCount() {
        return this.items.size();
    }

    public static class ProgramsViewHolder extends RecyclerView.ViewHolder {

        TextView tvProgramTitle;
        TextView tvProgramLevel;
        TextView tvProgramGoal;
        TextView tvProgramAuditory;

        public ProgramsViewHolder(@NonNull View itemView) {
            super(itemView);

            this.tvProgramTitle = itemView.findViewById(R.id.tv_programTitle);
            this.tvProgramLevel = itemView.findViewById(R.id.tv_programLevel);
            this.tvProgramGoal = itemView.findViewById(R.id.tv_programGoal);
            this.tvProgramAuditory = itemView.findViewById(R.id.tv_programAuditory);
        }

        public void bind(Program program) {
            this.tvProgramTitle.setText(program.getTitle());
            this.tvProgramLevel.setText(program.getLevelName());
            this.tvProgramGoal.setText(program.getGoalName());
            this.tvProgramAuditory.setText(program.getAuditoryName());
        }
    }
}
