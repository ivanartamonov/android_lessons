package online.yourfit.ui;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

public class BaseFragment extends Fragment {

    protected AppCompatActivity activity;
    protected ActionBar actionBar;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        this.activity = (AppCompatActivity) getActivity();
    }

    protected void setActionBarTitle(String title) {
        this.actionBar = this.activity.getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle(title);
        }
    }

    protected void removeUpButton() {
        this.actionBar = this.activity.getSupportActionBar();
        if (actionBar != null) {
            actionBar.setHomeButtonEnabled(false); // disable the button
            actionBar.setDisplayHomeAsUpEnabled(false); // remove the left caret
            actionBar.setDisplayShowHomeEnabled(false); // remove the icon
        }
    }

}
