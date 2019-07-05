package online.yourfit.ui.programs;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import online.yourfit.R;
import online.yourfit.core.App;
import online.yourfit.data.programs.Program;

public class ProgramDetailsFragment extends Fragment {

    private AppCompatActivity activity;
    private ProgramsViewModel viewModel;

    private TextView tvProgramTitle;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_program_details, container, false);
        this.viewModel = new ProgramsViewModel(App.instance);
        initViews(v);
        return v;
    }

    private void initViews(View v) {
        this.tvProgramTitle = v.findViewById(R.id.tv_programTitle);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        this.activity = (AppCompatActivity) getActivity();

        Bundle bundle = getArguments();
        if (bundle == null) {
            throw new RuntimeException("Invalid program ID");
        }

        int id = bundle.getInt("programId", -1);
        if (id < 0) {
            throw new RuntimeException("Invalid program ID");
        }

        this.render(id);
    }

    private void render(int programId) {
        this.setActionBarTitle("Программа");

        LiveData<Program> liveData = this.viewModel.getProgramById(programId);

        liveData.observe(this, new Observer<Program>() {
            @Override
            public void onChanged(Program program) {
                fillViews(program);
            }
        });
    }

    private void fillViews(Program program) {
        this.setActionBarTitle(program.getTitle());
        this.tvProgramTitle.setText(program.getTitle());
    }

    private void setActionBarTitle(String title) {
        ActionBar actionBar = this.activity.getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle(title);
        }
    }
}
