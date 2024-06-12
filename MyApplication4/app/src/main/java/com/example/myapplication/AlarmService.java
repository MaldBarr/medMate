package com.example.myapplication;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.IBinder;
import android.provider.Settings;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;

public class AlarmService extends Service {

    private static final String CHANNEL_ID = "AlarmChannel";
    private MediaPlayer mediaPlayer;

    @Override
    public void onCreate() {
        super.onCreate();
        createNotificationChannel();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        mediaPlayer = MediaPlayer.create(this, Settings.System.DEFAULT_ALARM_ALERT_URI);
        mediaPlayer.setLooping(true);
        mediaPlayer.start();

        SharedPreferences sharedPref = getSharedPreferences("MyPref", Context.MODE_PRIVATE);
        String medicamentoSeleccionado = sharedPref.getString("MEDICAMENTO_SELECCIONADO", null);


        Intent stopIntent = new Intent(this, StopAlarmReceiver.class);
        PendingIntent pendingStopIntent = PendingIntent.getBroadcast(this, 0, stopIntent, PendingIntent.FLAG_IMMUTABLE);

        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setContentTitle("¡Recuerda tomar tu medicamento!")
                .setContentText(medicamentoSeleccionado)
                .setSmallIcon(R.drawable.ic_alarm)
                .addAction(R.drawable.ic_stop, "Aceptar", pendingStopIntent)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setOngoing(true);

        startForeground(1, notificationBuilder.build());

        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mediaPlayer != null) {
            mediaPlayer.stop();
            mediaPlayer.release();
        }
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    private void createNotificationChannel() {
        CharSequence name = "Alarm Channel";
        String description = "Channel for Alarm";
        int importance = NotificationManager.IMPORTANCE_HIGH;
        NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
        channel.setDescription(description);

        NotificationManager notificationManager = getSystemService(NotificationManager.class);
        notificationManager.createNotificationChannel(channel);
    }
}
