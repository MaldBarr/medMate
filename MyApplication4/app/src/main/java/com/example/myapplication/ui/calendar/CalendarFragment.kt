package com.example.myapplication.ui.calendar

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.data.RetrofitInstance
import com.example.myapplication.data.models.MedicamentosbyIdReq
import com.example.myapplication.data.models.recordatorioUsuarioReq
import com.example.myapplication.databinding.FragmentCalendarBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CalendarFragment : Fragment(R.layout.fragment_calendar) {

    private var _binding: FragmentCalendarBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCalendarBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setupRecyclerView() {
        val myDataset = mutableListOf<String>()
        val adapter = MyAdapter(myDataset)
        binding.listadoMedicamentos.layoutManager = LinearLayoutManager(context)
        binding.listadoMedicamentos.adapter = adapter

        CoroutineScope(Dispatchers.Main).launch {
            val sharedPref = requireActivity().getSharedPreferences("MyPref", 0)
            val id = sharedPref.getString("USER_ID", null)
            val response = RetrofitInstance.api.getRecordatoriosByUserId(recordatorioUsuarioReq(id))
            if (response.isSuccessful) {
                val recordatorios = response.body()
                if (recordatorios != null && recordatorios.isNotEmpty()) {
                    for (recordatorio in recordatorios) {
                        val idMedicamento = recordatorio.id_medicamento
                        val responseMedicamento = RetrofitInstance.api.getNombreMedicamentoById(
                            MedicamentosbyIdReq(idMedicamento)
                        )
                        if (responseMedicamento.isSuccessful) {
                            val medicamentoNombre = responseMedicamento.body()?.nombre
                            if (medicamentoNombre != null) {
                                println("Adding $medicamentoNombre to dataset") // Debugging line
                                myDataset.add(medicamentoNombre)
                            }
                        }
                    }
                    println("Notifying adapter about dataset change") // Debugging line
                    adapter.notifyDataSetChanged()
                }
            }
        }
    }

    class MyAdapter(private val myDataset: List<String>) :
        RecyclerView.Adapter<MyAdapter.MyViewHolder>() {

        class MyViewHolder(val view: View) : RecyclerView.ViewHolder(view)

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyAdapter.MyViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item, parent, false)
            return MyViewHolder(view)
        }

        override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
            val textView = holder.view.findViewById<TextView>(R.id.nameTextView) // Asegúrate de que 'textView' es el id correcto en tu item.xml
            textView.text = myDataset[position]
        }

        override fun getItemCount() = myDataset.size
    }
}
