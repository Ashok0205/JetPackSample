package com.example.jetpacksamplev1.network


sealed class WebResponse<T>(val data: T?=null, val errorMessage: String?=null, val responseCode : Int=0)  {
    class Loading<T> : WebResponse<T>()
    class Success<T>(data:T?=null) : WebResponse<T>(data=data)
    class Error<T>(errorMessage:String?,resCode:Int) : WebResponse<T>(errorMessage = if(errorMessage.isNullOrEmpty()) "" else errorMessage,responseCode = resCode)
}