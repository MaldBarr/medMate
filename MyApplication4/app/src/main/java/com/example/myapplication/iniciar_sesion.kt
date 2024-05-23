package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.findViewTreeViewModelStoreOwner

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
            val correo = findViewById<EditText>(R.id.editTextTextEmailAddress2)
            val contrasenia = findViewById<EditText>(R.id.editTextTextPassword2)
            if (correo.text.isEmpty() || contrasenia.text.isEmpty()) {
                mostrarAlertDialog()
            }
            else
            {
                startActivity(Intent(this, sidebar::class.java))
            }
            Log.d("AlertDialog", "Botón 'Iniciar Sesión' clickeado")
        }
    }

    private fun mostrarAlertDialog() {
        Log.d("AlertDialog", "Mostrando AlertDialog")

        AlertDialog.Builder(this)
            .setTitle("Error")
            .setMessage("Correo o Contraseña son incorrectos")
            .setPositiveButton("Aceptar") { dialog, id ->
                Log.d("AlertDialog", "Aceptar clickeado")
                // Acción al hacer clic en Aceptar
            }
            .show()
    }
}
