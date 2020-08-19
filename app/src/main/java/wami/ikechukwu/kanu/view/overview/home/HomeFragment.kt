package wami.ikechukwu.kanu.view.overview.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import wami.ikechukwu.kanu.HomeFragmentViewModel
import wami.ikechukwu.kanu.R
import wami.ikechukwu.kanu.adapter.fragments.HomeFragmentAdapter
import wami.ikechukwu.kanu.databinding.HomeFragmentLayoutBinding
import wami.ikechukwu.kanu.model.network.ResponseDataModel

class HomeFragment : Fragment() {

    //    DATA BINDING
    private lateinit var binding: HomeFragmentLayoutBinding

    //    VIEW MODEL
    private lateinit var homeFragmentViewModel: HomeFragmentViewModel

    //    VIEW MODEL FACTORY
    private lateinit var viewManger: RecyclerView.LayoutManager
    private lateinit var viewAdapter: RecyclerView.Adapter<*>


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val list: ArrayList<ResponseDataModel> = java.util.ArrayList()

//  IMPLEMENTING DATA BINDING
        binding = DataBindingUtil.inflate(inflater, R.layout.home_fragment_layout, container, false)

        homeFragmentViewModel = ViewModelProviders.of(this).get(HomeFragmentViewModel::class.java)

        val URLKEY: String = "buhari"

        val url: String = "https://newsapi.org/v2/everything?q=$URLKEY&language=en&sortBy=publishedAt&pageSize=100&apiKey=883fcfd667104a34ac74c1827fb419e4"


        val jsonObjRequest = JsonObjectRequest(Request.Method.GET, url, null, Response.Listener { response ->

//            GET THE ARRAY FROM THE JSON OBJECTION WITH THE NAME "articles"
            val jsonArray = response.getJSONArray("articles")

//            USE A FOR LOOP TO ITTERATE THE JSON ARRAY
            for (i in 0 until jsonArray.length()) {
                val jsonObj = jsonArray.getJSONObject(i)

                list.add(ResponseDataModel(jsonObj.getString("title").toString(), jsonObj.getString("urlToImage").toString()))
                Toast.makeText(context, "Ran", Toast.LENGTH_LONG).show()
                binding.homeIntroOne.text = jsonObj.getString("title").toString()
                HomeFragmentAdapter(list)

            }
        }, Response.ErrorListener { error ->
            Toast.makeText(context, error.toString(), Toast.LENGTH_LONG).show()
        })

        val requestQueue = Volley.newRequestQueue(context)
        requestQueue.add(jsonObjRequest)

        viewManger = LinearLayoutManager(context)
        viewAdapter = HomeFragmentAdapter(list)

        binding.homeFragmentRecyclerview.apply {
            layoutManager = viewManger
            adapter = viewAdapter
        }


        return binding.root
    }

}