import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SharedViewModel : ViewModel() {
    private val _medicamento = MutableLiveData<String>()
    val medicamento: LiveData<String> get() = _medicamento

    private val _formatoMedicamento = MutableLiveData<String>()
    val formatoMedicamento: LiveData<String> get() = _formatoMedicamento

    private val _frecuencia = MutableLiveData<String>()
    val frecuencia: LiveData<String> get() = _frecuencia

    private val _hora = MutableLiveData<String>()
    val hora: LiveData<String> get() = _hora

    fun setMedicamento(medicamento: String) {
        _medicamento.value = medicamento
    }

    fun setFormatoMedicamento(formatoMedicamento: String) {
        _formatoMedicamento.value = formatoMedicamento
    }

    fun setFrecuencia(frecuencia: String) {
        _frecuencia.value = frecuencia
    }

    fun setHora(hora: String) {
        _hora.value = hora
    }
}