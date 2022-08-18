package com.example.jetpacksamplev1.util

import com.example.jetpacksamplev1.MyApplication
import com.example.jetpacksamplev1.network.CommonServerRes
import com.example.jetpacksamplev1.screen.login_section.login.model.LoginResponse
import com.google.gson.Gson
import com.google.gson.JsonObject


object GsonUtils {
    fun  toJosnObject2(model:String):JsonObject{
        val json = """{"title": "Kotlin Tutorial #1", "author": "bezkoder", "categories" : ["Kotlin","Basic"]}"""
        val gson = Gson()
       return gson.fromJson(json, JsonObject::class.java)
    }

    fun  toJosnObject(model:Any):JsonObject{
        //val json = """{"title": "Kotlin Tutorial #1", "author": "bezkoder", "categories" : ["Kotlin","Basic"]}"""
        val jsonModel: String =  Gson().toJson(model)
        val gson = Gson()
        return gson.fromJson(jsonModel, JsonObject::class.java)
    }

    fun  toCommonServerResponse(model:Any):CommonServerRes{
        return  Gson().fromJson(Gson().toJson(model), CommonServerRes::class.java)
    }
    fun  toLoginModle(model:Any):LoginResponse{
        MyApplication.INSTANCE.preferencesHelper.setLoginResponse(model.toString())
        return  Gson().fromJson(Gson().toJson(model), LoginResponse::class.java)
    }

  /*  fun  toUsersModle(model:Any):List<UserResponseItem>{
        val jsonArr = JSONArray(model)
        return List<UserResponseItem>()//Gson().fromJson(Gson().toJson(jsonArr), List<UserResponseItem>::class.java)
    }*/

}