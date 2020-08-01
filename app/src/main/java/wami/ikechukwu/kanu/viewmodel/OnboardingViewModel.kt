package wami.ikechukwu.kanu.viewmodel

import androidx.lifecycle.ViewModel
import wami.ikechukwu.kanu.R
import wami.ikechukwu.kanu.adapter.OnboardingAdapter
import wami.ikechukwu.kanu.model.OnboardingModel

class OnboardingViewModel : ViewModel() {
    //    DECLARED ARRAYLIST
    private val onArrayList: ArrayList<OnboardingModel> = ArrayList()

    init {
        initDataModelForTheOnboarding()
    }

    //SETTING UP THE ARRAYLIST
    private fun initDataModelForTheOnboarding() {
        onArrayList.add(OnboardingModel("Get The Latest New", "Read them all new", R.drawable.git2, R.color.firstColor))
        onArrayList.add(OnboardingModel("Be updated", "Be inspire", R.drawable.git2, R.color.secondColor))
        onArrayList.add(OnboardingModel("Read", "Learn", R.drawable.git2, R.color.thirdColor))
        onArrayList.add(OnboardingModel("see", "understand", R.drawable.git2, R.color.fourthColor))
    }

    //    PASSING IN THE ARRAYLIST TO THE ADAPTER
    fun initTheAdapter(): OnboardingAdapter {
        return OnboardingAdapter(onArrayList)
    }

    fun onArrayListSize(): Int {
        return onArrayList.size
    }
}