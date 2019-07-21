package online.yourfit.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import online.yourfit.R;
import online.yourfit.data.user.User;

import com.bumptech.glide.Glide;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    private MainViewModel viewModel;
    private NavController navController;

    // Views
    Toolbar toolbar;
    DrawerLayout drawer;
    NavigationView navigationView;
    TextView tvUserName;
    ImageView imgUserAvatar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new MainViewModel(this.getApplication());
        setContentView(R.layout.main_activity);

        this.processIntent(getIntent());
        this.initViews();
        this.initNavigation();
        this.startObserving();
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        this.processIntent(intent);
    }

    private void processIntent(Intent intent) {
        String navigateArg = intent.getStringExtra("navigateTo");
        if (navigateArg != null && navigateArg.equals("workout")) {
            navController = Navigation.findNavController(this, R.id.nav_host_fragment);
            navController.navigate(R.id.workoutNewFragment);
        }
    }

    private void initViews() {
        this.toolbar = findViewById(R.id.toolbar);
        this.drawer = findViewById(R.id.drawer_layout);
        this.navigationView = findViewById(R.id.nav_view);
        this.tvUserName = navigationView.getHeaderView(0).findViewById(R.id.tv_user_name);
        this.imgUserAvatar = navigationView.getHeaderView(0).findViewById(R.id.img_user_avatar);
    }

    private void startObserving() {
        this.viewModel.getUser().observe(this, this::displayUserInfo);
    }

    private void initNavigation() {
        setSupportActionBar(this.toolbar);
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home,
                R.id.nav_programs,
                R.id.nav_exercises,
                R.id.nav_measurements
        )
                .setDrawerLayout(drawer)
                .build();

        navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
    }

    private void displayUserInfo(User user) {
        tvUserName.setText(user.getName());
        Glide.with(this)
                .asBitmap()
                .load(user.getAvatarUrl())
                .into(this.imgUserAvatar);
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }
}
