package com.example.myapplication.ui.editarMedicamento

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.myapplication.R
import com.example.myapplication.data.RetrofitInstance
import com.example.myapplication.data.models.formatoReq
import com.example.myapplication.data.models.frecuenciaReq
import com.example.myapplication.data.models.medicamentosReq
import com.example.myapplication.data.models.recordatorioUpatedReq
import com.example.myapplication.slidebar
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class EditarMedicamentoPt3 : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_editar_medicamento_pt3)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val idRecordatorio = intent.getStringExtra("id_recordatorio")
        val idFrecuencia = intent.getStringExtra("id_frecuencia")?.toInt()
        val frecuenciasArray = resources.getStringArray(R.array.frecuencia)
        val radioGroup = findViewById<RadioGroup>(R.id.radioEdit3)

        // Limpiar todos los RadioButton existentes en el RadioGroup
        radioGroup.removeAllViews()

        // Crear un RadioButton para cada frecuencia y agregarlo al RadioGroup
        for (i in frecuenciasArray.indices) {
            val radioButton = RadioButton(this)
            radioButton.text = frecuenciasArray[i]
            radioButton.id = i
            radioGroup.addView(radioButton)

            // Si idFrecuencia coincide con el índice, marca este RadioButton
            if (idFrecuencia != null) {
                if (i == idFrecuencia-1) {
                    radioButton.isChecked = true
                }
            }
        }

        radioGroup.setOnCheckedChangeListener { group, checkedId ->
            val radioButton = group.findViewById<RadioButton>(checkedId)
            val selectedFrequency = radioButton.text.toString()

            // Guardar el valor seleccionado en las preferencias compartidas
            val sharedPref = getSharedPreferences("MyPref", Context.MODE_PRIVATE)
            val editor = sharedPref.edit()
            editor.putString("SELECTED_FREQUENCY", selectedFrequency)
            editor.apply()
        }

        val btnEditSiguiente3 = findViewById<Button>(R.id.btnEditSiguiente3)
        btnEditSiguiente3.setOnClickListener {
            val sharedPref = getSharedPreferences("MyPref", Context.MODE_PRIVATE)
            val medicamentoSeleccionado = sharedPref.getString("MEDICAMENTO_SELECCIONADO", null)
            val formatoSeleccionado = sharedPref.getString("SELECTED_FORMAT", null)
            val frecuenciaSeleccionada = sharedPref.getString("SELECTED_FREQUENCY", null)

            // Create an instance of your ApiService
            val apiService = RetrofitInstance.api

            // Create a recordatorioUpatedReq object with the necessary data

            // Call the updateRecordatorio method
            CoroutineScope(Dispatchers.IO).launch {

                val idMedicamentoRes = apiService.idMedicamento(medicamentosReq(medicamentoSeleccionado))
                val idFormatoRes = apiService.idformato(formatoReq(formatoSeleccionado) )
                val idfrecuenciaRes = apiService.idFrecuencia(frecuenciaReq(frecuenciaSeleccionada) )
                val request = recordatorioUpatedReq(idRecordatorio, idMedicamentoRes, idFormatoRes, idfrecuenciaRes)
                val response = apiService.updateRecordatorio(request)

                if (response.isSuccessful) {
                    // Handle successful response
                    val updatedRecordatorio = response.body()
                    withContext(Dispatchers.Main) {
                        Toast.makeText(this@EditarMedicamentoPt3, "Recordatorio updated successfully", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    // Handle error
                    withContext(Dispatchers.Main) {
                        Toast.makeText(this@EditarMedicamentoPt3, "Error updating recordatorio", Toast.LENGTH_SHORT).show()
                    }
                }
            }

            val intent = Intent(this, slidebar::class.java)
            startActivity(intent)
        }
    }
}