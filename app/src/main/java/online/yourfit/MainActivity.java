package online.yourfit;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.os.Bundle;

import online.yourfit.ui.exercises.ExerciseDetailFragment;
import online.yourfit.ui.exercises.ExercisesAdapter;
import online.yourfit.ui.home.WorkoutDetailFragment;
import online.yourfit.ui.home.WorkoutHistoryAdapter;

import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity
        implements WorkoutHistoryAdapter.IDetailWorkoutListener,
                   ExercisesAdapter.IDetailExerciseListener {

    private AppBarConfiguration mAppBarConfiguration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);

        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home,
                R.id.nav_programs,
                R.id.nav_exercises,
                R.id.nav_measurements
        )
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }


    @Override
    public void openDetailWorkoutFragment(int i) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.nav_host_fragment, WorkoutDetailFragment.newInstance(i))
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void openDetailExerciseFragment(int i) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.nav_host_fragment, ExerciseDetailFragment.newInstance(i))
                .addToBackStack(null)
                .commit();
    }
}
