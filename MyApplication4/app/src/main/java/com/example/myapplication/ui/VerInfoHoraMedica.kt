package com.example.myapplication.ui

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.R

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
        val minuto = intent.getStringExtra("minuto")

        if (nombre != null) {
            val textViewNombre = findViewById<TextView>(R.id.infoHoraMedica)
            textViewNombre.setText(nombre)
        }
        if (fecha != null) {
            val textViewFecha = findViewById<TextView>(R.id.InfoFecha)
            textViewFecha.setText(fecha)
        }
        if (hora != null){
            if (minuto != null) {
                val textViewHora = findViewById<TextView>(R.id.InfoHora)
                textViewHora.setText(hora + ":" + minuto)
            }
        }
    }
}