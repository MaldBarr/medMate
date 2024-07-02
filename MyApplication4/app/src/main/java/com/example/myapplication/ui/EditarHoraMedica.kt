package com.example.myapplication.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.CalendarView
import android.widget.EditText
import android.widget.TimePicker
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.R
import com.example.myapplication.data.RetrofitInstance
import com.example.myapplication.data.models.UpdateHoraMedicaReq
import com.example.myapplication.slidebar
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.Date

class EditarHoraMedica : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_reminder)

        val botonAgregarHora = findViewById<Button>(R.id.button40)
        botonAgregarHora.setOnClickListener {
            val Tratamiento = findViewById<EditText>(R.id.editTextText30).text.toString()

            if (Tratamiento.isNotEmpty()) {
                editarRecordatorio()
            } else {
                mostrarAlertDialog("Error", "Debe completar el campo de Nombre del tratamiento.") {}
            }
        }

        val botonCancelar = findViewById<Button>(R.id.button50)
        botonCancelar.setOnClickListener {
            finish() // Cierra y vuelve a la anterior
        }
    }

    private fun mostrarAlertDialog(title: String, message: String, onAccept: () -> Unit) {
        AlertDialog.Builder(this)
            .setTitle(title)
            .setMessage(message)
            .setPositiveButton("Aceptar") { dialog, id ->
                onAccept()
                // Acción al hacer clic en Aceptar en el AlertDialog
            }
            .show()
    }

    private fun editarRecordatorio(){
        val id = intent.getStringExtra("id")
        val Tratamiento = findViewById<EditText>(R.id.editTextText30).text.toString()
        val FechaMillis = findViewById<CalendarView>(R.id.calendarView0).date
        val Fecha = Date(FechaMillis)
        val Hora = findViewById<TimePicker>(R.id.timePicker0).hour
        val Minuto = findViewById<TimePicker>(R.id.timePicker0).minute

        // Guardar el medicamento
        CoroutineScope(Dispatchers.IO).launch {
            val response = RetrofitInstance.api.updateHoraMedica(
                UpdateHoraMedicaReq(
                    id,
                    Tratamiento,
                    Fecha,
                    Hora,
                    Minuto
                )
            )
            // Verificar que la respuesta es exitosa
            if (response.isSuccessful) {
                // El recordatorio se creó exitosamente
                Log.d("SharedViewModel", "Recordatorio editado exitosamente")
                // Redirigir a la actividad Slidebar
                val intent = Intent(this@EditarHoraMedica, slidebar::class.java)
                startActivity(intent)
            }
        }
    }
}