package com.example.myapplication.ui.medstep

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.myapplication.databinding.FragmentMedStep4Binding

class MedStep4Fragment : Fragment() {

    private var _binding: FragmentMedStep4Binding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMedStep4Binding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnSave.setOnClickListener {
            val dosis = binding.etDosis.text.toString()
            val cantidad = binding.etCantidad.text.toString()

            if (dosis.isNotEmpty() && cantidad.isNotEmpty()) {
                val sharedPref = requireActivity().getSharedPreferences("MyPref", Context.MODE_PRIVATE)
                val editor = sharedPref.edit()
                editor.putString("SELECTED_DOSIS", dosis)
                editor.putString("SELECTED_CANTIDAD", cantidad)
                editor.apply()

                Log.d("SharedViewModel", "Dosis: $dosis")
                Log.d("SharedViewModel", "Cantidad: $cantidad")

                Toast.makeText(context, "Dosis y cantidad guardadas", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(context, "Por favor, ingrese dosis y cantidad", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
