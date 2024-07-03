// HorasMedicas.kt
package com.example.myapplication

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.CalendarView
import android.widget.EditText
import android.widget.TimePicker
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import java.util.Calendar

class HorasMedicas : AppCompatActivity() {

    private var selectedDate: Calendar? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_reminder)

        val calendarView = findViewById<CalendarView>(R.id.calendarView)
        calendarView.minDate = System.currentTimeMillis() - 1000 // Set minimum date to today

        calendarView.setOnDateChangeListener { _, year, month, dayOfMonth ->
            selectedDate = Calendar.getInstance().apply {
                set(year, month, dayOfMonth)
            }
        }

        val botonAgregarHora = findViewById<Button>(R.id.button4)
        botonAgregarHora.setOnClickListener {
            val tratamiento = findViewById<EditText>(R.id.editTextText3).text.toString()
            val timePicker = findViewById<TimePicker>(R.id.timePicker)

            if (tratamiento.isNotEmpty() && selectedDate != null) {
                selectedDate?.apply {
                    set(Calendar.HOUR_OF_DAY, timePicker.hour)
                    set(Calendar.MINUTE, timePicker.minute)
                    set(Calendar.SECOND, 0)
                }

                val now = Calendar.getInstance()
                if (selectedDate?.before(now) == true) {
                    Toast.makeText(this, "Seleccione una fecha y hora futura", Toast.LENGTH_LONG).show()
                } else {
                    guardarHoraMedica(tratamiento, selectedDate!!)
                }
            } else {
                mostrarAlertDialog("Error", "Debe completar todos los campos.")
            }
        }

        val botonCancelar = findViewById<Button>(R.id.button5)
        botonCancelar.setOnClickListener {
            finish() // Cierra y vuelve a la anterior
        }
    }

    private fun mostrarAlertDialog(title: String, message: String) {
        AlertDialog.Builder(this)
            .setTitle(title)
            .setMessage(message)
            .setPositiveButton("Aceptar", null)
            .show()
    }

    private fun guardarHoraMedica(tratamiento: String, calendar: Calendar) {
        val intent = Intent(this, Guardando_Hora_Medica::class.java).apply {
            putExtra("Tratamiento", tratamiento)
            putExtra("Fecha", calendar.timeInMillis)
        }
        startActivity(intent)
    }
}
