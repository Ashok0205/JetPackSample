package com.example.jetpacksamplev1.network

import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*

interface ApiInterface {

    @POST
    suspend  fun apiCallPost(@Url url: String?, @Body toJson: JsonObject?):  Response<JsonObject?>?/* Call<JsonObject?>?*/
    @POST
    suspend  fun apiCallPost2(@Url url: String?, @Body toJson: JsonObject?): Response<JsonObject?>?
    @POST
    suspend   fun apiCallPostWithHeader(@Header("Authorization") header: String?,@Url url: String?,@Body toJson: JsonObject?):  Response<JsonObject?>?//Call<JsonObject?>?

    /*...GET Api Methods...*/
    @GET
    suspend  fun apiCallGet(@Url url: String?): Response<JsonObject?>?

    @GET
    suspend  fun apiCallGetWithHeader(@Header("Authorization") header: String?,@Url url: String?):  Response<JsonObject?>?//Call<JsonObject?>?
}