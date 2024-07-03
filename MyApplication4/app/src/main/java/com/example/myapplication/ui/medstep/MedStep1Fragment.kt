package com.example.myapplication.ui.medstep

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import com.example.myapplication.R
import com.example.myapplication.data.RetrofitInstance
import com.example.myapplication.data.interfaz.ApiService
import com.example.myapplication.data.models.MedicamentosTodosRes
import com.example.myapplication.databinding.FragmentMedStep1Binding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MedStep1Fragment : Fragment() {

    private var _binding: FragmentMedStep1Binding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMedStep1Binding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // Crea una instancia de ApiService
        var medicamentosArray = emptyList<MedicamentosTodosRes>()

        // Llama a obtenerTodosMedicamentos en una Coroutine
        // Llama a obtenerTodosMedicamentos en una Coroutine
        CoroutineScope(Dispatchers.IO).launch {
            val service = RetrofitInstance.api.obtenerTodosMedicamentos()
            val response = service
            if (response.isSuccessful) {
                medicamentosArray = response.body() ?: emptyList<MedicamentosTodosRes>()

                withContext(Dispatchers.Main) {
                    val medicamentosNombres = medicamentosArray.map { it.medicamento ?: "" }
                    val adapter = CustomArrayAdapter(requireContext(), android.R.layout.simple_dropdown_item_1line, medicamentosNombres)
                    binding.autoCompleteMedicamento.setAdapter(adapter)

                    // Configurar el threshold y mostrar el dropdown
                    binding.autoCompleteMedicamento.threshold = 0 // Mostrará todos los medicamentos desde el inicio
                    binding.autoCompleteMedicamento.showDropDown()
                }
            } else {
                // Manejar error aquí
            }
        }

        binding.autoCompleteMedicamento.setOnItemClickListener { parent, _, position, _ ->
            val medicamentoSeleccionado = parent.getItemAtPosition(position) as String
            if (medicamentoSeleccionado.isEmpty()) {
                mostrarAlertDialog("Error", "Debes seleccionar un medicamento") {}
            } else {
                // Guardar el medicamentoSeleccionado en SharedPreferences
                val sharedPref = requireActivity().getSharedPreferences("MyPref", Context.MODE_PRIVATE)
                val editor = sharedPref.edit()
                editor.putString("MEDICAMENTO_SELECCIONADO", medicamentoSeleccionado)
                editor.apply()
            }
        }

        // Mostrar todos los medicamentos inicialmente
        binding.autoCompleteMedicamento.post {
            binding.autoCompleteMedicamento.showDropDown()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun mostrarAlertDialog(title: String, message: String, onAccept: () -> Unit) {
        AlertDialog.Builder(requireContext())
            .setTitle(title)
            .setMessage(message)
            .setPositiveButton("Aceptar") { dialog, id ->
                onAccept()
                // Acción al hacer clic en Aceptar en el AlertDialog
            }
            .show()
    }
}