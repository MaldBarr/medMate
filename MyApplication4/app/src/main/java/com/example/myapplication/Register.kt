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
            Log.d("Register", "Email:"+email)
            val name = findViewById<EditText>(R.id.editTextText).text.toString()
            val contrasenia = findViewById<EditText>(R.id.editTextTextPassword).text.toString()
            val terminos_condiciones = findViewById<CheckBox>(R.id.checkBox)
            if (email.isEmpty() || name.isEmpty() || contrasenia.isEmpty()) {
                mostrarAlertDialog("Error", "Por favor completa todos los campos.")
                {

                }
            } else if (!isEmailValid(email)) {
                mostrarAlertDialog("Error", "Por favor ingresa un correo electrónico válido.")
                {

                }
            } else if (contrasenia.length < 6) {
                mostrarAlertDialog("Error", "La contraseña debe tener al menos 6 caracteres.")
                {

                }
            } else if (!terminos_condiciones.isChecked) {
                mostrarAlertDialog("Error", "Debes aceptar los términos y condiciones para continuar.")
                {

                }
            } else {
                // Crear el objeto RegisterRequest para enviar al servidor
                val registerRequest = RegisterRequest(name, email, contrasenia)

                // Llamar a la función para registrar
                registrar(registerRequest)
            }
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
                    mostrarAlertDialog("Gracias Por Registrarte","Por favor inicia sesion con tu correo:"+registerRequest.email+" y contraseña: "+registerRequest.contrasenia)
                    {
                        startActivity(intent)
                    }
                } else {
                    // El servidor retornó un error
                    // Puedes obtener más detalles con response.errorBody() y response.code()
                    mostrarAlertDialog("Error", "Correo o Contraseña son Incorrectos.")
                    {
                        // Acción al hacer clic en Aceptar en el AlertDialog
                    }
                }
            }
        }
    }

    private fun isEmailValid(email: String): Boolean {
        val emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"
        return email.matches(emailPattern.toRegex())
    }

    private fun mostrarAlertDialog(title: String, message: String, onAccept: () -> Unit) {
        AlertDialog.Builder(this)
            .setTitle(title)
            .setMessage(message)
            .setPositiveButton("Aceptar") { dialog, id ->
                // Acción al hacer clic en Aceptar en el AlertDialog
                onAccept()
            }
            .show()
    }

}
