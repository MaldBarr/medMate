package com.example.myapplication.ui.medstep

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentMedStep2Binding

class MedStep2Fragment : Fragment() {

    private var _binding: FragmentMedStep2Binding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMedStep2Binding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val formats = resources.getStringArray(R.array.formatos_array)
        for (format in formats) {
            val radioButton = RadioButton(context).apply {
                text = format
                id = View.generateViewId()
            }
            binding.radioGroup.addView(radioButton)
        }

        binding.radioGroup.setOnCheckedChangeListener { group, checkedId ->
            val selectedRadioButton = group.findViewById<RadioButton>(checkedId)
            val selectedFormat = selectedRadioButton?.text?.toString()
            // Muestra un mensaje de confirmación para probar la selección
            Toast.makeText(context, "Seleccionaste: $selectedFormat", Toast.LENGTH_SHORT).show()
            // Guardar el selectedFormat en SharedPreferences
            val sharedPref = requireActivity().getSharedPreferences("MyPref", Context.MODE_PRIVATE)
            val editor = sharedPref.edit()
            editor.putString("SELECTED_FORMAT", selectedFormat)
            editor.apply()
        }

    }



    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}