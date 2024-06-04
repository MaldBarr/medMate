package com.example.myapplication.data.models

data class LoginRequest(val email: String, val contrasenia: String)
data class LoginResponse(
    val id: Int,
    val name: String,
    val email: String,
    val contrasenia: String, // Asegúrate de incluir esta propiedad si es necesaria
    val createdAt: String,
    val updatedAt: String
)