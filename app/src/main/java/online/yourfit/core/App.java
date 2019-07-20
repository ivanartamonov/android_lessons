package online.yourfit.core;

import android.app.Application;

import online.yourfit.di.AppComponent;
import online.yourfit.di.DaggerAppComponent;
import online.yourfit.di.DataModule;
import online.yourfit.di.NetworkModule;

public class App extends Application {

    public static App instance;

    private AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        appComponent = DaggerAppComponent.builder()
                .networkModule(new NetworkModule())
                .dataModule(new DataModule(getApplicationContext()))
                .build();

        instance = this;
    }

    public AppComponent getAppComponent() {
        return appComponent;
    }

}
