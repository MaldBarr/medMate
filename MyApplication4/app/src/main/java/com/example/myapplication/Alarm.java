package com.example.myapplication;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class Alarm extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        String medicamentoNombre = intent.getStringExtra("MEDICAMENTO_NOMBRE");
        String dosis = intent.getStringExtra("DOSIS");
        String cantidad = intent.getStringExtra("CANTIDAD");

        Intent serviceIntent = new Intent(context, AlarmService.class);
        serviceIntent.putExtra("MEDICAMENTO_NOMBRE", medicamentoNombre);
        serviceIntent.putExtra("DOSIS", dosis);
        serviceIntent.putExtra("CANTIDAD", cantidad);
        context.startForegroundService(serviceIntent);
    }
}
