package com.example.myapplication

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        notificationChannel()
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val boton_iniciar_sesion = findViewById<Button>(R.id.Boton_ini_ini)
        boton_iniciar_sesion.setOnClickListener {
            startActivity(Intent(this, iniciar_sesion::class.java))
        }
        val boton_registro = findViewById<Button>(R.id.Boton_ini_regis)
        boton_registro.setOnClickListener {
            startActivity(Intent(this, Register::class.java))
        }
    }
    private fun notificationChannel(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name : CharSequence = "Notification Channel"
            val importance = NotificationManager.IMPORTANCE_HIGH
            val channel = NotificationChannel("channelId",name,importance)
            val notificationManager = getSystemService(
                NotificationManager::class.java
            )
        }
    }
}