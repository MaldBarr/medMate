package com.example.myapplication.ui.medstep

import SharedViewModel
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentMedStep1Binding
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

        val medicamentosArray = resources.getStringArray(R.array.medicamentos)
        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_dropdown_item_1line, medicamentosArray)
        binding.autoCompleteMedicamento.setAdapter(adapter)

        binding.autoCompleteMedicamento.setOnItemClickListener { parent, _, position, _ ->
            val medicamentoSeleccionado = parent.getItemAtPosition(position) as String
            if(medicamentoSeleccionado.isEmpty())
            {
                mostrarAlertDialog("Error", "Debes seleccionar un medicamento", {})
            }
            else{
                val viewModel: SharedViewModel by activityViewModels()
                viewModel.setMedicamento(medicamentoSeleccionado)
            }
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun mostrarAlertDialog(title: String, message: String, onAccept: () -> Unit)
    {
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
