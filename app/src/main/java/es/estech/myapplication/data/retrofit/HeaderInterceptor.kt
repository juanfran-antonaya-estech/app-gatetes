package es.estech.myapplication.data.retrofit

import okhttp3.Interceptor
import okhttp3.Response

class HeaderInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val peticionOriginal = chain.request()
        val requestBuilder = peticionOriginal.newBuilder()
            .header("x-api-key", MyService.API_KEY)
            .header("Content-Type", MyService.CONTENT_TYPE)
        val request = requestBuilder.build()
        return chain.proceed(request)
    }

}