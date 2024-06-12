package com.example.myapplication.ui.library

//import androidx.fragment.app.viewModels
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.Eliminar_Medicamento
import com.example.myapplication.R
import com.example.myapplication.data.RetrofitInstance
import com.example.myapplication.data.models.HoraMedicaReq
import com.example.myapplication.data.models.MedicamentosbyIdReq
import com.example.myapplication.data.models.ObtenerHorasMedicasIDReq
import com.example.myapplication.data.models.ObtenerHorasMedicasIDRes
import com.example.myapplication.data.models.recordatorioUsuarioReq
import com.example.myapplication.data.models.recordatorioUsuarioRes
import com.example.myapplication.databinding.FragmentLibraryBinding
import com.example.myapplication.ui.editarMedicamento.EditarMedicamentoPt1
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LibraryFragment : Fragment(R.layout.fragment_library) {

    private lateinit var binding: FragmentLibraryBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = FragmentLibraryBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding.tvTexto.text = "Fragment Library"
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_library, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
    }


    private fun setupRecyclerView() {
        val myDataset = mutableListOf<ObtenerHorasMedicasIDRes>()
        val adapter = MyAdapter(myDataset)
        binding.listadoHorasMedicas.layoutManager = LinearLayoutManager(context)
        binding.listadoHorasMedicas.adapter = adapter

        CoroutineScope(Dispatchers.Main).launch {
            val sharedPref = requireActivity().getSharedPreferences("MyPref", 0)
            val id_usuario = sharedPref.getString("USER_ID", null)
            val response = RetrofitInstance.api.getHorasMedicasByID(ObtenerHorasMedicasIDReq(id_usuario))
            if (response.isSuccessful) {
                val horasMedicas = response.body()
                if (horasMedicas != null && horasMedicas.isNotEmpty()) {
                    for (horaMedica in horasMedicas) {
                        myDataset.add(horaMedica)
                    }
                    println("Notifying adapter about dataset change") // Debugging line
                    adapter.notifyDataSetChanged()
                }
            }
        }
    }

    class MyAdapter(private val myDataset: List<ObtenerHorasMedicasIDRes>) :
        RecyclerView.Adapter<MyAdapter.MyViewHolder>() {

        class MyViewHolder(val view: View) : RecyclerView.ViewHolder(view)

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyAdapter.MyViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item, parent, false)
            return MyViewHolder(view)
        }

        override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
            val textView = holder.view.findViewById<TextView>(R.id.nameTextView)
            textView.text = myDataset[position].nombre

            /*val editButton = holder.view.findViewById<Button>(R.id.editButton)
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
            }*/
        }

        override fun getItemCount() = myDataset.size
    }
}