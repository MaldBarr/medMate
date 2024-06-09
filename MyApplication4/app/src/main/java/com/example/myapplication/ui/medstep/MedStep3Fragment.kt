package com.example.myapplication.ui.medstep

import SharedViewModel
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentMedStep3Binding

class MedStep3Fragment : Fragment() {

    private var _binding: FragmentMedStep3Binding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMedStep3Binding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val frequencies = resources.getStringArray(R.array.frecuencia)
        for (frequency in frequencies) {
            val radioButton = RadioButton(context).apply {
                text = frequency
                id = View.generateViewId()
            }
            binding.radioGroup.addView(radioButton)
        }

        binding.radioGroup.setOnCheckedChangeListener { group, checkedId ->
            val selectedRadioButton = group.findViewById<RadioButton>(checkedId)
            val selectedFrequency = selectedRadioButton?.text?.toString()
            // Muestra un mensaje de confirmación para probar la selección
            Toast.makeText(context, "Seleccionaste: $selectedFrequency", Toast.LENGTH_SHORT).show()
            // Aquí es donde identificamos el dato para guardarlo en la base de datos
            saveSelectedFrequency(selectedFrequency)
        }
    }

    private fun saveSelectedFrequency(frequency: String?) {
        if(frequency != null && frequency.isNotEmpty()) {
            val viewModel: SharedViewModel by activityViewModels()
            viewModel.setFrecuencia(frequency)
        } else {
            // Handle the case where frequency is null or empty
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
