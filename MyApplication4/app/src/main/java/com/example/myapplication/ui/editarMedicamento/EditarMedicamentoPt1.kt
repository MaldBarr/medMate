package com.example.myapplication.ui.editarMedicamento

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.myapplication.R

class EditarMedicamentoPt1 : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_editar_medicamento_pt1)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val medicamentosArray = resources.getStringArray(R.array.medicamentos)
        val adapter = ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, medicamentosArray)
        val autoCompleteTextView = findViewById<AutoCompleteTextView>(R.id.editTextText4)
        autoCompleteTextView.setAdapter(adapter)

        val btnEditSiguiente1 = findViewById<Button>(R.id.btnEditSiguiente1)
        btnEditSiguiente1.setOnClickListener {
            val medicamentoSeleccionado = autoCompleteTextView.text.toString()
            val sharedPref = getSharedPreferences("MyPref", Context.MODE_PRIVATE)
            val editor = sharedPref.edit()
            editor.putString("MEDICAMENTO_SELECCIONADO", medicamentoSeleccionado)
            editor.apply()
            val intent = Intent(this, EditarMedicamentoPt2::class.java)
        }
    }
}