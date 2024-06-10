package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.example.myapplication.databinding.ActivityBottomNavBinding

class bottomNav : AppCompatActivity() {

    private lateinit var binding: ActivityBottomNavBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBottomNavBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupNavegacion()
    }

    private fun setupNavegacion() {
        val bottomNavigationView = binding.bottomNavigationView
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController

        // Configuración de NavigationUI para manejar la navegación
        NavigationUI.setupWithNavController(bottomNavigationView, navController)

        // Configuración del listener para interceptar la selección del elemento page_add
        bottomNavigationView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.page_add -> {
                    Log.d("bottomNav", "page_add selected")
                    showAddOptionsDialog()
                    true
                }
                else -> {
                    NavigationUI.onNavDestinationSelected(item, navController)
                    false
                }
            }
        }
    }

    private fun showAddOptionsDialog() {
        Log.d("bottomNav", "showAddOptionsDialog called")
        val options = arrayOf("Agregar Medicamento", "Agregar Hora Médica")
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Selecciona una opción")
        builder.setItems(options) { dialog, which ->
            when (which) {
                0 -> {
                    Log.d("bottomNav", "Navigating to AddMed")
                    navigateToAddMedicamento()
                }
                1 -> {
                    Log.d("bottomNav", "Navigating to HorasMedicas")
                    navigateToHorasMedicas()
                }
                else -> {
                    Log.d("bottomNav", "Invalid option selected")
                }
            }
        }
        builder.show()
    }



    private fun navigateToAddMedicamento() {
        Log.d("bottomNav", "Starting AddMed Activity")
        val intent = Intent(this, AddMed::class.java)
        startActivity(intent)
    }


    private fun navigateToHorasMedicas() {
        Log.d("bottomNav", "Starting HorasMedicas Activity")
        val intent = Intent(this, HorasMedicas::class.java)
        startActivity(intent)
    }
}
