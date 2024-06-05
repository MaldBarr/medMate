package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.myapplication.data.RetrofitInstance
import com.example.myapplication.data.models.LoginRequest
import com.example.myapplication.data.models.RegisterRequest
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class Register : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_register)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val boton_registro = findViewById<Button>(R.id.registerBtn)
        boton_registro.setOnClickListener {
            val email = findViewById<EditText>(R.id.editTextTextEmailAddress).text.toString()
            val name = findViewById<EditText>(R.id.editTextText).text.toString()
            val contrasenia = findViewById<EditText>(R.id.editTextTextPassword).text.toString()
            if (email.isEmpty() || name.isEmpty() || contrasenia.isEmpty()) {
                mostrarAlertDialog("Error", "Por favor completa todos los campos.")
            }

            val terminos_condiciones = findViewById<CheckBox>(R.id.checkBox)
            if (!terminos_condiciones.isChecked) {
                mostrarAlertDialog("Error", "Debes aceptar los términos y condiciones para continuar.")
            }

            // Crear el objeto RegisterRequest para enviar al servidor
            val registerRequest = RegisterRequest(name, email, contrasenia)

            // Llamar a la función para registrar
            registrar(registerRequest)
        }
    }

    private fun registrar(registerRequest: RegisterRequest) {
        CoroutineScope(Dispatchers.IO).launch {
            val response = RetrofitInstance.api.register(registerRequest)

            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    // Inicio de sesión exitoso
                    val loginResponse = response.body()
                    Log.d("LoginResponse", "Response: $loginResponse") // Loguear el loginResponse
                    val intent = Intent(this@Register, MainActivity::class.java)
                    startActivity(intent)
                } else {
                    // El servidor retornó un error
                    // Puedes obtener más detalles con response.errorBody() y response.code()
                    mostrarAlertDialog("Error", "Correo o Contraseña son Incorrectos.")
                }
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
