package com.example.myapplication.ui.editarMedicamento

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.myapplication.R

class EditarMedicamentoPt2 : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_editar_medicamento_pt2)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val idFormato = intent.getStringExtra("id_formato")?.toInt()
        val id_frecuencia = intent.getStringExtra("id_frecuencia")
        val id_recordatorio = intent.getStringExtra("id_recordatorio")
        val formatosArray = resources.getStringArray(R.array.formatos_array)
        val radioGroup = findViewById<RadioGroup>(R.id.radioEdit)

        // Limpiar todos los RadioButton existentes en el RadioGroup
        radioGroup.removeAllViews()

        // Crear un RadioButton para cada formato y agregarlo al RadioGroup
        for (i in formatosArray.indices) {
            val radioButton = RadioButton(this)
            radioButton.text = formatosArray[i]
            radioButton.id = i
            radioGroup.addView(radioButton)

            // Si idFormato coincide con el índice, marca este RadioButton
            if (idFormato != null) {
                if (i == (idFormato-1)) {
                    radioButton.isChecked = true
                }
            }
        }

        radioGroup.setOnCheckedChangeListener { group, checkedId ->
            val radioButton = group.findViewById<RadioButton>(checkedId)
            val selectedFormat = radioButton.text.toString()

            val sharedPref = getSharedPreferences("MyPref", Context.MODE_PRIVATE)
            val editor = sharedPref.edit()
            editor.putString("SELECTED_FORMAT", selectedFormat)
            editor.apply()
        }

        val btnEditSiguiente2 = findViewById<Button>(R.id.btnEditSiguiente2)
        btnEditSiguiente2.setOnClickListener {
            val intent = Intent(this, EditarMedicamentoPt3::class.java)
            intent.putExtra("id_recordatorio", id_recordatorio)
            Log.d("EditarMedicamentoPt2", "id_recordatorio: $id_recordatorio")
            intent.putExtra("id_frecuencia", id_frecuencia)
            startActivity(intent)
        }
    }
}