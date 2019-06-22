package online.yourfit.ui.measurements;


import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Random;

import online.yourfit.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class TestFragment extends Fragment {

    int pageNumber;
    int backColor;

    static TestFragment newInstance(int page) {
        TestFragment pageFragment = new TestFragment();
        Bundle arguments = new Bundle();
        arguments.putInt("pageNumber", page);
        pageFragment.setArguments(arguments);
        return pageFragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        pageNumber = getArguments().getInt("pageNumber");

        Random rnd = new Random();
        backColor = Color.argb(40, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_test, container, false);

        TextView tvPage = view.findViewById(R.id.tv_test);
        tvPage.setText("Page " + pageNumber);
        tvPage.setBackgroundColor(backColor);

        return view;
    }

}
