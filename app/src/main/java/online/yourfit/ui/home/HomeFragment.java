package online.yourfit.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import online.yourfit.AddWorkoutActivity;
import online.yourfit.MainActivity;
import online.yourfit.R;
import online.yourfit.managers.WorkoutHistoryManager;
import online.yourfit.models.User;
import online.yourfit.services.InitUser;
import online.yourfit.ui.SetUserInfo;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class HomeFragment extends Fragment implements View.OnClickListener {

    private static final String LOG_TAG = "HomeFragment";

    private HomeViewModel homeViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        final View root = inflater.inflate(R.layout.home_fragment, container, false);

        RecyclerView recyclerView = (RecyclerView) root.findViewById(R.id.workout_recycler);

        this.homeViewModel = ViewModelProviders.of(this).get(HomeViewModel.class);
        this.homeViewModel.getUser().observe(this, new Observer<User>() {
            @Override
            public void onChanged(User user) {
                if (user != null) {
                    SetUserInfo.execute(getActivity(), user);
                    Log.d(LOG_TAG, "ViewModeOnChange: " + user.getName());
                    Toast.makeText(getActivity(), user.getName(), Toast.LENGTH_SHORT).show();
                } else {
                    Log.d(LOG_TAG, "User is null");
                    Toast.makeText(getActivity(), "User is null", Toast.LENGTH_SHORT).show();
                }
            }
        });

        InitUser userDefiner = new InitUser((MainActivity) getActivity());
        User user = userDefiner.getUser();

        // 2. set layoutManger
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);

        WorkoutHistoryAdapter.IDetailWorkoutListener listener = (MainActivity) getActivity();

        WorkoutHistoryAdapter mAdapter = new WorkoutHistoryAdapter(WorkoutHistoryManager.getList(), listener);
        recyclerView.setAdapter(mAdapter);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(),
                layoutManager.getOrientation());
        recyclerView.addItemDecoration(dividerItemDecoration);

        FloatingActionButton fab = (FloatingActionButton) root.findViewById(R.id.fab_add_workout);
        fab.setOnClickListener(this);

        return root;
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.fab_add_workout:
                //Toast.makeText(this.getContext(), "Hello, world!", Toast.LENGTH_LONG).show();

                Intent intent = new Intent(this.getContext(), AddWorkoutActivity.class);
                startActivityForResult(intent, 1);

                break;
        }
    }
}