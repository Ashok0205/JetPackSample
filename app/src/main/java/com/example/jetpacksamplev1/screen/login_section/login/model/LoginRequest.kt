package com.example.jetpacksamplev1.screen.login_section.login.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LoginRequest(
    @SerialName("email")
    val email: String="",
    @SerialName("password")
    val password: Int=0
)