// Guardando_Hora_Medica.kt
package com.example.myapplication

import android.annotation.SuppressLint
import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.myapplication.data.RetrofitInstance
import com.example.myapplication.data.models.HoraMedicaReq
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.*

class Guardando_Hora_Medica : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_guardando_hora_medica)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val texto = findViewById<TextView>(R.id.guardando_guardado)
        texto.text = "Guardando Hora Medica..."

        val sharedPref = this.getSharedPreferences("MyPref", Context.MODE_PRIVATE)
        val id_usuario = sharedPref.getString("USER_ID", null)

        val tratamiento = intent.getStringExtra("Tratamiento")
        val fechaMillis = intent.getLongExtra("Fecha", 0L)
        val calendar = Calendar.getInstance().apply {
            timeInMillis = fechaMillis
        }

        val date = Date(fechaMillis)
        val hour = calendar.get(Calendar.HOUR_OF_DAY)
        val minute = calendar.get(Calendar.MINUTE)

        CoroutineScope(Dispatchers.IO).launch {
            val response = RetrofitInstance.api.createHoraMedica(
                HoraMedicaReq(
                    id_usuario,
                    tratamiento,
                    date,
                    hour,
                    minute
                )
            )
            // Verificar que la respuesta es exitosa
            if (response.isSuccessful) {
                // El recordatorio se creó exitosamente
                runOnUiThread {
                    texto.text = "Hora Medica Guardada"
                }

                kotlinx.coroutines.delay(2000)

                programarAlarma(tratamiento!!, calendar)
                // Redirigir a la actividad Slidebar
                val intent = Intent(this@Guardando_Hora_Medica, slidebar::class.java)
                startActivity(intent)
            }
        }
    }

    @SuppressLint("ScheduleExactAlarm")
    private fun programarAlarma(tratamiento: String, calendar: Calendar) {
        val intent = Intent(this, AlarmReceiver::class.java).apply {
            putExtra("TRATAMIENTO", tratamiento)
        }
        val pendingIntent = PendingIntent.getBroadcast(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE)
        val alarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager
        alarmManager.setExact(AlarmManager.RTC_WAKEUP, calendar.timeInMillis, pendingIntent)
    }
}
