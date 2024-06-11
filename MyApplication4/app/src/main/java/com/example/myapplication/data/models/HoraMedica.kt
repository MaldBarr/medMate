package com.example.myapplication.data.models

import java.util.Date

data class HoraMedicaReq(
    val id: String?,
    val tratamiento: String?,
    val fecha: Date?,
    val hora: Int?,
    val minuto: Int?,
)

data class HoraMedicaRes(
    val id: String?,
    val tratamiento: String?,
    val fecha: Date?,
    val hora: Int?,
    val minuto: Int?,
)