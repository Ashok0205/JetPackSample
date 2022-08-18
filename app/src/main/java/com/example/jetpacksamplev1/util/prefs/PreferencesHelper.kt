package com.example.jetpacksamplev1.util.prefs

import com.example.jetpacksamplev1.util.LoggedInMode

interface PreferencesHelper {
    suspend fun getCurrentUserLoggedInMode(): Int?
    fun setCurrentUserLoggedInMode(mode: LoggedInMode)
    fun setLoginResponse(response:String)
    fun getLoginResponse():String?
    fun setAccessToken(response:String)
    fun getAccessToken():String?

    fun clearAll()
}