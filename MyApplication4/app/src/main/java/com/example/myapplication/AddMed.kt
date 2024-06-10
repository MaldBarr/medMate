package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.example.myapplication.databinding.ActivityAddMedBinding

class AddMed : AppCompatActivity() {

    private lateinit var binding: ActivityAddMedBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddMedBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val viewPager: ViewPager2 = binding.viewPager
        val progressBar = binding.progressBar
        val btnCancelar = binding.btnCancelar
        val btnAtras = binding.btnAtras
        val btnSiguiente = binding.btnSiguiente

        // Set up the ViewPager with an adapter
        val adapter = AddMedPagerAdapter(this)
        viewPager.adapter = adapter

        // Set initial progress
        progressBar.max = 4 // Cambiar a 4
        progressBar.progress = 1

        // Botón Cancelar
        btnCancelar.setOnClickListener {
            finish() // Termina la actividad
        }

        // Botón Siguiente
        btnSiguiente.setOnClickListener {
            val currentItem = viewPager.currentItem
            if (currentItem < adapter.itemCount - 1) {
                viewPager.currentItem = currentItem + 1
                progressBar.progress = currentItem + 2
            } else {
                finish()
            }
        }

        // Botón Atrás
        btnAtras.setOnClickListener {
            val currentItem = viewPager.currentItem
            if (currentItem > 0) {
                viewPager.currentItem = currentItem - 1
                progressBar.progress = currentItem
            }
        }

        // Listener para actualizar visibilidad de botones
        viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                btnAtras.visibility = if (position == 0) View.GONE else View.VISIBLE
                btnSiguiente.text = if (position == adapter.itemCount - 1) "Finalizar" else "Siguiente"
            }
        })
    }
}
