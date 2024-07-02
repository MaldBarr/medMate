package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.myapplication.data.RetrofitInstance
import com.example.myapplication.data.models.DeleteHoraMedicaReq
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class Eliminar_HoraMedica : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_eliminar_hora_medica)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val texto = findViewById<TextView>(R.id.textView7)
        texto.text = "Eliminando hora médica..."
        val idHoraMedica = intent.getStringExtra("idHoraMedica")?.toInt()
        Log.d("Eliminar_HoraMedica", "idHoraMedica: $idHoraMedica")
        CoroutineScope(Dispatchers.Main).launch {
            val request = DeleteHoraMedicaReq(idHoraMedica)
            val response = RetrofitInstance.api.deleteHoraMedica(request)

            if (response.isSuccessful) {
                runOnUiThread {
                    val textView = findViewById<TextView>(R.id.textView7)
                    textView.text = "Hora Medica Eliminada"
                }

                kotlinx.coroutines.delay(2000)
                val deleteResponse = response.body()
                println("DeleteResponse: $deleteResponse")
                val intent = Intent(this@Eliminar_HoraMedica, slidebar::class.java)
                startActivity(intent)
            }
        }
    }
}