package com.example.myapplication;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class PostponeAlarmReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        // No se requiere código adicional aquí, la lógica de posponer está en AlarmService.PostponeAlarmReceiver
    }
}
