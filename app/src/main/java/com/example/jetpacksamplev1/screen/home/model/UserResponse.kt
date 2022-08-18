package com.example.jetpacksamplev1.screen.home.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

//class UserResponse : List<UserResponseItem>()

@Serializable
data class UserResponseItem(
    @SerialName("address")
    val address: Address,
    @SerialName("company")
    val company: Company,
    @SerialName("email")
    val email: String,
    @SerialName("id")
    val id: Int,
    @SerialName("name")
    val name: String,
    @SerialName("phone")
    val phone: String,
    @SerialName("username")
    val username: String,
    @SerialName("website")
    val website: String
)


@Serializable
data class Geo(
    @SerialName("lat")
    val lat: String,
    @SerialName("lng")
    val lng: String
)



@Serializable
data class Company(
    @SerialName("bs")
    val bs: String,
    @SerialName("catchPhrase")
    val catchPhrase: String,
    @SerialName("name")
    val name: String
)


@Serializable
data class Address(
    @SerialName("city")
    val city: String,
    @SerialName("geo")
    val geo: Geo,
    @SerialName("street")
    val street: String,
    @SerialName("suite")
    val suite: String,
    @SerialName("zipcode")
    val zipcode: String
)