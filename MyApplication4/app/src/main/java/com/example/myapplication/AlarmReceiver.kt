package com.example.myapplication

import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.core.app.NotificationCompat

public class AlarmReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        Log.d("AlarmReceiver", "Alarma recibida")
        showNotification(context)
    }

    private fun showNotification(context: Context?) {
        val notificationIntent = Intent(context, MainActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(context, 0, notificationIntent, 0)

        val notification = NotificationCompat.Builder(context!!, "channelId")
            .setContentTitle("¡Hora de tomar tu medicamento!")
            .setContentText("Recuerda tomar tu medicamento.")
            .setSmallIcon(R.drawable.ic_alarm)
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)
            .build()

        val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.notify(0, notification)
    }
}
