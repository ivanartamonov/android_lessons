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

import online.yourfit.activities.MainActivity;
import online.yourfit.R;
import online.yourfit.activities.StartWorkoutActivity;
import online.yourfit.managers.WorkoutHistoryManager;
import online.yourfit.models.User;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class HomeFragment extends Fragment implements View.OnClickListener {

    private static final String LOG_TAG = "HomeFragment";

    private HomeViewModel homeViewModel;

    // Views
    private View root;
    private RecyclerView recyclerView;
    private FloatingActionButton fab;
    private TextView tvUserName;
    private ImageView userAvatar;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_home, container, false);
        this.homeViewModel = ViewModelProviders.of(this).get(HomeViewModel.class);
        this.initViews();

        this.showWorkoutHistoryList();

        return root;
    }

    private void initViews() {
        tvUserName = root.findViewById(R.id.tv_user_name);
        userAvatar = root.findViewById(R.id.img_user_avatar);
        recyclerView = root.findViewById(R.id.workout_recycler);
        fab = root.findViewById(R.id.fab_add_workout);

        fab.setOnClickListener(this);
    }

    private void showWorkoutHistoryList() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);

        WorkoutHistoryAdapter.IDetailWorkoutListener listener = (MainActivity) getActivity();

        WorkoutHistoryAdapter mAdapter = new WorkoutHistoryAdapter(WorkoutHistoryManager.getList(), listener);
        recyclerView.setAdapter(mAdapter);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(),
                layoutManager.getOrientation());
        recyclerView.addItemDecoration(dividerItemDecoration);
    }

    @Override
    public void onStart() {
        super.onStart();

        this.homeViewModel.getUser().observe(this, new Observer<User>() {
            @Override
            public void onChanged(User user) {
                if (user != null) {
                    Log.d(LOG_TAG, "ViewModeOnChange: " + user.getName());
                    Toast.makeText(getActivity(), user.getName(), Toast.LENGTH_SHORT).show();

                    // Почему tvUserName = null?
                    //tvUserName.setText(user.getName()); // Ошибка, попытка вызвать метод setText() у null

                    /*
                    Glide.with(getContext())
                            .load(user.getAvatarUrl())
                            .into(userAvatar);

                     */
                } else {
                    Log.d(LOG_TAG, "User is null");
                    Toast.makeText(getActivity(), "User is null", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fab_add_workout:
                Intent intent = new Intent(this.getContext(), StartWorkoutActivity.class);
                startActivityForResult(intent, 1);

                /*
                Activity activity = getActivity();
                if (activity != null) {
                    Intent intent = new Intent(this.getContext(), StartWorkout.class);
                    intent.setAction("StartWorkout Action");
                    activity.startService(intent);
                } else {
                    Log.d(LOG_TAG, "Activity is null");
                }
                */

                break;
        }
    }
}