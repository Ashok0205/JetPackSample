package com.example.jetpacksamplev1.network

import com.google.gson.JsonObject

interface ApiCallbacks {
    /**
     * method will call when api response is success. response status is success
     *
     * @param jsonObject response json object body
     * @param apiName    api name
     */
    fun onSuccess(jsonObject: Any?, apiName: String?)

    /**
     * method will call when api response is error. response status is not success
     *
     * @param jsonObject response json object body
     * @param apiName    api name
     */
    fun onError(jsonObject: String?, apiName: String?,code:Int)
}