package com.example.myapplication.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.example.myapplication.AddMed
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentHomeBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val homeViewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        setupBottomNavigation()
        return binding.root
    }

    fun setupBottomNavigation() {
        val bottomNavigationView = binding.bottomNavigationView2
        val navHostFragment = childFragmentManager.findFragmentById(R.id.nav_host_fragment2) as NavHostFragment
        val navController = navHostFragment.navController

        NavigationUI.setupWithNavController(bottomNavigationView, navController)

        bottomNavigationView.setOnItemSelectedListener { item ->
            if (item.itemId == R.id.page_add) {
                showAddOptionsDialog()
                true
            } else {
                NavigationUI.onNavDestinationSelected(item, navController)
                true
            }
        }
    }

    private fun showAddOptionsDialog() {
        val options = arrayOf("Agregar Medicamento", "Agregar Hora Médica")
        val builder = AlertDialog.Builder(requireContext())
        builder.setTitle("Selecciona una opción")
        builder.setItems(options) { dialog, which ->
            when (which) {
                0 -> navigateToAddMedicamento()
                1 -> {
                    // Navegar al fragmento de agregar hora médica (esto puede ser implementado más tarde)
                }
            }
        }
        builder.show()
    }

    private fun navigateToAddMedicamento() {
        val intent = Intent(activity, AddMed::class.java)
        startActivity(intent)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
