package com.artamonov.lessons.services;

import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.artamonov.lessons.core.App;
import com.artamonov.lessons.core.db.AppDatabase;
import com.artamonov.lessons.data.user.UserDao;
import com.artamonov.lessons.models.User;
import com.artamonov.lessons.network.NetworkService;
import com.artamonov.lessons.ui.SetUserInfo;

import java.util.concurrent.Callable;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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

        loadUser();
        //fetchFromApi();

        return this.user;
    }

    private void loadUser() {
        Log.d(TAG, "Try to load user from database...");

        Observable<User> observable = Observable.fromCallable(new Callable<User>() {
            @Override
            public User call() throws Exception {
                AppDatabase db = App.getInstance().getDatabase();
                UserDao userDao = db.userDao();
                return userDao.getById(17);
            }
        })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());

        observable.subscribe(new Consumer<User>() {
            @Override
            public void accept(final User dbUser) throws Exception {
                Log.d(TAG, "Fetched from DB: " + dbUser.getName());
                if (dbUser != null) {
                    SetUserInfo.execute(activity, dbUser);
                } else {
                    //fetchFromApi();
                }
            }
        });
    }

    private void fetchFromApi() {
        Call<User> call = NetworkService.getInstance()
                .getJSONApi()
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
    }

    private void saveUserInDatabase(User user) {
        Log.d(TAG, "Saving user in database...");

        class SaveInDatabaseAction implements Callable<User> {

            private final User user;

            private SaveInDatabaseAction(User user) {
                this.user = user;
            }

            @Override
            public User call() throws Exception {
                AppDatabase db = App.getInstance().getDatabase();
                UserDao userDao = db.userDao();
                userDao.insert(user);
                return user;
            }
        }

        Observable.fromCallable(new SaveInDatabaseAction(user))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<User>() {
                    @Override
                    public void accept(final User user) throws Exception {
                        Log.d(TAG, "Accepted: " + user.getName());
                    }
                });
    }

}
