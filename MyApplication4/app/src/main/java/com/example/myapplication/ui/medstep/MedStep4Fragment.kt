// MedStep4Fragment.kt
package com.example.myapplication.ui.medstep

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import com.example.myapplication.R
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TimePicker
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.myapplication.Guardando_Medicamento
import java.util.*

class MedStep4Fragment : Fragment() {

    private lateinit var timePicker: TimePicker
    private lateinit var btnGuardar: Button
    private var hourOfDay = 0
    private var minute = 0

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_med_step4, container, false)

        timePicker = view.findViewById(R.id.timePicker)
        btnGuardar = view.findViewById(R.id.btnGuardar)


        btnGuardar.setOnClickListener {
            saveAlarm()
        }

        return view
    }

    private fun saveAlarm() {
        /*// Obtener la hora y los minutos seleccionados
        hourOfDay = timePicker.hour
        minute = timePicker.minute

        val calendar = Calendar.getInstance()
        calendar.set(Calendar.HOUR_OF_DAY, hourOfDay)
        calendar.set(Calendar.MINUTE, minute)
        calendar.set(Calendar.SECOND, 0)

        val alarmManager = requireContext().getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val alarmIntent = Intent(requireContext(), AlarmReceiver::class.java)
        val pendingIntent: PendingIntent = if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
            PendingIntent.getBroadcast(requireContext(), 0, alarmIntent, PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT)
        } else {
            PendingIntent.getBroadcast(requireContext(), 0, alarmIntent, PendingIntent.FLAG_UPDATE_CURRENT)
        }

        // Establecer la alarma con el tiempo seleccionado
        alarmManager.set(AlarmManager.RTC_WAKEUP, calendar.timeInMillis, pendingIntent)

        // Guardar la hora seleccionada en el SharedViewModel
        val selectedTime = "$hourOfDay:$minute"*/
        //viewModel.setHora(selectedTime)

        startActivity(Intent(requireContext(), Guardando_Medicamento::class.java))
        // Iniciar la actividad Guardando_Medicamento

    }
}
