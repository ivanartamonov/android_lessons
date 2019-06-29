package online.yourfit.ui.programs;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import online.yourfit.R;
import online.yourfit.data.programs.Program;

import java.util.ArrayList;
import java.util.List;

public class ProgramsFragment extends Fragment {


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_programs, container, false);


        RecyclerView recyclerView = (RecyclerView) root.findViewById(R.id.programs_recycler);

        // 2. set layoutManger
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);

        // this is data for recycler view
        List<Program> programs = new ArrayList<>();
        programs.add(new Program("Худышка", Program.LEVEL_BEGINNER, Program.GOAL_WEIGHT_LOSS, Program.AUDITORY_FEMALE));
        programs.add(new Program("Сила творожка", Program.LEVEL_MIDDLE, Program.GOAL_MASS, Program.AUDITORY_MALE));
        programs.add(new Program("Бегемотик", Program.LEVEL_PRO, Program.GOAL_GOOD_SHAPE, Program.AUDITORY_ALL));


        ProgramsAdapter mAdapter = new ProgramsAdapter(programs);
        recyclerView.setAdapter(mAdapter);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(),
                layoutManager.getOrientation());
        recyclerView.addItemDecoration(dividerItemDecoration);


        return root;
    }
}