package app.autosilenceapp.onboardScreens;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;

import app.autosilenceapp.R;

public class AudioService extends Service {

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i("tag", "onCreate: Created Notificaion");
        if (Build.VERSION.SDK_INT >= 26) {
            String CHANNEL_ID = "my_channel_01";
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID,
                    "Channel human readable title",
                    NotificationManager.IMPORTANCE_DEFAULT);

            ((NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE)).createNotificationChannel(channel);

            Notification notification = new NotificationCompat.Builder(this, CHANNEL_ID)
                    .setContentTitle("Audio service running")
                    .setContentText("Playing Azan").build();

            startForeground(1, notification);
        }
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i("tag", "startCommand: Created Notificaion");
        if (Build.VERSION.SDK_INT >= 26) {
            String CHANNEL_ID = "my_channel_01";
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID,
                    "Channel human readable title",
                    NotificationManager.IMPORTANCE_DEFAULT);

            ((NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE)).createNotificationChannel(channel);

            Notification notification = new NotificationCompat.Builder(this, CHANNEL_ID)
                    .setContentTitle("Audio service running")
                    .setContentText("Playing Azan").build();

            startForeground(1, notification);
        }
        silent();

        return super.onStartCommand(intent, flags, startId);
    }
    public void silent() {
        MediaPlayer mp = MediaPlayer.create(this, R.raw.azan1);
        mp.start();
        mp.setOnCompletionListener(mp1 -> {
            stopForeground(true);
            stopSelf();
        });

//
//        AudioManager am = (AudioManager) this.getSystemService(Context.AUDIO_SERVICE);
//        NotificationManager notificationManager = (NotificationManager) getBaseContext().getSystemService(Context.NOTIFICATION_SERVICE);
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M
//                && !notificationManager.isNotificationPolicyAccessGranted()) {
//
//            Intent intent = new Intent(
//                    android.provider.Settings
//                            .ACTION_NOTIFICATION_POLICY_ACCESS_SETTINGS);
//            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//            startActivity(intent);
//        }
//
//        switch (am.getRingerMode()) {
//            case AudioManager.RINGER_MODE_NORMAL:
//                am.setRingerMode(AudioManager.RINGER_MODE_SILENT);
//                Log.d("executed", "silent");
//                break;
//            case AudioManager.RINGER_MODE_SILENT:
//                am.setRingerMode(AudioManager.RINGER_MODE_NORMAL);
//                Log.d("executed", "general");
//                break;
//            case AudioManager.RINGER_MODE_VIBRATE:
//                break;
//        }


        // Toast.makeText(this, "phone is silent", Toast.LENGTH_SHORT).show();
    }
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
