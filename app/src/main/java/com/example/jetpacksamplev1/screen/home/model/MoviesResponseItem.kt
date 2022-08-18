package com.example.jetpacksamplev1.screen.home.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MoviesResponseItem(
    @SerialName("category")
    var category: String,
    @SerialName("desc")
    var desc: String,
    @SerialName("imageUrl")
    var imageUrl: String,
    @SerialName("name")
    var name: String
)