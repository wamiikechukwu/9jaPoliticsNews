package wami.ikechukwu.kanu.repository

import android.telecom.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface EndPoints {
    @GET("/v2/everything?q=politics&language=en&sortBy=publishedAt&pageSize=100&apiKey=883fcfd667104a34ac74c1827fb419e4")
    fun getNews(@Query("api_key") key: String):Call
}