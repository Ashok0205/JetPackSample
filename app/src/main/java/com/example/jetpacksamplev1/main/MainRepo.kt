package com.example.jetpacksamplev1.main

import com.example.jetpacksamplev1.MyApplication
import com.example.jetpacksamplev1.network.ApiCallbacks
import com.example.jetpacksamplev1.ui.base.BaseRepository
import com.example.jetpacksamplev1.util.*
import javax.inject.Inject

class MainRepo @Inject constructor() : BaseRepository() {



    suspend fun getMovieWebApi(apiName: String, apiCallbacks: ApiCallbacks)
    {
        if (Utility.isOnline()) {
            try {
                val response =  MyApplication.INSTANCE.retrofitApi.getServicePayment().getMovieApi(apiName)//MyApplication.INSTANCE.apiInterface.apiCallGet(apiName)
                if (response?.body() != null) {
                    LogUtils.logApiResponse("Api Response $apiName :-  ${response.body()}")
                    if (response?.body()!=null)
                        apiCallbacks.onSuccess(response?.body(), apiName)
                    else
                        apiCallbacks.onError(NOT_FOUND_DATA,apiName,response?.code()!!)

                } else {
                    LogUtils.logApiResponse("Api Response $apiName :-  $response.toString()")
                    apiCallbacks.onError(response!!.message(),apiName,response?.code()!!)
                }
            } catch (e: Exception) {
                apiCallbacks.onError(e.message,apiName, SERVER_ERORR)
            }

        }else{
            apiCallbacks.onError(NO_INTERNET_CONNECTION,apiName, INTERNET_ERORR)
        }
    }

}