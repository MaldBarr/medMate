package com.example.myapplication.ui

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.R
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class VerInfoHoraMedica : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ver_info_hora)

        val botonVolver = findViewById<Button>(R.id.button7)
        botonVolver.setOnClickListener {
            finish() // Cierra y vuelve a la anterior
        }
        val nombre = intent.getStringExtra("nombre")
        val fecha = intent.getStringExtra("fecha")
        val hora = intent.getStringExtra("hora")
        val minutoString = intent.getStringExtra("minuto")
        val minutoForm = minutoString?.toIntOrNull()
        val minuto = if (minutoForm != null) String.format("%02d", minutoForm) else minutoForm

        Log.d("Fecha", "Fecha:"+fecha)
        Log.d("hora", "hora:"+hora)
        Log.d("minuto", "minuto:"+minuto)

        if (nombre != null) {
            val textViewNombre = findViewById<TextView>(R.id.infoHoraMedica)
            textViewNombre.setText(nombre)
        }
        if (fecha != null) {
            val textViewFecha = findViewById<TextView>(R.id.InfoFecha)

            // Crear un SimpleDateFormat para analizar la cadena de fecha
            val formatoEntrada = SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy", Locale.ENGLISH)
            val fechaDate: Date? = formatoEntrada.parse(fecha)

            // Crear un SimpleDateFormat para formatear la fecha en el formato deseado
            val formatoSalida = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
            val fechaFormateada: String = formatoSalida.format(fechaDate)

            textViewFecha.text = fechaFormateada
        }
        if (hora != null){
            if (minuto != null) {
                val textViewHora = findViewById<TextView>(R.id.InfoHora)
                textViewHora.setText(hora + ":" + minuto)
            }
        }
    }
}