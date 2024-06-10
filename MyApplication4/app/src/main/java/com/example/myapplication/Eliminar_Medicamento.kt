package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.myapplication.data.RetrofitInstance
import com.example.myapplication.data.models.recordatorioDeleteReq
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class Eliminar_Medicamento<TextView : View?> : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_eliminar_medicamento)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Recibir el dato pasado por Intent desde CalendarFragment
        val idRecordatorio = intent.getStringExtra("id_recordatorio")?.toInt()
        // Ahora puedes usar idRecordatorio en tu actividad
        Log.d("Eliminar_Medicamento", "idRecordatorio: $idRecordatorio")
        if (idRecordatorio != null) {

            eliminarRecordatorio(idRecordatorio)
        } else {
            // Handle the case where idRecordatorio is null
        }
    }


    private fun eliminarRecordatorio(idRecordatorio: Int) {
        val textView7 = findViewById<android.widget.TextView>(R.id.textView7)
        textView7?.text = "Eliminando recordatorio"

        CoroutineScope(Dispatchers.IO).launch {
            // Crear una instancia de recordatorioDeleteReq
            val request = recordatorioDeleteReq(idRecordatorio)

            // Hacer la llamada a la API
            val response = RetrofitInstance.api.recordatorioDeleteReq(request)

            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    // La solicitud fue exitosa
                    val deleteResponse = response.body()
                    Log.d("DeleteResponse", "Response: $deleteResponse") // Loguear el deleteResponse

                    // Cambiar el texto de textView7 a "Recordatorio Eliminado"
                    textView7.text = "Recordatorio Eliminado"

                    // Iniciar la actividad slidebar después de un retraso de 2 segundos
                    Handler(Looper.getMainLooper()).postDelayed({
                        val intent = Intent(this@Eliminar_Medicamento, slidebar::class.java)
                        startActivity(intent)
                    }, 2000) // Retraso de 2 segundos

                } else {
                    // El servidor retornó un error
                    // Puedes obtener más detalles con response.errorBody() y response.code()
                    mostrarAlertDialog("Error", "Hubo un error en la solicitud.")
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