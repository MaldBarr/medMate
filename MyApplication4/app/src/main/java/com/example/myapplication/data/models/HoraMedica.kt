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
    val id: Int,
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
    val id: Int,
    val nombre: String?,
    val fecha: Date?,
    val id_hora: Int?,
    val id_minuto: Int?,
)

data class DeleteHoraMedicaReq(
    val id: Int,
)

data class DeleteHoraMedicaRes(
    val message: String,
)