package com.example.myapplication.data.models

import retrofit2.Response

data class recordatorioReq(
    val id_usuario: String?,
    val id_medicamento: Int?,
    val id_formato: Int?,
    val id_frecuencia: Int?,
)

data class recordatorioRes(
    val id_recordatorio: String,
    val id_usuario: String,
    val id_medicamento: String,
    val id_formato: String,
    val id_frecuencia: String,
    val id_hora: String,
    val created_at: String,
    val updated_at: String,
)

data class recordatorioUsuarioReq(
    val id_usuario: String?,
)

data class recordatorioUsuarioRes(
    val id: Int,
    val id_usuario: String,
    var id_medicamento: String,
    val id_formato: String,
    val id_frecuencia: String,
    val id_hora: String,
    val created_at: String,
    val updated_at: String,
)

data class recordatorioUpatedReq(
    val id: String?,
    val id_medicamento: Response<medicamentosRes>,
    val id_formato: Response<formatoRes>,
    val id_frecuencia: Response<frecuenciaRes>,
)

data class recordatorioUpdatedRes(
    val id: Int?,
    val id_usuario: String,
    val id_medicamento: String,
    val id_formato: String,
    val id_frecuencia: String,
    val id_hora: String,
    val created_at: String,
    val updated_at: String,
)



data class recordatorioDeleteReq(
    val id: Int,
)

data class recordatorioDeleteRes(
    val message: String,
)
