package com.example.myapplication.data.models

data class medicamentosReq(
    val medicamento: String?
)
data class medicamentosRes(
    val id: Int?,
)

data class MedicamentosbyIdReq(
    val id: String?
)

data class MedicamentosbyIdRes(
    val nombre: String?
)

