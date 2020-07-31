package wami.ikechukwu.kanu.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.viewpager.widget.PagerAdapter
import com.bumptech.glide.Glide
import wami.ikechukwu.kanu.R
import wami.ikechukwu.kanu.model.OnboardingModel

class OnboardingAdapter(private var onboardingArrayList: ArrayList<OnboardingModel>) : PagerAdapter() {


    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view == `object`
    }

    override fun getCount(): Int {
        return onboardingArrayList.size
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {

        val mView = LayoutInflater.from(container.context).inflate(R.layout.onboarding_item, container, false)
        val onTitle: TextView = mView.findViewById(R.id.onboard_item_title)
        onTitle.text = onboardingArrayList[position].onboardTitle

        val onSubtitle: TextView = mView.findViewById(R.id.onboard_item_subtitle)
        onSubtitle.text = onboardingArrayList[position].onboardSubTitle

        val onImage: ImageView = mView.findViewById(R.id.onboard_item_image)
        Glide.with(container.context).load(onboardingArrayList[position].onboardImage).into(onImage)

        val onCard: CardView = mView.findViewById(R.id.onboarding_item_cardview)
        onCard.setCardBackgroundColor(onboardingArrayList[position].onboardColor)

        container.addView(mView, 0)
        return mView

    }

}


