package com.example.myapplication.data.interfaz

import com.example.myapplication.data.models.HoraMedicaReq
import com.example.myapplication.data.models.HoraMedicaRes
import com.example.myapplication.data.models.HoraReq
import com.example.myapplication.data.models.HoraRes
import com.example.myapplication.data.models.LoginRequest
import com.example.myapplication.data.models.LoginResponse
import com.example.myapplication.data.models.MedicamentosbyIdReq
import com.example.myapplication.data.models.MedicamentosbyIdRes
import com.example.myapplication.data.models.RegisterRequest
import com.example.myapplication.data.models.RegisterResponse
import com.example.myapplication.data.models.formatoReq
import com.example.myapplication.data.models.formatoRes
import com.example.myapplication.data.models.frecuenciaReq
import com.example.myapplication.data.models.frecuenciaRes
import com.example.myapplication.data.models.medicamentosReq
import com.example.myapplication.data.models.medicamentosRes
import com.example.myapplication.data.models.recordatorioDeleteReq
import com.example.myapplication.data.models.recordatorioDeleteRes
import com.example.myapplication.data.models.recordatorioReq
import com.example.myapplication.data.models.recordatorioRes
import com.example.myapplication.data.models.recordatorioUpatedReq
import com.example.myapplication.data.models.recordatorioUpdatedRes
import com.example.myapplication.data.models.recordatorioUsuarioReq
import com.example.myapplication.data.models.recordatorioUsuarioRes
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {
    @POST("usuario/iniciarSesion")
    suspend fun login(@Body request: LoginRequest): Response<LoginResponse>
    @POST("usuario/registerUsuario")
    suspend fun register(@Body request: RegisterRequest): Response<RegisterResponse>

    @POST("medicamentos/obtenerIdMedicamento")
    suspend fun idMedicamento(@Body request: medicamentosReq): Response<medicamentosRes>

    @POST("formato_medicina/obtenerIdFormato_medicina")
    suspend fun idformato(@Body request: formatoReq): Response<formatoRes>

    @POST("frecuencias/obtenerIdFrecuencia")
    suspend fun idFrecuencia(@Body request: frecuenciaReq): Response<frecuenciaRes>

    @POST("horas/obtenerIdHora")
    suspend fun idHora(@Body request: HoraReq): Response<HoraRes>

    @POST("recordatorio/createRecordatorio")
    suspend fun createRecordatorio(@Body request: recordatorioReq): Response<recordatorioRes>

    @POST("recordatorio/getRecordatoriosByUserId")
    suspend fun getRecordatoriosByUserId(@Body request: recordatorioUsuarioReq): Response<List<recordatorioUsuarioRes>>

    @POST("medicamentos/obtenerNombreMedicamentoPorId")
    suspend fun getNombreMedicamentoById(@Body request: MedicamentosbyIdReq): Response<MedicamentosbyIdRes>

    @POST("recordatorio/updateRecordatorio")
    suspend fun updateRecordatorio(@Body request: recordatorioUpatedReq): Response<recordatorioUpdatedRes>

    @POST("recordatorio/deleteRecordatorio")
    suspend fun recordatorioDeleteReq(@Body request: recordatorioDeleteReq): Response<recordatorioDeleteRes>

    @POST("recordatorio_hora_medica/createHoraMedica")
    suspend fun createHoraMedica(@Body request: HoraMedicaReq): Response<HoraMedicaRes>


}
