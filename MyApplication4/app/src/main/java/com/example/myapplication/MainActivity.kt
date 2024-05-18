package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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
}