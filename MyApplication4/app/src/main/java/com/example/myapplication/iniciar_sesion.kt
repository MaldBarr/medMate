package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import com.example.myapplication.data.RetrofitServiceFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class iniciar_sesion : AppCompatActivity() {

    private val retrofitService = RetrofitServiceFactory.makeRetrofitService()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_iniciar_sesion)

        val botonIniciarSesion = findViewById<Button>(R.id.iniciarsesion)
        botonIniciarSesion.setOnClickListener {
            val correo = findViewById<EditText>(R.id.editTextTextEmailAddress2).text.toString()
            val contrasenia = findViewById<EditText>(R.id.editTextTextPassword2).text.toString()

            if (correo.isNotEmpty() && contrasenia.isNotEmpty()) {
                // Llamada a la API dentro de un coroutine
                GlobalScope.launch(Dispatchers.Main) {
                    try {
                        val result = retrofitService.iniciarSesion(correo, contrasenia)
                        // Manejar el resultado de la API
                        // Por ejemplo, navegar a otra actividad si la autenticación es exitosa
                        startActivity(Intent(this@iniciar_sesion, slidebar::class.java))
                    } catch (e: Exception) {
                        mostrarAlertDialog("Error", "Error de conexión. Inténtelo de nuevo más tarde.")
                    }
                }
            } else {
                mostrarAlertDialog("Error", "Debe completar todos los campos.")
            }
        }
    }

    private fun mostrarAlertDialog(title: String, message: String) {
        AlertDialog.Builder(this)
            .setTitle(title)
            .setMessage(message)
            .setPositiveButton("Aceptar") { dialog, id ->
                // Acción al hacer clic en Aceptar en el AlertDialog
            }
            .show()
    }
}
