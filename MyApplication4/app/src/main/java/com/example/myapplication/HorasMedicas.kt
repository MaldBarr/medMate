package com.example.myapplication

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.Button
import android.widget.CalendarView
import android.widget.EditText
import android.widget.TimePicker
import androidx.appcompat.app.AlertDialog
import java.util.Date

class HorasMedicas : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_reminder)

        val botonAgregarHora = findViewById<Button>(R.id.button4)
        botonAgregarHora.setOnClickListener {
            val Tratamiento = findViewById<EditText>(R.id.editTextText3).text.toString()
            val FechaMillis = findViewById<CalendarView>(R.id.calendarView).date
            val Fecha = Date(FechaMillis)
            val Hora = findViewById<TimePicker>(R.id.timePicker).hour

            if (Tratamiento.isNotEmpty()) {
                // Agregar la hora médica a la base de datos
                TODO()
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
}
