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

    private List<Program> items;
    private ProgramAdapterListener listener;

    public interface ProgramAdapterListener {
        void navigateToProgramDetails(int programId);
    }

    ProgramsAdapter(List<Program> items, ProgramAdapterListener listener) {
        this.items = items;
        this.listener = listener;
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

        final int pos = position;
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener == null) {
                    throw new RuntimeException("Listener must be initialized");
                }
                listener.navigateToProgramDetails(getIdByPosition(pos));
            }
        });
    }

    private int getIdByPosition(int position) {
        return this.items.get(position).getId();
    }

    @Override
    public int getItemCount() {
        return this.items.size();
    }

    static class ProgramsViewHolder extends RecyclerView.ViewHolder {
        TextView tvProgramTitle;
        TextView tvProgramLevel;
        TextView tvProgramGoal;
        TextView tvProgramAuditory;

        ProgramsViewHolder(@NonNull View itemView) {
            super(itemView);

            this.tvProgramTitle = itemView.findViewById(R.id.tv_programTitle);
            this.tvProgramLevel = itemView.findViewById(R.id.tv_programLevel);
            this.tvProgramGoal = itemView.findViewById(R.id.tv_programGoal);
            this.tvProgramAuditory = itemView.findViewById(R.id.tv_programAuditory);
        }

        void bind(Program program) {
            this.tvProgramTitle.setText(program.getTitle());
            this.tvProgramLevel.setText(program.getLevelName());
            this.tvProgramGoal.setText(program.getGoalName());
            this.tvProgramAuditory.setText(program.getAuditoryName());
        }
    }
}
