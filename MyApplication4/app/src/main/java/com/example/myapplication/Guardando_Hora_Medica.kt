package com.example.myapplication

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.myapplication.data.RetrofitInstance
import com.example.myapplication.data.models.HoraMedicaReq
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

class Guardando_Hora_Medica : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
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

        val Tratamiento = intent.getStringExtra("Tratamiento")
        val Fecha = intent.getStringExtra("Fecha")
        val Hora = intent.getStringExtra("Hora")
        val Minuto = intent.getStringExtra("Minuto")
        val sdf = SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy", Locale.ENGLISH)
        val date : Date = sdf.parse(Fecha) ?: Date()

        // Crear un SimpleDateFormat para analizar la cadena de fecha

        CoroutineScope(Dispatchers.IO).launch{
            val response = RetrofitInstance.api.createHoraMedica(
                HoraMedicaReq(
                    id_usuario,
                    Tratamiento,
                    date,
                    Hora?.toInt(),
                    Minuto?.toInt()
                )
            )
            // Verificar que la respuesta es exitosa
            if (response.isSuccessful) {
                // El recordatorio se creó exitosamente
                Log.d("SharedViewModel", "Recordatorio creado exitosamente")
                runOnUiThread {
                    val textView = findViewById<TextView>(R.id.guardando_guardado)
                    textView.text = "Hora Medica Guardada"
                }

                kotlinx.coroutines.delay(2000)

                // Redirigir a la actividad Slidebar
                val intent = Intent(this@Guardando_Hora_Medica, slidebar::class.java)
                startActivity(intent)
            }
        }

    }
}