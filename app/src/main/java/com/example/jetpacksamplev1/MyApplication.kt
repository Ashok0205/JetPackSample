package com.example.jetpacksamplev1

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.example.jetpacksamplev1.network.ApiCallbacks
import com.example.jetpacksamplev1.network.ApiInterface
import com.example.jetpacksamplev1.network.PaymentApiInterface
import com.example.jetpacksamplev1.network.RetrofitApi
import com.example.jetpacksamplev1.util.coroutines.AppDispatcherProvider
import com.example.jetpacksamplev1.util.prefs.PreferencesHelper
import com.google.gson.Gson
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

@HiltAndroidApp
class MyApplication: Application()
{

    @Inject
    lateinit var apiInterface: ApiInterface

   /* @Inject
    lateinit var paymentApiInterface: PaymentApiInterface*/
  /*  @Inject
    lateinit var retrofitApi: RetrofitApi*/

    @Inject
    lateinit var preferencesHelper: PreferencesHelper


    @Inject
    lateinit var appDispatcherProvider: AppDispatcherProvider

    @Inject
    lateinit var retrofitApi: RetrofitApi

  /*  @Inject
    lateinit var apiCallbacks: ApiCallbacks*/



    companion object{
        lateinit var INSTANCE: MyApplication
    }


    override fun onCreate() {
        super.onCreate()
        INSTANCE = this
        }

}