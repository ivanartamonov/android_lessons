package online.yourfit.ui.measurements;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

public class TestPagerAdapter extends FragmentStatePagerAdapter {

    TestPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return TestFragment.newInstance(0);
            case 1:
                return TestFragment.newInstance(1);
            case 2:
                return TestFragment.newInstance(2);
        }

        return TestFragment.newInstance(0);
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return "Page " + position;
    }

    @Override
    public int getCount() {
        return 3;
    }
}
