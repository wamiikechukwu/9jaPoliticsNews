package wami.ikechukwu.kanu.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.PagerAdapter
import wami.ikechukwu.kanu.R
import wami.ikechukwu.kanu.model.OnboardingModel

class OnboardingAdapter(private var onboardingArrayList: ArrayList<OnboardingModel>) : PagerAdapter() {


    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        TODO("Not yet implemented")
    }

    override fun getCount(): Int {
        return onboardingArrayList.size
    }


    init {
        setItem(onboardingArrayList)
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        constructor(parent: ViewGroup) : this(LayoutInflater
                .from(parent.context)
                .inflate(R.layout.onboarding_item, parent, false))


        val title: TextView = itemView.findViewById(R.id.onboard_item_title)
        val subTitle: TextView = itemView.findViewById(R.id.onboard_item_subtitle)
        val image: ImageView = itemView.findViewById(R.id.onboard_item_image)
        val cardView: CardView = itemView.findViewById(R.id.onboarding_item_cardview)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(parent)
    }

    override fun getItemCount(): Int {
        return onboardingArrayList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

//        GETTING THE INDEX POSITION OF THE ARRAYLIST
        val dataModelPosition: OnboardingModel = onboardingArrayList[position]

//      SETTING THE TEXT REFERENCE WITH THE TEXT GOTTEN FROM THE ARRAYLIST
        val titleText = holder.title
        titleText.text = dataModelPosition.onboardTitle

        val subTitleText = holder.subTitle
        subTitleText.text = dataModelPosition.onboardSubTitle

        val image = holder.image
        image.setImageResource(dataModelPosition.onboardImage)

        val cardviewColor = holder.cardView
        cardviewColor.setCardBackgroundColor(dataModelPosition.onboardColor)

    }

    private fun setItem(onboardingArrayList: ArrayList<OnboardingModel>) {
        this.onboardingArrayList = onboardingArrayList
        notifyDataSetChanged()
    }

}


