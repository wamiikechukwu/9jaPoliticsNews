package wami.ikechukwu.kanu.adapter.fragments

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import wami.ikechukwu.kanu.R
import wami.ikechukwu.kanu.model.network.NewsDetails

class HomeFragmentAdapter(private val mArrayList: ArrayList<NewsDetails>) : RecyclerView.Adapter<HomeFragmentAdapter.MyViewHolder>() {

    class MyViewHolder(inflate: View) : RecyclerView.ViewHolder(inflate) {

        fun bindMyViewHolder(myArrayList: NewsDetails) {

            val homeFrameImageView: ImageView = itemView.findViewById(R.id.home_fragment_recyclerView_imageview)
            val homeFragmentTitle: TextView = itemView.findViewById(R.id.home_fragment_recyclerView_title)


            Glide.with(itemView.context).load(myArrayList.urlToImage).into(homeFrameImageView)
            homeFragmentTitle.text = myArrayList.title
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.home_fragment_recycler_layout, parent, false))
    }

    override fun getItemCount(): Int {
        return mArrayList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bindMyViewHolder(mArrayList[position])
    }


}



