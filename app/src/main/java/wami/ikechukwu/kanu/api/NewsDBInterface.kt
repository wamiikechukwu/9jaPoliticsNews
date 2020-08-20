package wami.ikechukwu.kanu.api

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import wami.ikechukwu.kanu.model.network.NewsDetails

interface NewsDBInterface {
//   https://newsapi.org/v2/everything?q="buhari"&language=en&sortBy=publishedAt&pageSize=100&apiKey=883fcfd667104a34ac74c1827fb419e4

    @GET("topic/{news_topic}")
    fun getNewsDetails(@Path("news_topic") newsTopic: String): Single<NewsDetails>
}