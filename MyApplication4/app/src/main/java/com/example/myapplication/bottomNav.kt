package com.example.myapplication

import android.os.Bundle
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
            if (item.itemId == R.id.page_add) {
                showAddOptionsDialog()
                true
            } else {
                NavigationUI.onNavDestinationSelected(item, navController)
            }
        }
    }

    private fun showAddOptionsDialog() {
        val options = arrayOf("Agregar Medicamento", "Agregar Hora Médica")
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Selecciona una opción")
        builder.setItems(options) { dialog, which ->
            when (which) {
                0 -> {
                    // Navegar al fragmento de agregar medicamento
                    navigateToAddMedicamento()
                }
                1 -> {
                    // Navegar al fragmento de agregar hora médica (esto puede ser implementado más tarde)
                    // navigateToAddHoraMedica()
                }
            }
        }
        builder.show()
    }

    private fun navigateToAddMedicamento() {
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController
        navController.navigate(R.id.add_medicamento_fragment)
    }
}
