package com.example.jetpacksamplev1.ui.base

import com.example.jetpacksamplev1.MyApplication
import com.example.jetpacksamplev1.network.ApiCallbacks
import com.example.jetpacksamplev1.network.ApiInterface
import com.example.jetpacksamplev1.util.*
import com.example.jetpacksamplev1.util.GsonUtils.toCommonServerResponse
import com.example.jetpacksamplev1.util.prefs.PreferencesHelper
import com.google.gson.JsonObject

open class BaseRepository() {
    fun getApiHelper(): ApiInterface {
        return MyApplication.INSTANCE.apiInterface
    }

    fun getPreferencesHelper(): PreferencesHelper {
        return MyApplication.INSTANCE.preferencesHelper
    }

    fun getUserId(): String {
        return getPreferencesHelper().getLoginResponse() ?: ""
    }



    /**********************************/

    suspend fun callPostWebApi(apiName: String, request: JsonObject,apiCallbacks: ApiCallbacks)
    {
        if (Utility.isOnline()) {
        try {
            val response =MyApplication.INSTANCE.apiInterface. apiCallPost2(apiName, request)
            if (response?.body() != null) {
                LogUtils.logApiResponse("Api Response $apiName :-  ${response.body()}.toString()")
                var resData = toCommonServerResponse(response?.body()!!)
                if (resData.data!=null)
                    apiCallbacks.onSuccess(response?.body(), apiName)
                else
                    apiCallbacks.onError(resData.message,apiName,response?.code()!!)

            } else {
                LogUtils.logApiResponse("Api Response $apiName :-  $response.toString()")
                apiCallbacks.onError(response!!.message(),apiName,response?.code()!!)
            }
        } catch (e: Exception) {
            apiCallbacks.onError(e.message,apiName,SERVER_ERORR)
        }

    }else{
        apiCallbacks.onError(NO_INTERNET_CONNECTION,apiName,INTERNET_ERORR)
    }
    }



    suspend fun callGetWebApi(apiName: String, apiCallbacks: ApiCallbacks)
    {
        if (Utility.isOnline()) {
            try {
                val response =MyApplication.INSTANCE.apiInterface. apiCallGet(apiName)
                if (response?.body() != null) {
                    LogUtils.logApiResponse("Api Response $apiName :-  ${response.body()}.toString()")
                    var resData = toCommonServerResponse(response?.body()!!)
                    if (resData.data!=null)
                        apiCallbacks.onSuccess(response?.body(), apiName)
                    else
                        apiCallbacks.onError(resData.message,apiName,response?.code()!!)

                } else {
                    LogUtils.logApiResponse("Api Response $apiName :-  $response.toString()")
                    apiCallbacks.onError(response!!.message(),apiName,response?.code()!!)
                }
            } catch (e: Exception) {
                apiCallbacks.onError(e.message,apiName,SERVER_ERORR)
            }

        }else{
            apiCallbacks.onError(NO_INTERNET_CONNECTION,apiName,INTERNET_ERORR)
        }
    }

}