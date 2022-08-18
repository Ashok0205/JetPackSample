package com.example.jetpacksamplev1.screen.login_section.login.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LoginResponse(
    @SerialName("code")
    val code: Int,
    @SerialName("data")
    val `data`: Data,
    @SerialName("message")
    val message: String
)

@Serializable
data class Data(
    @SerialName("Email")
    val email: String,
    @SerialName("Id")
    val id: Int,
    @SerialName("Name")
    val name: String,
    @SerialName("Token")
    val token: String
)