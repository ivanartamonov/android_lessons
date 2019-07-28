package online.yourfit.ui.programs;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import online.yourfit.R;
import online.yourfit.core.App;
import online.yourfit.data.programs.Program;

public class ProgramDetailsFragment extends Fragment implements View.OnClickListener {

    private AppCompatActivity activity;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();
    private ProgramsViewModel viewModel;
    private int programId;
    private TextView tvProgramTitle;
    private Button btnDelete;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_program_details, container, false);
        this.viewModel = new ProgramsViewModel(App.instance);
        initViews(v);
        return v;
    }

    private void initViews(View v) {
        this.tvProgramTitle = v.findViewById(R.id.tv_programTitle);
        this.btnDelete = v.findViewById(R.id.btn_delete);
        this.btnDelete.setOnClickListener(this);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        this.activity = (AppCompatActivity) getActivity();

        Bundle bundle = getArguments();
        if (bundle == null) {
            throw new RuntimeException("Invalid program ID");
        }

        programId = bundle.getInt("programId", -1);
        if (programId < 0) {
            throw new RuntimeException("Invalid program ID");
        }

        this.render(programId);
    }

    private void render(int programId) {
        this.setActionBarTitle("Программа");

        LiveData<Program> liveData = this.viewModel.getProgramById(programId);

        liveData.observe(this, program -> fillViews(program));
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

    @Override
    public void onClick(View v) {
        Disposable disposable = viewModel.deleteById(programId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(() -> {
                Toast.makeText(getActivity(), "Запись удалена", Toast.LENGTH_LONG).show();
                NavController controller = NavHostFragment.findNavController(this);
                controller.popBackStack();
            });

        compositeDisposable.add(disposable);
    }

    @Override
    public void onDestroy() {
        compositeDisposable.clear();
        super.onDestroy();
    }
}
