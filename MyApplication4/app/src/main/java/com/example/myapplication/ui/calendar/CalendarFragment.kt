package com.example.myapplication.ui.calendar

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.Eliminar_Medicamento
import com.example.myapplication.InfoMedicamento
import com.example.myapplication.R
import com.example.myapplication.data.RetrofitInstance
import com.example.myapplication.data.models.MedicamentosbyIdReq
import com.example.myapplication.data.models.recordatorioUsuarioReq
import com.example.myapplication.data.models.recordatorioUsuarioRes
import com.example.myapplication.databinding.FragmentCalendarBinding
import com.example.myapplication.ui.editarMedicamento.EditarMedicamentoPt1
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
        val myDataset = mutableListOf<recordatorioUsuarioRes>()
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
                                recordatorio.id_medicamento = medicamentoNombre
                                myDataset.add(recordatorio)
                            }
                        }
                    }
                    println("Notifying adapter about dataset change") // Debugging line
                    adapter.notifyDataSetChanged()
                }
            }
        }
    }

    class MyAdapter(private val myDataset: List<recordatorioUsuarioRes>) :
        RecyclerView.Adapter<MyAdapter.MyViewHolder>() {

        class MyViewHolder(val view: View) : RecyclerView.ViewHolder(view)

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyAdapter.MyViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item, parent, false)
            return MyViewHolder(view)
        }

        override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
            val textView = holder.view.findViewById<TextView>(R.id.nameTextView)
            textView.text = myDataset[position].id_medicamento

            val editButton = holder.view.findViewById<Button>(R.id.editButton)
            editButton.setOnClickListener {
                val context = holder.view.context
                val intent = Intent(context, EditarMedicamentoPt1::class.java)
                intent.putExtra("id_recordatorio", myDataset[position].id.toString())
                intent.putExtra("id_medicamento", myDataset[position].id_medicamento)
                intent.putExtra("id_formato", myDataset[position].id_formato)
                intent.putExtra("id_frecuencia", myDataset[position].id_frecuencia)
                context.startActivity(intent)
            }
            val deleteButton = holder.view.findViewById<Button>(R.id.deleteButton)
            deleteButton.setOnClickListener {
                val context = holder.view.context
                val intent = Intent(context, Eliminar_Medicamento::class.java)
                intent.putExtra("id_recordatorio", myDataset[position].id.toString())
                context.startActivity(intent)
            }
            val infoButton = holder.view.findViewById<ImageButton>(R.id.infoButtom)
            infoButton.setOnClickListener{
                    val context = holder.view.context
                    val intent = Intent(context, InfoMedicamento::class.java)
                    intent.putExtra("id_medicamento", myDataset[position].id_medicamento)
                    intent.putExtra("id_formato", myDataset[position].id_formato)
                    intent.putExtra("id_frecuencia", myDataset[position].id_frecuencia)
                    context.startActivity(intent)
                }
        }

        override fun getItemCount() = myDataset.size
    }
}
