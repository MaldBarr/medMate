package com.example.myapplication.ui.library

//import androidx.fragment.app.viewModels
import android.content.Intent
import android.os.Bundle
import android.provider.Telephony.Mms.Intents
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.Eliminar_HoraMedica
import com.example.myapplication.Eliminar_Medicamento
import com.example.myapplication.R
import com.example.myapplication.data.RetrofitInstance
import com.example.myapplication.data.models.DeleteHoraMedicaReq
import com.example.myapplication.data.models.HoraMedicaReq
import com.example.myapplication.data.models.MedicamentosbyIdReq
import com.example.myapplication.data.models.ObtenerHorasMedicasIDReq
import com.example.myapplication.data.models.ObtenerHorasMedicasIDRes
import com.example.myapplication.data.models.recordatorioUsuarioReq
import com.example.myapplication.data.models.recordatorioUsuarioRes
import com.example.myapplication.databinding.FragmentLibraryBinding
import com.example.myapplication.ui.EditarHoraMedica
import com.example.myapplication.ui.VerInfoHoraMedica
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
        //binding.tvTexto.text = "Biblioteca no disponible en la versión actual"
        // Inflate the layout for this fragment
        return binding.root
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
            val horasMedicas = response.body()
            if (response.isSuccessful && !horasMedicas.isNullOrEmpty()) {
                for (horaMedica in horasMedicas) {
                    myDataset.add(horaMedica)
                }
                println("Notifying adapter about dataset change") // Debugging line
                adapter.notifyDataSetChanged()
                binding.textView11.visibility = View.GONE // Ocultar el TextView si la respuesta es exitosa y hay horas medicas
            } else {
                binding.textView11.visibility = View.VISIBLE // Mostrar el TextView si la respuesta no es exitosa o si no hay horas medicas
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

            val editButton = holder.view.findViewById<Button>(R.id.editButton)
            editButton.setOnClickListener {
                val context = holder.view.context
                val intent = Intent(context, EditarHoraMedica::class.java)
                intent.putExtra("id",myDataset[position].id.toString())
                context.startActivity(intent)
            }

            val deleteButton = holder.view.findViewById<Button>(R.id.deleteButton)
            deleteButton.setOnClickListener {
                val context = holder.view.context
                val intent = Intent(context, Eliminar_HoraMedica::class.java)
                intent.putExtra("idHoraMedica", myDataset[position].id.toString())
                context.startActivity(intent)
            }

            val infoButton = holder.view.findViewById<ImageButton>(R.id.infoButtom)
            infoButton.setOnClickListener {
                val context = holder.view.context
                val intent = Intent(context, VerInfoHoraMedica::class.java)
                intent.putExtra("id",myDataset[position].id.toString())
                intent.putExtra("nombre",myDataset[position].nombre)
                intent.putExtra("fecha",myDataset[position].fecha.toString())
                intent.putExtra("hora",myDataset[position].id_hora.toString())
                intent.putExtra("minuto",myDataset[position].id_minuto.toString())
                context.startActivity(intent)
            }
        }

        override fun getItemCount() = myDataset.size
    }
}