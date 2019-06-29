package online.yourfit.services;

import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import online.yourfit.data.user.User;

import online.yourfit.data.user.UserRepository;

public class InitUser {

    private static final String TAG = "InitUser";

    private AppCompatActivity activity;

    private User user;

    public InitUser(AppCompatActivity activity) {
        this.activity = activity;
    }

    public User getUser() {
        if (this.user != null) {
            return this.user;
        }

        fetchFromApi();

        return this.user;
    }

    private void fetchFromApi() {
        /*
        Call<User> call = NetworkService.getInstance()
                .getUserApi()
                .getUser();

        Log.d(TAG, call.request().url() + "");

        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(@NonNull Call<User> call, @NonNull Response<User> response) {
                user = response.body();
                if (user != null) {
                    // Show user info on screen
                    SetUserInfo.execute(activity, user);

                    // Cache in database
                    saveUserInDatabase(user);
                } else {
                    Log.d(TAG, "User not found.");
                }
            }

            @Override
            public void onFailure(@NonNull Call<User> call, @NonNull Throwable t) {
                Toast.makeText(activity, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
         */
    }

    private void saveUserInDatabase(User user) {
        Log.d(TAG, "Saving user in database...");

        UserRepository userRepository = new UserRepository(this.activity.getApplication());
        userRepository.deleteAll();
        userRepository.insert(user);
    }

}
