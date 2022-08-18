package com.example.jetpacksamplev1.screen.login_section.forgot_password.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class ForgotPasswordRequests(
    @SerialName("email")
    var email: String="",
    )