package com.example.jetpacksamplev1.network

import com.example.jetpacksamplev1.BuildConfig
import com.example.jetpacksamplev1.MyApplication
import com.example.jetpacksamplev1.network.APIEndPoint.BASE_URL
import com.example.jetpacksamplev1.network.APIEndPoint.BASE_URL_PAYMENT
//import com.example.jetpacksamplev1.network.moshiFactories.MyStandardJsonAdapters
//import com.example.mvvmKotlinJetpackCompose.data.network.moshiFactories.MyKotlinJsonAdapterFactory
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Inject
import javax.inject.Singleton

private const val timeoutRead = 30
private const val contentType = "Content-Type"
private const val contentTypeValue = "application/json"
private const val timeoutConnect = 30
private const val baseUrl="https://your.com/Api/"

@Singleton
class RetrofitApi  @Inject constructor(){
    private lateinit var request: Request
    private lateinit var retrofit: Retrofit
    private lateinit var retrofitPayment: Retrofit
    private val okHttpBuilder: OkHttpClient.Builder = OkHttpClient.Builder()
    //var protectedApiHeader: ApiHeader.ProtectedApiHeader= ApiHeader.ProtectedApiHeader("","","")
    private var headerInterceptor = Interceptor { chain ->
        val original = chain.request()
      val  accessToken=   MyApplication.INSTANCE.preferencesHelper.getAccessToken()
        if(accessToken.isNullOrEmpty()) {
            request = original.newBuilder()
                .header(contentType, contentTypeValue)
                .method(original.method, original.body)
                .build()
        }else{
            request = original.newBuilder()
                .header(contentType, contentTypeValue)
                .header("Authorization", "Bearer " + accessToken)
                .method(original.method, original.body)
                .build()
        }

        chain.proceed(request)
    }

    private val logger: HttpLoggingInterceptor
        get() {
            val loggingInterceptor = HttpLoggingInterceptor()
            if (BuildConfig.DEBUG) {
                loggingInterceptor.apply { level = HttpLoggingInterceptor.Level.BODY }
            }
            return loggingInterceptor
        }

    var client: OkHttpClient


    init {
        okHttpBuilder.addInterceptor(headerInterceptor)
        okHttpBuilder.addInterceptor(logger)
        okHttpBuilder.connectTimeout(timeoutConnect.toLong(), TimeUnit.SECONDS)
        okHttpBuilder.readTimeout(timeoutRead.toLong(), TimeUnit.SECONDS)
        client = okHttpBuilder.build()
        retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            /*...........Payment Web Api..........*/
        retrofitPayment= Retrofit.Builder()
            .baseUrl(BASE_URL_PAYMENT)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    fun  getService(): ApiInterface {
        return  retrofit.create(ApiInterface::class.java)
    }
    fun  getServicePayment(): PaymentApiInterface {
        return  retrofitPayment.create(PaymentApiInterface::class.java)
    }



}