package online.yourfit.ui.programs;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import io.reactivex.disposables.CompositeDisposable;
import online.yourfit.R;
import online.yourfit.data.programs.Program;

import java.util.ArrayList;
import java.util.List;

public class ProgramsFragment extends Fragment {

    private View root;
    private RecyclerView recyclerView;
    private RelativeLayout emptyListNotice;

    @NonNull
    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_programs, container, false);

        this.initViews();
        this.render();

        return root;
    }

    private void initViews() {
        this.recyclerView = root.findViewById(R.id.programs_recycler);
        this.emptyListNotice = root.findViewById(R.id.empty_list_notice);
    }

    private void render() {
        List<Program> programs = new ArrayList<>();
        // programs.add(new Program("Худышка", Program.LEVEL_BEGINNER, Program.GOAL_WEIGHT_LOSS, Program.AUDITORY_FEMALE));

        if (programs.size() == 0) {
            this.showEmptyListMessage();
        } else {
            this.showList(programs);
        }
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
}