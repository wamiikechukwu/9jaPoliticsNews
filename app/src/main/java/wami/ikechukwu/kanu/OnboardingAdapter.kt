package wami.ikechukwu.kanu

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import wami.ikechukwu.kanu.model.OnboardingModel

class OnboardingAdapter(private val onboardingArrayList: ArrayList<OnboardingModel>) : RecyclerView.Adapter<OnboardingAdapter.ViewHolder>() {
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OnboardingAdapter.ViewHolder {

    }

    override fun getItemCount(): Int {
        return onboardingArrayList.size
    }

    override fun onBindViewHolder(holder: OnboardingAdapter.ViewHolder, position: Int) {
        TODO("Not yet implemented")
    }


}


