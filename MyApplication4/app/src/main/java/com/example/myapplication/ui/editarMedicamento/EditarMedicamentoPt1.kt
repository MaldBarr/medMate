package com.example.myapplication.ui.editarMedicamento

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.myapplication.R
import com.example.myapplication.slidebar

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
        val idRecordatorio = intent.getStringExtra("id_recordatorio")
        val idMedicamento = intent.getStringExtra("id_medicamento")
        val idFormato = intent.getStringExtra("id_formato")
        val idFrecuencia = intent.getStringExtra("id_frecuencia")
        val medicamentosArray = resources.getStringArray(R.array.medicamentos)
        val adapter = ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, medicamentosArray)
        val autoCompleteTextView = findViewById<AutoCompleteTextView>(R.id.editTextText4)
        autoCompleteTextView.setAdapter(adapter)
        Log.d("EditarMedicamentoPt1", "idMedicamento: $idMedicamento")

        if (idMedicamento != null) {
            autoCompleteTextView.setText(idMedicamento, false)
        }

        val btnEditSiguiente1 = findViewById<Button>(R.id.btnEditSiguiente1)
        btnEditSiguiente1.setOnClickListener {
            val medicamentoSeleccionado = autoCompleteTextView.text.toString()
            val sharedPref = getSharedPreferences("MyPref", Context.MODE_PRIVATE)
            val editor = sharedPref.edit()
            editor.putString("MEDICAMENTO_SELECCIONADO", medicamentoSeleccionado)
            editor.apply()
            val intent = Intent(this, EditarMedicamentoPt2::class.java)
            intent.putExtra("id_recordatorio", idRecordatorio)
            Log.d("EditarMedicamentoPt1", "idRecordatorio: $idRecordatorio")
            intent.putExtra("id_formato", idFormato)
            intent.putExtra("id_frecuencia", idFrecuencia)
            startActivity(intent)
        }
        val btnCancelar1 = findViewById<Button>(R.id.btnEditCancelar1)
        btnCancelar1.setOnClickListener {
            val intent = Intent(this, slidebar::class.java)
            startActivity(intent)
        }
    }
}