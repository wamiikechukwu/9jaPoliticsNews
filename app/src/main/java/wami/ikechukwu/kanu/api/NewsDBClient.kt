package wami.ikechukwu.kanu.api

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

const val API_KEY = "883fcfd667104a34ac74c1827fb419e4"
const val BASE_URL = "https://newsapi.org/v2/everything?q=\"buhari\"&language=en&sortBy=publishedAt&pageSize=100&apiKey="

object NewsDBClient {
    //   https://newsapi.org/v2/everything?q="buhari"&language=en&sortBy=publishedAt&pageSize=100&apiKey=883fcfd667104a34ac74c1827fb419e4

    fun getClient(): NewsDBInterface {
        val requestInterceptor = Interceptor { chain ->
            val url = chain.request()
                    .url()
                    .newBuilder()
                    .addQueryParameter("api_key", API_KEY)
                    .build()

            val request = chain.request()
                    .newBuilder()
                    .url(url)
                    .build()
            return@Interceptor chain.proceed(request)
        }
        val okHttpClient: OkHttpClient = OkHttpClient.Builder()
                .addInterceptor(requestInterceptor)
                .connectTimeout(60, TimeUnit.SECONDS)
                .build()

        return Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl(BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(NewsDBInterface::class.java)
    }
}