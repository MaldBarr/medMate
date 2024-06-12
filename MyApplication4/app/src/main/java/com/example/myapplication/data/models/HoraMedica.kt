package com.example.myapplication.data.models

import java.util.Date

data class HoraMedicaReq(
    val id_usuario: String?,
    val nombre: String?,
    val fecha: Date?,
    val id_hora: Int?,
    val id_minuto: Int?,
)

data class HoraMedicaRes(
    val id_usuario: String?,
    val nombre: String?,
    val fecha: Date?,
    val id_hora: Int?,
    val id_minuto: Int?,
)

data class ObtenerHorasMedicasIDReq(
    val id_usuario: String?
)

data class ObtenerHorasMedicasIDRes(
    val nombre: String?,
    val fecha: Date?,
    val id_hora: Int?,
    val id_minuto: Int?,
)