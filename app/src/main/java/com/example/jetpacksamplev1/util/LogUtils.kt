package com.example.jetpacksamplev1.util

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.util.Log
import android.widget.Toast
import com.example.jetpacksamplev1.MyApplication

object LogUtils {


    fun showToast(msg:String?){
        Toast.makeText(MyApplication.INSTANCE,msg, Toast.LENGTH_SHORT).show()
    }

    fun logError(msg: String?)
    {
        Log.e("Dots", msg.toString())
    }

    fun logApiResponse(msg: String?)
    {
        Log.e("Dots", msg.toString())
    }

}