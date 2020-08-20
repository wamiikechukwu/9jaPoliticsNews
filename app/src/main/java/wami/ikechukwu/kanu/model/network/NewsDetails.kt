package wami.ikechukwu.kanu.model.network


import com.google.gson.annotations.SerializedName

data class NewsDetails(
        @SerializedName("author")
        val author: String, // Oboh
        @SerializedName("content")
        val content: String, // By Dapo AkinrefonTHE United Niger Delta Energy Development Security Strategy, UNDEDSS, on Monday, said there is a calculated attempt to single out the Niger Delta by using the ongoing Niger Delta D… [+4004 chars]
        @SerializedName("description")
        val description: String, // THE United Niger Delta Energy Development Security Strategy, UNDEDSS, on Monday, said there is a calculated attempt to single out the Niger Delta by using the ongoing Niger Delta Development Commission, NDDC, probe to distract Nigerians from the corruption in…
        @SerializedName("publishedAt")
        val publishedAt: String, // 2020-08-10T18:25:57Z
        @SerializedName("title")
        val title: String, // NDDC probe: Enough’s enough, UNDEDSS tells FG
        @SerializedName("url")
        val url: String, // https://www.vanguardngr.com/2020/08/nddc-probe-enoughs-enough-undedss-tells-fg/
        @SerializedName("urlToImage")
        val urlToImage: String // https://i2.wp.com/www.vanguardngr.com/wp-content/uploads/2019/09/NDDC.jpg?fit=1500%2C840&ssl=1
)