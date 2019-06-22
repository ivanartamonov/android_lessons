package online.yourfit.ui.measurements;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import online.yourfit.R;

public class MeasurementsFragment extends Fragment {

    private static final String LOG_TAG = "MeasurementsFragment";

    // Views
    private View root;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_measurements, container, false);

        return root;
    }
}
