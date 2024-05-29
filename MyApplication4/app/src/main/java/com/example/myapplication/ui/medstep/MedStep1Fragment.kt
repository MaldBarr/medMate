package com.example.myapplication.ui.medstep

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
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
            // Aquí puedes guardar el medicamentoSeleccionado en la base de datos o en cualquier otro lugar necesario
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
