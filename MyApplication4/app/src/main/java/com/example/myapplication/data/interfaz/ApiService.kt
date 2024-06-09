package com.example.myapplication.data.interfaz

import com.example.myapplication.data.models.FormatoMedicinaResp
import com.example.myapplication.data.models.LoginRequest
import com.example.myapplication.data.models.LoginResponse
import com.example.myapplication.data.models.RegisterRequest
import com.example.myapplication.data.models.RegisterResponse
import com.example.myapplication.data.models.medicamentosRes
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {
    @POST("usuario/iniciarSesion")
    suspend fun login(@Body request: LoginRequest): Response<LoginResponse>
    @POST("usuario/registerUsuario")
    suspend fun register(@Body request: RegisterRequest): Response<RegisterResponse>

    @GET("formato_medicina/getFormato_medicinas")
    suspend fun getFormatoMedicina(): Response<List<FormatoMedicinaResp>>

    @GET("medicamentos/getMedicamentos")
    suspend fun getMedicamentos(): Response<List<medicamentosRes>>
}
