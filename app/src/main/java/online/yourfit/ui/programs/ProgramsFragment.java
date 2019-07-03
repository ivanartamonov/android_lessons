package online.yourfit.ui.programs;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import io.reactivex.disposables.CompositeDisposable;
import online.yourfit.R;
import online.yourfit.core.App;
import online.yourfit.data.programs.Program;

import java.util.List;

public class ProgramsFragment extends Fragment implements View.OnClickListener {

    private ProgramsViewModel viewModel;

    private View root;
    private RecyclerView recyclerView;
    private RelativeLayout emptyListNotice;
    private Button btnAdd;

    @NonNull
    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_programs, container, false);
        this.viewModel = new ProgramsViewModel(App.instance);

        this.initViews();
        this.render();

        return root;
    }

    private void initViews() {
        this.recyclerView = root.findViewById(R.id.programs_recycler);
        this.emptyListNotice = root.findViewById(R.id.empty_list_notice);
        this.btnAdd = root.findViewById(R.id.btn_create_program);

        this.btnAdd.setOnClickListener(this);
    }

    private void render() {
        //List<Program> programs = new ArrayList<>();
        // programs.add(new Program("Худышка", Program.LEVEL_BEGINNER, Program.GOAL_WEIGHT_LOSS, Program.AUDITORY_FEMALE));

        LiveData<List<Program>> liveData = viewModel.getProgramsList();

        liveData.observe(this, new Observer<List<Program>>() {
            @Override
            public void onChanged(List<Program> programs) {
                if (programs.size() == 0) {
                    showEmptyListMessage();
                } else {
                    showList(programs);
                }
            }
        });
    }

    private void showEmptyListMessage() {
        this.recyclerView.setVisibility(View.GONE);
        this.emptyListNotice.setVisibility(View.VISIBLE);
    }

    private void showList(List<Program> programs) {
        this.recyclerView.setVisibility(View.VISIBLE);
        this.emptyListNotice.setVisibility(View.GONE);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);

        // this is data for recycler view
        //programs.add(new Program("Худышка", Program.LEVEL_BEGINNER, Program.GOAL_WEIGHT_LOSS, Program.AUDITORY_FEMALE));
        //programs.add(new Program("Сила творожка", Program.LEVEL_MIDDLE, Program.GOAL_MASS, Program.AUDITORY_MALE));
        //programs.add(new Program("Бегемотик", Program.LEVEL_PRO, Program.GOAL_GOOD_SHAPE, Program.AUDITORY_ALL));

        ProgramsAdapter mAdapter = new ProgramsAdapter(programs);
        recyclerView.setAdapter(mAdapter);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(
                recyclerView.getContext(),
                layoutManager.getOrientation()
        );
        recyclerView.addItemDecoration(dividerItemDecoration);
    }

    @Override
    public void onDestroyView() {
        compositeDisposable.clear();
        super.onDestroyView();
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_create_program) {
            NavController controller = NavHostFragment.findNavController(this);
            controller.navigate(R.id.addProgramFragment);
        }
    }
}