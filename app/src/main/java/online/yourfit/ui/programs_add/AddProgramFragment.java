package online.yourfit.ui.programs_add;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import online.yourfit.R;
import online.yourfit.core.App;

public class AddProgramFragment extends Fragment implements View.OnClickListener {

    private AddProgramViewModel viewModel;

    View root;
    EditText editTextTitle;
    Button btnSave;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_program_add, container, false);

        this.initViews();

        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        viewModel = new AddProgramViewModel(App.instance);

        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle("Новая программа");
    }

    private void initViews() {
        this.editTextTitle = root.findViewById(R.id.et_programName);
        this.btnSave = root.findViewById(R.id.btn_programSave);

        this.btnSave.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        this.editTextTitle.clearFocus();
        viewModel.createNew(editTextTitle.getText().toString());
        NavController controller = NavHostFragment.findNavController(this);
        controller.popBackStack();
    }
}
