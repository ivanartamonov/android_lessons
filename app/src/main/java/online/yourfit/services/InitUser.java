package online.yourfit.services;

import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import online.yourfit.core.App;
import online.yourfit.core.db.AppDatabase;
import online.yourfit.user.UserDao;
import online.yourfit.models.User;
import online.yourfit.network.NetworkService;
import online.yourfit.ui.SetUserInfo;

import java.util.concurrent.Callable;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Задача такая: при запуске приложения, нужно "залогинить" пользоватея.
 * Самой процедуры логина нет и пока не будет, мне нужно просто хардкорно
 * взять юезара по ID из АПИ и залогинить его.
 *
 * "Залогинить" - имеется ввиду, что в дровере будет его имя и аватарка.
 *
 * Что я пытаюсь селать:
 * 1) Проверить, есть ли юзер в локальной базе
 * 2) Если есть, то достаю его оттуда и "логиню". Т.е. пишу в дровере его имя и ставлю аватарку
 * Если в базе нету, то:
 * 3) Получаю его по API, "логиню" и попутно сохраняю в локальную базу на будущее
 *
 * Проблемы:
 * - почему-то нельзя обратиться к activity внутри Consumer класса ниже (см.комменты далее)
 * - ощущение, что тут вообще всё неправильно... Как можно более лаконично и "читаемо" делать вызовы к API и базе?
 * За всей этой шелухой и бесконечными обертками трудно прочитать что вообще происходит.
 */
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

        //fetchFromApi(); // Этот метод работает

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

        // Как всю эту херню написать нормально? Весь этот асинхронный rx вызов?
        // Это же какая-то жесть...
        observable.subscribe(new Consumer<User>() {
            @Override
            public void accept(final User dbUser) throws Exception {
                Log.d(TAG, "Fetched from DB: " + dbUser.getName()); // Работает, выводит имя
                if (dbUser != null) {
                    // При попытке обратиться к activity отсюда, приложение вылетает.
                    // Log.d(TAG, activity.getCallingActivity().toString()); // КРАШ...

                    // SetUserInfo должен установит имя и аватарку в дровере
                    // SetUserInfo.execute(activity, dbUser);
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

        // Снова какая-то жесть... мне нужно вызвать 2 строчки кода, сохраняющие юзера в базу...
        // А для этого надо написать 25 строчек с двумя анонимными классами...
        // Как это делать правильно?

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
