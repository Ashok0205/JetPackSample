package com.example.jetpacksamplev1.network
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ApiClient {
    @Singleton
    @Provides
    fun getApiInterface(retroFit: Retrofit): ApiInterface {
        return retroFit.create(ApiInterface::class.java)
    }

    @Provides
    @Singleton
    fun getRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(APIEndPoint.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
    }

 /*   @Singleton
    @Provides
    fun getPaymentApiInterface(retroFit: Retrofit): PaymentApiInterface {
        return retroFit.create(PaymentApiInterface::class.java)
    }
    @Provides
    @Singleton
    fun getRetrofitPayment(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(APIEndPoint.BASE_URL_PAYMENT)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
    }
*/

    @Provides
    @Singleton
    fun getOkHttpCleint(httpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
        return  OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor)
            .readTimeout(60,TimeUnit.SECONDS)
            .connectTimeout(60,TimeUnit.SECONDS)
            .build()
    }

    @Provides
    @Singleton
    fun getHttpLoggingInterceptor(): HttpLoggingInterceptor {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)

        return httpLoggingInterceptor
    }
}