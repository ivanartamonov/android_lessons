package online.yourfit.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import online.yourfit.R;
import online.yourfit.data.user.User;

import com.bumptech.glide.Glide;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity implements FragmentOpener {

    private AppBarConfiguration mAppBarConfiguration;
    private MainViewModel viewModel;

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

        this.initViews();
        this.initNavigation();
        this.setUser();
    }

    private void initViews() {
        this.toolbar = findViewById(R.id.toolbar);
        this.drawer = findViewById(R.id.drawer_layout);
        this.navigationView = findViewById(R.id.nav_view);
        this.tvUserName = navigationView.getHeaderView(0).findViewById(R.id.tv_user_name);
        this.imgUserAvatar = navigationView.getHeaderView(0).findViewById(R.id.img_user_avatar);
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
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
    }

    private void setUser() {
        this.viewModel.getUser().observe(this, new Observer<User>() {
            @Override
            public void onChanged(User user) {
                if (user != null) {
                    displayUserInfo(user);
                }
            }
        });
    }

    private void displayUserInfo(User user) {
        tvUserName.setText(user.getName());
        Glide.with(this)
                .load(user.getAvatarUrl())
                .into(this.imgUserAvatar);
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    @Override
    public void showFragment(Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.nav_host_fragment, fragment)
                .addToBackStack(null)
                .commit();
    }
}
