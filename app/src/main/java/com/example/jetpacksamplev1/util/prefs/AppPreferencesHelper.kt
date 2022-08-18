package com.example.jetpacksamplev1.util.prefs

import android.content.Context
import android.content.SharedPreferences
import com.example.jetpacksamplev1.di.PreferenceInfo
import com.example.jetpacksamplev1.util.LoggedInMode
import com.example.jetpacksamplev1.util.NULL_INDEX
import com.example.jetpacksamplev1.util.PREF_KEY_CURRENT_USER_ID
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class AppPreferencesHelper  @Inject constructor(
    @ApplicationContext val context: Context,
    @PreferenceInfo val prefFileName: String?,
) : PreferencesHelper {


    private var mPrefs: SharedPreferences =context.getSharedPreferences(prefFileName, Context.MODE_PRIVATE);


    override suspend fun getCurrentUserLoggedInMode(): Int? {
        TODO("Not yet implemented")
    }

    override fun setCurrentUserLoggedInMode(mode: LoggedInMode) {
        TODO("Not yet implemented")
    }

    override fun setLoginResponse(response: String) {
        val id = response ?: NULL_INDEX
        mPrefs.putAny(PREF_KEY_CURRENT_USER_ID, id)
    }

    override fun getLoginResponse(): String? {
        val response = mPrefs.getString(PREF_KEY_CURRENT_USER_ID, NULL_INDEX)
        return if (response == NULL_INDEX) null else response
    }

    override fun setAccessToken(response: String) {
        TODO("Not yet implemented")
    }

    override fun getAccessToken(): String? {
       return  ""
    }

    override fun clearAll() {
        mPrefs.edit().clear().apply()
    }
    /*...........Prefs methods.............*/


    private fun SharedPreferences.putAny(name: String, any: Any?) {
        when (any) {
            is String -> edit().putString(name, any).apply()
            is Int -> edit().putInt(name, any).apply()
            is Boolean -> edit().putBoolean(name,any).apply()
            is Float -> edit().putFloat(name,any).apply()
        }
    }

    private fun SharedPreferences.remove(name:String){
        edit().remove(name).apply()
    }

}