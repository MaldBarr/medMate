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


        val btnVolver = findViewById<Button>(R.id.button6)
        btnVolver.setOnClickListener {
            finish()
        }
    }
}