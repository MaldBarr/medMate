package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

public class SetAlarm extends AppCompatActivity implements View.OnClickListener {
    TimePicker tp;
    Button btn_set, btn_cancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_alarm);

        btn_set = findViewById(R.id.set_alarm);
        btn_cancel = findViewById(R.id.cancel_alarm);
        tp = findViewById(R.id.time);



        btn_set.setOnClickListener(this);
        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Alam_cancel();
            }
        });
    }

    private void Alam_cancel() {
        AlarmManager manager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(this, Alarm.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0, intent, PendingIntent.FLAG_IMMUTABLE);
        manager.cancel(pendingIntent);
        Toast.makeText(this, "Your Alarm is canceled", Toast.LENGTH_SHORT).show();

        // Regresar a la actividad principal (bottomNav)
        Intent mainIntent = new Intent(this, bottomNav.class);
        startActivity(mainIntent);

        // Finalizar esta actividad
        finish();
    }

    @Override
    public void onClick(View v) {
        Calendar cal = Calendar.getInstance();
        cal.set(cal.get(Calendar.YEAR),
                cal.get(Calendar.MONTH),
                cal.get(Calendar.DAY_OF_MONTH),
                tp.getHour(),
                tp.getMinute(), 0);

        // Obtener el medicamento seleccionado, dosis y cantidad de SharedPreferences
        SharedPreferences sharedPref = getSharedPreferences("MyPref", Context.MODE_PRIVATE);
        String medicamentoSeleccionado = sharedPref.getString("MEDICAMENTO_SELECCIONADO", "Nombre del medicamento");
        String dosis = sharedPref.getString("SELECTED_DOSIS", "0");
        String cantidad = sharedPref.getString("SELECTED_CANTIDAD", "0");

        // Guardar la hora seleccionada en SharedPreferences
        SharedPreferences.Editor editor = sharedPref.edit();
        String selectedHour = tp.getHour() + ":" + tp.getMinute();
        editor.putString("SELECTED_HORA", selectedHour);
        editor.apply();

        Alarm_set(cal.getTimeInMillis(), medicamentoSeleccionado, dosis, cantidad);
    }

    @SuppressLint("ScheduleExactAlarm")
    private void Alarm_set(long timeInMillis, String medicamentoNombre, String dosis, String cantidad) {
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(this, Alarm.class);
        intent.putExtra("MEDICAMENTO_NOMBRE", medicamentoNombre);
        intent.putExtra("DOSIS", dosis);
        intent.putExtra("CANTIDAD", cantidad);

        // Generar un requestCode único utilizando el tiempo actual en milisegundos
        int requestCode = (int) System.currentTimeMillis();

        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, requestCode, intent, PendingIntent.FLAG_IMMUTABLE);

        alarmManager.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, timeInMillis, pendingIntent);

        Toast.makeText(this, "Alarm is set", Toast.LENGTH_SHORT).show();

        // Regresar a la actividad principal (bottomNav)
        Intent mainIntent = new Intent(this, Guardando_Medicamento.class);
        startActivity(mainIntent);

        // Finalizar esta actividad
        finish();
    }
}
