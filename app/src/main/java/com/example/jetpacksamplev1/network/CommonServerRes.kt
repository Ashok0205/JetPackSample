package com.example.jetpacksamplev1.network

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class CommonServerRes(
    @SerializedName("data")
    val data: Any,
    @SerializedName("message")
    val message: String,
    @SerializedName("code")
    val code: Int,
// {"code":1,"message":"invalid username or password","data":null}
    )