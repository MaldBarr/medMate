package com.example.myapplication.data

import com.example.myapplication.data.model.Remoteresult
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface RetrofitService {
    @FormUrlEncoded
    @POST("https://backendmedimate.fly.dev/usuario/iniciarSesion")
    suspend fun iniciarSesion(
        @Field("correo") correo: String,
        @Field("contrasenia") contrasenia: String
    ): Remoteresult
}

object RetrofitServiceFactory {
    private const val BASE_URL = "https://backendmedimate.fly.dev/"

    fun makeRetrofitService(): RetrofitService {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(RetrofitService::class.java)
    }
}
