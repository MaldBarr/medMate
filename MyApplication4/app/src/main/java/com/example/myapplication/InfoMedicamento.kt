package com.example.myapplication

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class InfoMedicamento : AppCompatActivity() {
    @SuppressLint("WrongViewCast")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_info_medicamento)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val idMedicamento = intent.getStringExtra("id_medicamento")
        val idFormato = intent.getStringExtra("id_formato")
        val idFrecuencia = intent.getStringExtra("id_frecuencia")
        val dosis = intent.getStringExtra("dosis")
        val cantidad = intent.getStringExtra("cantidad")
        val hora = intent.getStringExtra("hora")
        val partes = hora?.split(":")
        val parteHora = partes?.get(0)
        val parteMinuto = partes?.get(1)
        val minutoForm = parteMinuto?.toIntOrNull()
        val minuto = if (minutoForm != null) String.format("%02d", minutoForm) else minutoForm


        val formatoArray = resources.getStringArray(R.array.formatos_array)
        val frecuenciaArray = resources.getStringArray(R.array.frecuencia)

        if (idMedicamento != null) {
            val textViewMedicamento = findViewById<TextView>(R.id.infoMedicamento)
            textViewMedicamento.setText(idMedicamento)
        }
        if (idFormato != null) {
            val textViewFormato = findViewById<TextView>(R.id.infoFormatoMedicamento)
            textViewFormato.setText(formatoArray[idFormato.toInt()-1])
        }
        if (idFrecuencia != null) {
            val textViewFrecuencia = findViewById<TextView>(R.id.infoFrecuenciaMedicamento)
            textViewFrecuencia.setText(frecuenciaArray[idFrecuencia.toInt()-1])
        }
        if (dosis != null) {
            val textViewDosis = findViewById<TextView>(R.id.textViewDosis)
            textViewDosis.setText(dosis)
        }
        if (cantidad != null) {
            val textViewCantidad = findViewById<TextView>(R.id.textViewCantidad)
            textViewCantidad.setText(cantidad)
        }
        if (hora != null) {
            val textViewHora = findViewById<TextView>(R.id.infoHoraMedicamento)
            textViewHora.setText(parteHora + ":" + minuto)
        }


        val btnVolver = findViewById<Button>(R.id.button6)
        btnVolver.setOnClickListener {
            finish()
        }
    }
}