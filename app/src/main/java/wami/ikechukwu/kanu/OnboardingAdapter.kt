package wami.ikechukwu.kanu

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import wami.ikechukwu.kanu.model.OnboardingModel

class OnboardingAdapter(private val onboardingArrayList: ArrayList<OnboardingModel>) : RecyclerView.Adapter<OnboardingAdapter.ViewHolder>() {


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title: TextView = itemView.findViewById(R.id.onboard_item_title)
        val subTitle: TextView = itemView.findViewById(R.id.onboard_item_subtitle)
        val image: ImageView = itemView.findViewById(R.id.onboard_item_image)
        val cardView: CardView = itemView.findViewById(R.id.onboarding_item_cardview)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OnboardingAdapter.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context).inflate(R.layout.onboarding_item, parent, false)
        return ViewHolder(layoutInflater)
    }

    override fun getItemCount(): Int {
        return onboardingArrayList.size
    }

    override fun onBindViewHolder(holder: OnboardingAdapter.ViewHolder, position: Int) {

//        GETTING THE INDEX POSITION OF THE ARRAYLIST
        val dataModel: OnboardingModel = onboardingArrayList[position]

//      SETTING THE TEXT REFERENCE WITH THE TEXT GOTTEN FROM THE ARRAYLIST
        val titleText = holder.title
        titleText.text = dataModel.onboardTitle

        val subTitleText = holder.subTitle
        subTitleText.text = dataModel.onboardSubTitle

        val image = holder.image
        image.setImageResource(dataModel.onboardImage)

        val cardviewColor = holder.cardView
        cardviewColor.setCardBackgroundColor(dataModel.onboardColor)

    }


}


