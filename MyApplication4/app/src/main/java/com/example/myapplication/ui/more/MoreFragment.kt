package com.example.myapplication.ui.more

//import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentMoreBinding

class MoreFragment : Fragment(R.layout.fragment_more) {

    private lateinit var binding: FragmentMoreBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = FragmentMoreBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding.tvTexto.text = "Fragment More"

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_more, container, false)
    }
}