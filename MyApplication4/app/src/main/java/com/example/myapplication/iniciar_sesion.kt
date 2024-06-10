package com.example.myapplication

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.data.RetrofitInstance
import com.example.myapplication.data.models.LoginRequest
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class iniciar_sesion : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_iniciar_sesion)

        val botonIniciarSesion = findViewById<Button>(R.id.iniciarsesion)
        botonIniciarSesion.setOnClickListener {
            val correo = findViewById<EditText>(R.id.editTextTextEmailAddress2).text.toString()
            val contrasenia = findViewById<EditText>(R.id.editTextTextPassword2).text.toString()

            if (correo.isNotEmpty() && contrasenia.isNotEmpty()) {
                val loginRequest = LoginRequest(correo, contrasenia)
                iniciarSesion(loginRequest)
            } else {
                mostrarAlertDialog("Error", "Debe completar todos los campos.")
                {

                }
            }
        }
    }

    private fun iniciarSesion(loginRequest: LoginRequest) {
        CoroutineScope(Dispatchers.IO).launch {
            val response = RetrofitInstance.api.login(loginRequest)

            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    // Inicio de sesión exitoso
                    val loginResponse = response.body()
                    Log.d("LoginResponse", "Response: $loginResponse") // Loguear el loginResponse
                    val name = loginResponse?.name
                    val email = loginResponse?.email
                    val id = loginResponse?.id
                    Log.d("LoginResponse", "id: $id") // Loguear el name

                    // Pasar los valores a la siguiente actividad
                    val intent = Intent(this@iniciar_sesion, slidebar::class.java)
                    intent.putExtra("USER_NAME", name)
                    intent.putExtra("USER_EMAIL", email)

                    val sharedPref = getSharedPreferences("MyPref", Context.MODE_PRIVATE)
                    val editor = sharedPref.edit()
                    editor.putString("USER_ID", id.toString())
                    editor.apply()

                    mostrarAlertDialog("Inicios de sesion correcta", "Bienvenido "+name.toString())
                    {
                        startActivity(intent)
                    }
                } else {
                    // El servidor retornó un error
                    // Puedes obtener más detalles con response.errorBody() y response.code()
                    mostrarAlertDialog("Error", "Correo o Contraseña son Incorrectos.")
                    {

                    }
                }
            }
        }
    }



    private fun mostrarAlertDialog(title: String, message: String, onAccept: () -> Unit)
    {
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
