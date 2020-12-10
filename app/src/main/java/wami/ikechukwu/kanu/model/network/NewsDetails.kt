package wami.ikechukwu.kanu.model.network


data class News(
        val result: List<NewsDetails>
)
data class NewsDetails(
        val title: String,
        val description: String,
        val url: String,
        val urlToImage: String,
        val publishedAt: String,
        val author: String
)