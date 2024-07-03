// AlarmService.java
package com.example.myapplication;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Handler;
import android.os.IBinder;
import android.provider.Settings;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;

public class AlarmService extends Service {

    private static final String CHANNEL_ID = "AlarmChannel";
    private static final int NOTIFICATION_ID = 1;
    private MediaPlayer mediaPlayer;
    private Handler handler = new Handler();
    private boolean isAlarmActive = false;

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
        isAlarmActive = true;

        // Obtener los extras del intent
        String medicamentoNombre = intent.getStringExtra("MEDICAMENTO_NOMBRE");
        String dosis = intent.getStringExtra("DOSIS");
        String cantidad = intent.getStringExtra("CANTIDAD");

        if (medicamentoNombre == null || dosis == null || cantidad == null) {
            medicamentoNombre = "Medicamento";
            dosis = "X";
            cantidad = "X";
        }

        String notificationText = medicamentoNombre + " " + dosis + " mg (" + cantidad + ")";

        // Intent para el botón "Aceptar"
        Intent stopIntent = new Intent(this, StopAlarmReceiver.class);
        PendingIntent pendingStopIntent = PendingIntent.getBroadcast(this, 0, stopIntent, PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE);

        // Intent para el botón "Posponer"
        Intent postponeIntent = new Intent(this, PostponeAlarmReceiver.class);
        postponeIntent.putExtra("MEDICAMENTO_NOMBRE", medicamentoNombre);
        postponeIntent.putExtra("DOSIS", dosis);
        postponeIntent.putExtra("CANTIDAD", cantidad);
        PendingIntent pendingPostponeIntent = PendingIntent.getBroadcast(this, 0, postponeIntent, PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE);

        // Construcción de la notificación
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setContentTitle("¡Recuerda tomar tu medicamento!")
                .setContentText(notificationText)
                .setStyle(new NotificationCompat.BigTextStyle().bigText(notificationText))
                .setSmallIcon(R.drawable.ic_alarm)
                .addAction(R.drawable.ic_stop, "Aceptar", pendingStopIntent)
                .addAction(R.drawable.ic_stop, "Posponer", pendingPostponeIntent)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setOngoing(true);

        startForeground(NOTIFICATION_ID, notificationBuilder.build());

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

    // BroadcastReceiver para manejar la acción de posponer
    public static class PostponeAlarmReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            // Detener la alarma actual
            Intent stopIntent = new Intent(context, AlarmService.class);
            context.stopService(stopIntent);

            // Remover la notificación
            NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
            notificationManager.cancel(NOTIFICATION_ID);

            // Programar la alarma para que suene nuevamente después de 20 segundos
            String medicamentoNombre = intent.getStringExtra("MEDICAMENTO_NOMBRE");
            String dosis = intent.getStringExtra("DOSIS");
            String cantidad = intent.getStringExtra("CANTIDAD");

            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent serviceIntent = new Intent(context, AlarmService.class);
                    serviceIntent.putExtra("MEDICAMENTO_NOMBRE", medicamentoNombre);
                    serviceIntent.putExtra("DOSIS", dosis);
                    serviceIntent.putExtra("CANTIDAD", cantidad);
                    context.startForegroundService(serviceIntent);
                }
            }, 20000); // 20 segundos en milisegundos
        }
    }
}
