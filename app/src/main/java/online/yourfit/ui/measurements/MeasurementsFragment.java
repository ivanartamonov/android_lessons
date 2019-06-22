package online.yourfit.ui.measurements;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

import online.yourfit.R;

public class MeasurementsFragment extends Fragment {

    private static final String LOG_TAG = "MeasurementsFragment";

    ViewPager pager;
    TestPagerAdapter pagerAdapter;
    TabLayout tabLayout;

    // Views
    private View root;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_measurements, container, false);

        tabLayout = root.findViewById(R.id.tabs_measurements);
        pager = root.findViewById(R.id.viewPager_measurements);
        pagerAdapter = new TestPagerAdapter(getActivity().getSupportFragmentManager());
        pager.setAdapter(pagerAdapter);
        tabLayout.setupWithViewPager(pager);

        return root;
    }
}
