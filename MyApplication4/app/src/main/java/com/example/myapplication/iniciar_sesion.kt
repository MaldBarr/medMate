package com.example.myapplication

import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class iniciar_sesion : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_iniciar_sesion)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val botonIniciarSesion = findViewById<Button>(R.id.iniciarsesion)
        botonIniciarSesion.setOnClickListener {
            Log.d("AlertDialog", "Botón 'Iniciar Sesión' clickeado")
            mostrarAlertDialog()
        }
    }

    private fun mostrarAlertDialog() {
        Log.d("AlertDialog", "Mostrando AlertDialog")

        AlertDialog.Builder(this)
            .setTitle("Título")
            .setMessage("Detalle")
            .setPositiveButton("Aceptar") { dialog, id ->
                Log.d("AlertDialog", "Aceptar clickeado")
                // Acción al hacer clic en Aceptar
            }
            .setNegativeButton("Cancelar") { dialog, id ->
                Log.d("AlertDialog", "Cancelar clickeado")
                // Acción al hacer clic en Cancelar
            }
            .show()
    }
}
