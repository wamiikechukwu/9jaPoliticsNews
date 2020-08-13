package wami.ikechukwu.kanu.view.ui

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.google.android.material.snackbar.Snackbar
import dmax.dialog.SpotsDialog
import org.json.JSONException
import wami.ikechukwu.kanu.R
import wami.ikechukwu.kanu.model.onboarding.OnboardingModel
import wami.ikechukwu.kanu.viewmodel.newsAdapter
import wami.ikechukwu.kanu.viewmodel.newsAdapter.onclicklistener
import wami.ikechukwu.kanu.viewmodel.news_detail
import java.util.*

class MainActivity : AppCompatActivity(), onclicklistener {
    //THESE VARIABLE ARE USED TO GET THE MATCHING RESPONSE FROM THE JSON FROM THE API
    private val KEY_TITLE = "title"
    private val KEY_DESCRIPTION = "description"
    private val KEY_URL = "url"
    private val KEY_URL_TO_IMAGE = "urlToImage"
    private val KEY_PUBLISHED_AT = "publishedAt"

    //THIS STRING IS USED TO APPEND THE SEARCH WORD TO THE QUERY
    var urlLink = "buhari"

    //THIS SERVES AS A GLOBAL VARIABLE TO HOLD THE POSITION (NUMBER/INTEGER) OF THE ITEM CLICKED IN
    // THE RECYCLERVIEW
    var mPosition = 0

    //THIS ARRAY LIST CONTAINS THE DATA FROM THE JSON, THAT WILL MIND TO THE RECYCLERVIEW
    var list: ArrayList<OnboardingModel>? = null

    //
    private var recyclerView: RecyclerView? = null
    private var mAdapter: newsAdapter? = null
    private var mLayout: RecyclerView.LayoutManager? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar!!.hide()
        setContentView(R.layout.activity_main)
        list = ArrayList()
        recyclerView = findViewById(R.id.recyclerView)
        mAdapter = newsAdapter(this, list, this)
        mLayout = LinearLayoutManager(this)
//        recyclerView.setLayoutManager(mLayout)
//        recyclerView.setAdapter(mAdapter)
        if (isConnected) {
            jsonParser()
        } else {
//            val snackbar = Snackbar.make(recyclerView, "Oops No Internet",
//                    Snackbar.LENGTH_INDEFINITE).setAction("Retry") { jsonParser() }
//            snackbar.show()
        }
    }

    private val isConnected: Boolean
        private get() {
            val connectivityManager = applicationContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val networkInfo = connectivityManager.activeNetworkInfo
            return networkInfo != null && networkInfo.isConnectedOrConnecting
        }

    private fun jsonParser() {
        while (!isConnected) {
            val snackbar = Snackbar.make(recyclerView!!, "Oops No Internet",
                    Snackbar.LENGTH_INDEFINITE).setAction("Retry") { jsonParser() }
            snackbar.show()
            break
        }
        val progressDialog: AlertDialog = SpotsDialog(this, R.style.customProgressDialog)
        progressDialog.show()
        val jsonObjectRequest = JsonObjectRequest(Request.Method.GET, "https://newsapi.org/v2/everything?q=$urlLink&language=en&sortBy=publishedAt&pageSize=100&apiKey=a5f976b34089493abc8f97f088e5df64", null, Response.Listener { response ->
            try {
                val jsonArray = response.getJSONArray("articles")

                //Using a for loop to get the object (data) in the JSON
                for (i in 0 until jsonArray.length()) {
                    val jsonObject = jsonArray.getJSONObject(i)
                    val JO = jsonArray.getJSONObject(mPosition)

//                        OnboardingModel OnboardingModel = new OnboardingModel();
//                        OnboardingModel.setTitle(jsonObject.getString(KEY_TITLE));
//                        OnboardingModel.setImage(jsonObject.getString(KEY_URL_TO_IMAGE));
//                        OnboardingModel.setDescrip(jsonObject.getString(KEY_DESCRIPTION));
//
//                        list.add(OnboardingModel);
                }
            } catch (e: JSONException) {
                e.printStackTrace()
            }
            mAdapter!!.notifyDataSetChanged()
            progressDialog.dismiss()
        }, Response.ErrorListener { error ->
            Log.e("Volley", error.toString())
            progressDialog.dismiss()
        })
        val requestQueue = Volley.newRequestQueue(this)
        requestQueue.add(jsonObjectRequest)
    }

    override fun onItemClick(position: Int) {
        mPosition = position
        val intent = Intent(this, news_detail::class.java)
        intent.putExtra("POSITION", position)
        startActivity(intent)
    }
}