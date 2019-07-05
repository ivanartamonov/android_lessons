package online.yourfit.ui.programs;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import online.yourfit.R;

public class ProgramDetailsFragment extends Fragment {

    private View containerView;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_program_details, container, false);
        initViews(v);
        return v;
    }

    private void initViews(View v) {
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        containerView = view;
        super.onViewCreated(view, savedInstanceState);

        Bundle bundle = getArguments();
        if (bundle == null) {
            throw new RuntimeException("Invalid program ID");
        }

        int id = bundle.getInt("programId", -1);
        if (id < 0) {
            throw new RuntimeException("Invalid program ID");
        }

        //Exercise item = ExercisesManager.getList().get(id);
        //fillViews(item);
    }
}
