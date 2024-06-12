package com.example.myapplication

import android.content.Context
import android.content.Intent
import android.icu.text.DateFormat.HourCycle
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import android.widget.Button
import android.widget.CalendarView
import android.widget.EditText
import android.widget.TextView
import android.widget.TimePicker
import androidx.appcompat.app.AlertDialog
import com.example.myapplication.data.RetrofitInstance
import com.example.myapplication.data.models.HoraMedicaReq
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response
import java.util.Date

class HorasMedicas : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_reminder)

        val botonAgregarHora = findViewById<Button>(R.id.button4)
        botonAgregarHora.setOnClickListener {
            val Tratamiento = findViewById<EditText>(R.id.editTextText3).text.toString()

            if (Tratamiento.isNotEmpty()) {
                // Agregar la hora médica a la base de datos
                cargarRecordatorio()
            } else {
                mostrarAlertDialog("Error", "Debe completar el campo de Nombre del tratamiento.") {}
            }
        }

        val botonCancelar = findViewById<Button>(R.id.button5)
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

    private fun cargarRecordatorio() {
        // Cargar el recordatorio a la base de datos
        // Obtener una instancia de SharedPreferences
        val sharedPref = this.getSharedPreferences("MyPref", Context.MODE_PRIVATE)
        // Obtener el USER_ID de SharedPreferences
        val id_usuario = sharedPref.getString("USER_ID", null)

        val Tratamiento = findViewById<EditText>(R.id.editTextText3).text.toString()
        val FechaMillis = findViewById<CalendarView>(R.id.calendarView).date
        val Fecha = Date(FechaMillis)
        val Hora = findViewById<TimePicker>(R.id.timePicker).hour
        val Minuto = findViewById<TimePicker>(R.id.timePicker).minute

        // Guardar el medicamento
        CoroutineScope(Dispatchers.IO).launch{
            val response = RetrofitInstance.api.createHoraMedica(
                    HoraMedicaReq(
                        id_usuario,
                        Tratamiento,
                        Fecha,
                        Hora,
                        Minuto
                    )
            )
            // Verificar que la respuesta es exitosa
            if (response.isSuccessful) {
                // El recordatorio se creó exitosamente
                Log.d("SharedViewModel", "Recordatorio creado exitosamente")

                // Redirigir a la actividad Slidebar
                val intent = Intent(this@HorasMedicas, slidebar::class.java)
                startActivity(intent)
            }
        }
    }
}
