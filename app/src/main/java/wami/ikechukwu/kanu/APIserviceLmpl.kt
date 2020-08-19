package wami.ikechukwu.kanu

import android.content.Context
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import wami.ikechukwu.kanu.`interface`.APIService

class APIserviceLmpl(val context: Context) : APIService {

    private val URLKEY: String = "buhari"

    private val url: String = "https://newsapi.org/v2/everything?q=$URLKEY&language=en&sortBy=publishedAt&pageSize=100&apiKey=883fcfd667104a34ac74c1827fb419e4"


    override fun getTitle(): List<String> {

        val list: ArrayList<String> = ArrayList()
        val jsonObjRequest = JsonObjectRequest(Request.Method.GET, url, null, Response.Listener { response ->

//            GET THE ARRAY FROM THE JSON OBJECTION WITH THE NAME "articles"
            val jsonArray = response.getJSONArray("articles")

//            USE A FOR LOOP TO ITTERATE THE JSON ARRAY
            for (i in 0 until jsonArray.length()) {
                val jsonObj = jsonArray.getJSONObject(i)

                list.add(jsonObj.getString("title"))


            }
        }, Response.ErrorListener { error ->
            Toast.makeText(context, error.toString(), Toast.LENGTH_LONG).show()
        })

        val requestQueue = Volley.newRequestQueue(context)
        requestQueue.add(jsonObjRequest)


        return list

    }
}