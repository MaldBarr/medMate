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
import com.example.myapplication.data.models.formatoReq
import com.example.myapplication.data.models.frecuenciaReq
import com.example.myapplication.data.models.medicamentosReq
import com.example.myapplication.data.models.recordatorioReq
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class Guardando_Medicamento : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_guardando_medicamento)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Obtener una instancia de SharedPreferences
        val sharedPref = this.getSharedPreferences("MyPref", Context.MODE_PRIVATE)

        // Obtener el USER_ID de SharedPreferences
        val id = sharedPref.getString("USER_ID", null)

        // Obtener el medicamentoSeleccionado de SharedPreferences
        val medicamentoSeleccionado = sharedPref.getString("MEDICAMENTO_SELECCIONADO", null)

        // Obtener el selectedFormat de SharedPreferences
        val selectedFormat = sharedPref.getString("SELECTED_FORMAT", null)

        // Obtener el selectedFrequency de SharedPreferences
        val selectedFrequency = sharedPref.getString("SELECTED_FREQUENCY", null)

        // Mostrar todos los datos
        Log.d("SharedViewModel", "USER_ID: $id")
        Log.d("SharedViewModel", "medicamentoSeleccionado: $medicamentoSeleccionado")
        Log.d("SharedViewModel", "selectedFormat: $selectedFormat")
        Log.d("SharedViewModel", "selectedFrequency: $selectedFrequency")
        guardarMedicamento()
    }

    private fun guardarMedicamento() {
        // Obtener una instancia de SharedPreferences
        val sharedPref = this.getSharedPreferences("MyPref", Context.MODE_PRIVATE)

        // Obtener el USER_ID de SharedPreferences
        val id = sharedPref.getString("USER_ID", null)

        // Obtener el medicamentoSeleccionado de SharedPreferences
        val medicamentoSeleccionado = sharedPref.getString("MEDICAMENTO_SELECCIONADO", null)

        // Obtener el selectedFormat de SharedPreferences
        val selectedFormat = sharedPref.getString("SELECTED_FORMAT", null)

        // Obtener el selectedFrequency de SharedPreferences
        val selectedFrequency = sharedPref.getString("SELECTED_FREQUENCY", null)

        // Cambiar el texto del TextView a "Guardando Medicamento"
        runOnUiThread {
            val textView = findViewById<TextView>(R.id.guardando_guardado)
            textView.text = "Guardando Medicamento"
        }

        // Guardar el medicamento
        CoroutineScope(Dispatchers.IO).launch {
            // Obtener las ID de medicamentoSeleccionado, selectedFormat y selectedFrequency
            val idMedicamentoResponse = RetrofitInstance.api.idMedicamento(medicamentosReq(medicamentoSeleccionado))
            val idFormatoResponse = RetrofitInstance.api.idformato(formatoReq(selectedFormat))
            val idFrecuenciaResponse = RetrofitInstance.api.idFrecuencia(frecuenciaReq(selectedFrequency))

            // Verificar que las respuestas son exitosas
            if (idMedicamentoResponse.isSuccessful && idFormatoResponse.isSuccessful && idFrecuenciaResponse.isSuccessful) {
                // Obtener las ID de las respuestas
                val idMedicamento = idMedicamentoResponse.body()?.id
                val idFormato = idFormatoResponse.body()?.id
                val idFrecuencia = idFrecuenciaResponse.body()?.id

                // Crear el recordatorio con las ID obtenidas
                val response = RetrofitInstance.api.createRecordatorio(
                    recordatorioReq(
                        id,
                        idMedicamento,
                        idFormato,
                        idFrecuencia
                    )
                )

                // Verificar que la respuesta es exitosa
                if (response.isSuccessful) {
                    // El recordatorio se creó exitosamente
                    Log.d("SharedViewModel", "Recordatorio creado exitosamente")

                    // Cambiar el texto del TextView a "Medicamento Guardado"
                    runOnUiThread {
                        val textView = findViewById<TextView>(R.id.guardando_guardado)
                        textView.text = "Medicamento Guardado"
                    }

                    // Hacer una pausa de 2 segundos
                    kotlinx.coroutines.delay(2000)

                    // Redirigir a la actividad Slidebar
                    val intent = Intent(this@Guardando_Medicamento, slidebar::class.java)
                    startActivity(intent)
                } else {
                    // Hubo un error al crear el recordatorio
                    Log.d("SharedViewModel", "Error al crear el recordatorio: ${response.errorBody()}")
                }
            } else {
                // Hubo un error al obtener las ID
                Log.d("SharedViewModel", "Error al obtener las ID: Medicamento: ${idMedicamentoResponse.errorBody()}, Formato: ${idFormatoResponse.errorBody()}, Frecuencia: ${idFrecuenciaResponse.errorBody()}")
            }
        }
    }
}