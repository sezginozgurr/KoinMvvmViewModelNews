package com.coroutinestest.core.service


import com.example.corotinestest.core.service.Api
import com.example.corotinestest.core.service.KidsVidRequestInterceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

abstract class ServiceConnector {

    companion object {
        private const val CONNECT_TIMEOUT: Long = 240
        private const val READ_TIMEOUT: Long = 240
        private const val WRITE_TIMEOUT: Long = 240
        val kidsVidService = getClient().create(Api::class.java)

        @Volatile
        private var INSTANCE: Retrofit? = null
        private fun getClient(): Retrofit {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }

            synchronized(this) {
                val kidsVidRequestInterceptor = KidsVidRequestInterceptor()
                val loggingInterceptor = HttpLoggingInterceptor()
                loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.HEADERS)
                loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)

                val httpClientBuilder = OkHttpClient.Builder()

                if (!httpClientBuilder.interceptors()
                        .contains(kidsVidRequestInterceptor) && !httpClientBuilder.interceptors()
                        .contains(loggingInterceptor)
                ) {
                    httpClientBuilder.addNetworkInterceptor(loggingInterceptor)
                    httpClientBuilder.addInterceptor(kidsVidRequestInterceptor)
                }

                httpClientBuilder.connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
                httpClientBuilder.readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
                httpClientBuilder.writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS)

                val instance = Retrofit.Builder()
                    .baseUrl("BuildConfig.BASE_URL")
                    .client(httpClientBuilder.build())
                    .addConverterFactory(GsonConverterFactory.create())

                    .build()
                // .addCallAdapterFactory(NetworkResponseAdapterFactory())
                INSTANCE = instance
                return instance
            }
        }
    }


}