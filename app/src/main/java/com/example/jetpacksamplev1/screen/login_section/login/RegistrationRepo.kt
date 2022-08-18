package com.example.jetpacksamplev1.screen.login_section.login

import com.example.jetpacksamplev1.network.ApiInterface
import com.example.jetpacksamplev1.ui.base.BaseRepository
import com.example.jetpacksamplev1.util.prefs.PreferencesHelper
import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.Response
import javax.inject.Inject

class RegistrationRepo  @Inject constructor(
) : BaseRepository() {


    suspend fun callPost(api:String, request: JsonObject){

//GsonUtils.toJosnObject(request)
       /* callPostWebApi(api, request).let { it?.enqueue(object :
            retrofit2.Callback<JsonObject?> {
            override fun onResponse(call: Call<JsonObject?>, response: Response<JsonObject?>) {
                try {
                    if (response.body() != null) {

                        //apiCallbacks.onSuccess(response.body(), apiName)
                    } else {
                        //   apiCallbacks.onError(response.body(), apiName)
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }

            override fun onFailure(call: Call<JsonObject?>, t: Throwable) {

            }
        })

        }*/

    }

}