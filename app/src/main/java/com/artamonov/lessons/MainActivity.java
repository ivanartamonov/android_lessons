package com.artamonov.lessons;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.artamonov.lessons.models.User;
import com.artamonov.lessons.services.InitUser;
import com.artamonov.lessons.ui.home.WorkoutDetailFragment;
import com.artamonov.lessons.ui.home.WorkoutHistoryAdapter;
import com.bumptech.glide.Glide;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity implements WorkoutHistoryAdapter.IDetailWorkoutListener {

    private AppBarConfiguration mAppBarConfiguration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);

        mAppBarConfiguration = new AppBarConfiguration.Builder(R.id.nav_home, R.id.nav_programs)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

        this.setUser();
    }

    private void setUser() {
        InitUser userDefiner = new InitUser(this);
        User user = userDefiner.getUser();

        /*
        if (user != null) {
            TextView tvUserName = findViewById(R.id.tv_user_name);
            ImageView userAvatar = findViewById(R.id.img_user_avatar);

            tvUserName.setText(user.getName());

            Glide.with(this)
                    .load(user.getAvatarUrl())
                    .into(userAvatar);
        }
         */
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
}
