package es.estech.myapplication.data.retrofit

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitHelper {

    private val BASE_URL = "https://api.thecatapi.com/v1/"

    val httpClient = OkHttpClient.Builder().apply {
        interceptors().add(HeaderInterceptor())
    }.build()
    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(httpClient)
        .build()

    val myService by lazy {
        retrofit.create(MyService::class.java)
    }

}