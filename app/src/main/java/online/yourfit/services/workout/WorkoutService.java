package online.yourfit.services.workout;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.IBinder;
import android.util.Log;

import online.yourfit.R;
import online.yourfit.ui.MainActivity;

public class WorkoutService extends Service {

    public static final String TAG = "WorkoutService";
    public static final String ACTION_START = "START";
    public static final String ACTION_STOP = "STOP";

    private static final int NOTIFICATION_ID = 1;
    private static final int REQUEST_CODE = 1;
    private static final String CHANNEL_ID = "WorkoutService";

    @Override
    public IBinder onBind(Intent intent) {
        Log.d(TAG, "onBind");
        return null;
    }

    @Override
    public void onCreate() {
        createNotificationChannel();
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, "onStartCommand");
        Log.d(TAG, "Action: " + intent.getAction());

        startForeground(NOTIFICATION_ID, createNotification());

        if (intent.getAction() != null) {
            switch (intent.getAction()) {
                case ACTION_START:
                    break;
                case ACTION_STOP:
                    break;
            }
        }

        return START_STICKY;
    }

    private void createNotificationChannel(){
        Log.d(TAG, "createNotificationChannel");

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            NotificationManager notificationManager =
                    (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, "My channel",
                    NotificationManager.IMPORTANCE_DEFAULT);
            notificationManager.createNotificationChannel(channel);
        }
    }

    private Notification createNotification(){
        Log.d(TAG, "createNotification");

        Notification.Builder notificationBuilder;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
            notificationBuilder = new Notification.Builder(this, CHANNEL_ID);
        else
            notificationBuilder = new Notification.Builder(this);

        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        intent.putExtra("navigateTo", "workout");
        PendingIntent pi = PendingIntent.getActivity(this, REQUEST_CODE, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        return notificationBuilder
                .setContentTitle("Идет тренировка")
                .setContentText("Приседы со штангой на плечах")
                .setSmallIcon(R.drawable.ic_fire)
                .setContentIntent(pi)
                .build();
    }
}
