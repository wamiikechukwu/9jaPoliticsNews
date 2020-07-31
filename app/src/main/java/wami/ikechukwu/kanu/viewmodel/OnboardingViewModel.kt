package wami.ikechukwu.kanu.viewmodel

import androidx.lifecycle.ViewModel
import wami.ikechukwu.kanu.R
import wami.ikechukwu.kanu.adapter.OnboardingAdapter
import wami.ikechukwu.kanu.model.OnboardingModel

class OnboardingViewModel : ViewModel() {

    private val mArrayList: ArrayList<OnboardingModel> = ArrayList()

    fun initTheDataModel() {
        mArrayList.add(OnboardingModel("Get The Latest New", "Read them all new", R.drawable.git2, R.color.spots_dialog_color))
        mArrayList.add(OnboardingModel("Be updated", "Be inspire", R.drawable.git2, R.color.colorAccent))
        mArrayList.add(OnboardingModel("Read", "Learn", R.drawable.git2, R.color.colorPrimary))
    }

    fun initTheAdapter(): OnboardingAdapter {
        return OnboardingAdapter(mArrayList)
    }
}