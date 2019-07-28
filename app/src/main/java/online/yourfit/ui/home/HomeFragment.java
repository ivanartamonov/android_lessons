package online.yourfit.ui.home;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import online.yourfit.R;
import online.yourfit.data.blogs.BlogPost;
import online.yourfit.data.workout.Workout;
import online.yourfit.ui.MainViewModel;
import online.yourfit.ui.blogs.BlogsAdapter;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class HomeFragment extends Fragment implements View.OnClickListener,
        WorkoutHistoryAdapter.WorkoutHistoryAdapterListener,
        BlogsAdapter.BlogsAdapterListener {

    private HomeViewModel homeViewModel;
    private MainViewModel mainViewModel;

    // Views
    private View root;
    private RecyclerView recyclerView;
    private RecyclerView blogsRecycler;
    private Button btnOngoingWorkoutMessage;
    private FloatingActionButton fab;
    private WorkoutHistoryAdapter workoutHistoryAdapter;
    private BlogsAdapter blogsAdapter;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_home, container, false);
        this.homeViewModel = ViewModelProviders.of(this).get(HomeViewModel.class);
        this.mainViewModel = MainViewModel.getInstance();
        this.initViews();
        this.startObserving();

        return root;
    }

    private void initViews() {
        fab = root.findViewById(R.id.fab_add_workout);
        fab.setOnClickListener(this);
        btnOngoingWorkoutMessage = root.findViewById(R.id.btn_ongoingWorkoutMessage);
        btnOngoingWorkoutMessage.setOnClickListener(this);

        // Workouts recycler
        recyclerView = root.findViewById(R.id.workout_recycler);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        workoutHistoryAdapter = new WorkoutHistoryAdapter(this);
        recyclerView.setAdapter(workoutHistoryAdapter);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(),
                layoutManager.getOrientation());
        recyclerView.addItemDecoration(dividerItemDecoration);

        // Blogs recycler
        blogsRecycler = root.findViewById(R.id.blog_recycler);
        LinearLayoutManager blogsLayoutManager = new LinearLayoutManager(getActivity());
        blogsRecycler.setLayoutManager(blogsLayoutManager);
        blogsAdapter = new BlogsAdapter(this);
        blogsRecycler.setAdapter(blogsAdapter);
        DividerItemDecoration blogsDividerItemDecoration = new DividerItemDecoration(blogsRecycler.getContext(),
                layoutManager.getOrientation());
        blogsRecycler.addItemDecoration(blogsDividerItemDecoration);
        blogsRecycler.setNestedScrollingEnabled(false);
    }

    private void startObserving() {
        mainViewModel.getOngoingWorkout().observe(this, workout -> {
            if (workout != null) {
                btnOngoingWorkoutMessage.setText("Идет тренировка");
                btnOngoingWorkoutMessage.setVisibility(View.VISIBLE);
                fab.hide();
            } else {
                fab.show();
                btnOngoingWorkoutMessage.setVisibility(View.GONE);
            }
        });

        homeViewModel.getLastWorkouts().observe(this, workouts -> {
            workoutHistoryAdapter.setItems(workouts);
        });

        homeViewModel.getBlogPosts().observe(this, blogPosts -> {
            blogsAdapter.setItems(blogPosts);
            for (BlogPost post: blogPosts) {
                Log.d("blogs", post.getTitle());
            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onClick(View v) {
        NavController controller = NavHostFragment.findNavController(this);

        switch (v.getId()) {
            case R.id.fab_add_workout:
                controller.navigate(R.id.workoutNewFragment);
                break;
            case R.id.btn_ongoingWorkoutMessage:
                controller.navigate(R.id.workoutProcessFragment);
                break;
        }

    }

    @Override
    public void navigateToWorkoutHistoryDetail(int id) {
        NavController controller = NavHostFragment.findNavController(this);

        Bundle args = new Bundle();
        args.putInt(Workout.ARG_WORKOUT_ID, id);

        controller.navigate(R.id.nav_workout_history_detail, args);
    }

    @Override
    public void onBlogsPostSelected(int id) {
        Toast.makeText(getActivity(), "Blog #" + id, Toast.LENGTH_SHORT).show();
    }
}