package com.coroutine.core.di


import com.brkcnszgn.networkresponse.NetworkResponseAdapterFactory
import com.example.corotinestest.core.repository.DetailRepository
import com.example.corotinestest.core.repository.LastScreenRepository
import com.example.corotinestest.core.repository.UserRepository
import com.example.corotinestest.core.service.Api
import com.example.corotinestest.core.service.KidsVidRequestInterceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

private const val CONNECT_TIMEOUT: Long = 240
private const val READ_TIMEOUT: Long = 240
private const val WRITE_TIMEOUT: Long = 240

val networkModule = module {

    /**
     * Retrofit isteklerindeki interceptorlerin icerikler
     */
    single {
        val interceptor = HttpLoggingInterceptor()
        interceptor.apply {
            level = HttpLoggingInterceptor.Level.HEADERS
            level = HttpLoggingInterceptor.Level.BODY
        }
    }

    /**
     * Bu method icinde OkHttp client olusturulur, retrofit isteklerinde kullanilmasi icin.
     */
    single {
        val kidsVidRequestInterceptor = KidsVidRequestInterceptor()
        val client = OkHttpClient.Builder()

        if (!client.interceptors()
                .contains(kidsVidRequestInterceptor) && !client.interceptors()
                .contains(get<HttpLoggingInterceptor>())
        ) {
            client.addNetworkInterceptor(get<HttpLoggingInterceptor>())
            client.addInterceptor(kidsVidRequestInterceptor)
        }

        client.connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
        client.readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
        client.writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS)

        client.build()
    }

    /**
     * Bu method retrofit instance olusturur. Single fonksiyonu sadece bir tane instance olusturulmasini ve kullanilmasini saglar.
     */
    single {
        Retrofit.Builder()
            .baseUrl("https://jsonplaceholder.typicode.com/")
            .client(get())
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(NetworkResponseAdapterFactory())
            .build()
        //
    }

    single {
        get<Retrofit>().create(Api::class.java)
    }

    factory<UserRepository> { UserRepository(get()) }
    factory<DetailRepository> { DetailRepository(get()) }
    factory<LastScreenRepository> { LastScreenRepository(get()) }
//    factory<LoginRepository> { LoginRepository(get()) }
//    factory<InterestRepository> { InterestRepository(get()) }
//    factory<CreateChildRepository> { CreateChildRepository(get()) }
//    factory<ParentMyKidsRepository> { ParentMyKidsRepository(get()) }
//    factory<ChildVideosRepository> { ChildVideosRepository(get()) }
//    factory<CategoriesRepository> { CategoriesRepository(get()) }
//    factory<FormAboutRepository> { FormAboutRepository(get()) }
}

