package com.example.myapplication

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import android.widget.Button
import android.widget.CalendarView
import android.widget.EditText
import android.widget.TimePicker
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import java.util.Calendar
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
                    Toast.makeText(this, "Seleccione nuevamente la fecha", Toast.LENGTH_LONG).show()
                    cargarRecordatorio()
                } else {
                    mostrarAlertDialog(
                        "Error",
                        "Debe completar el campo de Nombre del tratamiento."
                    ) {}
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
        val Hora = findViewById<TimePicker>(R.id.timePicker).hour
        val Minuto = findViewById<TimePicker>(R.id.timePicker).minute
        val FechaMillis = findViewById<CalendarView>(R.id.calendarView)
        FechaMillis.setOnDateChangeListener{ view, year, month, dayOfMonth ->
            val Fecha = "$dayOfMonth/${month + 1}/$year"
            val intent = Intent(this, Guardando_Hora_Medica::class.java)
            intent.putExtra("Tratamiento", Tratamiento)
            intent.putExtra("Fecha", Fecha)
            intent.putExtra("Hora", Hora.toString())
            intent.putExtra("Minuto", Minuto.toString())
            startActivity(intent)
        }
        /*
        val intent = Intent(this, Guardando_Hora_Medica::class.java)
        intent.putExtra("Tratamiento", Tratamiento)
        intent.putExtra("Fecha", Fecha)
        intent.putExtra("Hora", Hora.toString())
        intent.putExtra("Minuto", Minuto.toString())
        startActivity(intent)
        */
        // Guardar el medicamento
    }
}
