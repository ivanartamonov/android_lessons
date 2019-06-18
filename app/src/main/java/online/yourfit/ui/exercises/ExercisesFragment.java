package online.yourfit.ui.exercises;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import online.yourfit.R;

public class ExercisesFragment extends Fragment {

    private static final String LOG_TAG = "ExercisesFragment";

    // Views
    private View root;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_exercises, container, false);
        return root;
    }

}
